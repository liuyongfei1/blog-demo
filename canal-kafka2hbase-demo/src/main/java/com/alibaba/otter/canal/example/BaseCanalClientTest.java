package com.alibaba.otter.canal.example;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.otter.canal.example.util.HbaseUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.Pair;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.protocol.CanalEntry.TransactionBegin;
import com.alibaba.otter.canal.protocol.CanalEntry.TransactionEnd;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.InvalidProtocolBufferException;

public class BaseCanalClientTest {

    protected final static Logger             logger             = LoggerFactory.getLogger(AbstractCanalClientTest.class);
    protected static final String             SEP                = SystemUtils.LINE_SEPARATOR;
    protected static final String             DATE_FORMAT        = "yyyy-MM-dd HH:mm:ss";
    protected volatile boolean                running            = false;
    protected Thread.UncaughtExceptionHandler handler            = new Thread.UncaughtExceptionHandler() {

                                                                     public void uncaughtException(Thread t, Throwable e) {
                                                                         logger.error("parse events has an error", e);
                                                                     }
                                                                 };
    protected Thread                          thread             = null;
    protected CanalConnector                  connector;
    protected static String                   context_format     = null;
    protected static String                   row_format         = null;
    protected static String                   transaction_format = null;
    protected String                          destination;

    static {
        context_format = SEP + "****************************************************" + SEP;
        context_format += "* Batch Id: [{}] ,count : [{}] , memsize : [{}] , Time : {}" + SEP;
        context_format += "* Start : [{}] " + SEP;
        context_format += "* End : [{}] " + SEP;
        context_format += "****************************************************" + SEP;

        row_format = SEP
                     + "----------------> binlog[{}:{}] , name[{},{}] , eventType : {} , executeTime : {}({}) , gtid : ({}) , delay : {} ms"
                     + SEP;

        transaction_format = SEP
                             + "================> binlog[{}:{}] , executeTime : {}({}) , gtid : ({}) , delay : {}ms"
                             + SEP;

    }

    protected void printSummary(Message message, long batchId, int size) {
        long memsize = 0;
        for (Entry entry : message.getEntries()) {
            memsize += entry.getHeader().getEventLength();
        }

        String startPosition = null;
        String endPosition = null;
        if (!CollectionUtils.isEmpty(message.getEntries())) {
            startPosition = buildPositionForDump(message.getEntries().get(0));
            endPosition = buildPositionForDump(message.getEntries().get(message.getEntries().size() - 1));
        }

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        logger.info(context_format, new Object[] { batchId, size, memsize, format.format(new Date()), startPosition,
                endPosition });
    }

    protected String buildPositionForDump(Entry entry) {
        long time = entry.getHeader().getExecuteTime();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        String position = entry.getHeader().getLogfileName() + ":" + entry.getHeader().getLogfileOffset() + ":"
                          + entry.getHeader().getExecuteTime() + "(" + format.format(date) + ")";
        if (StringUtils.isNotEmpty(entry.getHeader().getGtid())) {
            position += " gtid(" + entry.getHeader().getGtid() + ")";
        }
        return position;
    }

    protected void printEntry(List<Entry> entrys) { for (Entry entry : entrys) {
            String logfileName = entry.getHeader().getLogfileName();
            long executeTime = entry.getHeader().getExecuteTime();
            long delayTime = new Date().getTime() - executeTime;
            long execute_position = entry.getHeader().getLogfileOffset();
            logger.info("execute_time：" + executeTime + "\n");
            logger.info("execute_position：" + execute_position + "\n");
            Date date = new Date(entry.getHeader().getExecuteTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN) {
                    TransactionBegin begin = null;
                    try {
                        begin = TransactionBegin.parseFrom(entry.getStoreValue());
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                    }
                    // 打印事务头信息，执行的线程id，事务耗时
//                    logger.info(transaction_format,
//                        new Object[] { entry.getHeader().getLogfileName(),
//                                String.valueOf(entry.getHeader().getLogfileOffset()),
//                                String.valueOf(entry.getHeader().getExecuteTime()), simpleDateFormat.format(date),
//                                entry.getHeader().getGtid(), String.valueOf(delayTime) });
//                    logger.info(" BEGIN ----> Thread id: {}\n", begin.getThreadId());
                    printXAInfo(begin.getPropsList());
                } else if (entry.getEntryType() == EntryType.TRANSACTIONEND) {
                    TransactionEnd end = null;
                    try {
                        end = TransactionEnd.parseFrom(entry.getStoreValue());
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                    }
                    // 打印事务提交信息，事务id
                    logger.info("----------------\n");
                    logger.info(" END ----> transaction id: {}", end.getTransactionId());
                    printXAInfo(end.getPropsList());
//                    logger.info(transaction_format,
//                        new Object[] { entry.getHeader().getLogfileName(),
//                                String.valueOf(entry.getHeader().getLogfileOffset()),
//                                String.valueOf(entry.getHeader().getExecuteTime()), simpleDateFormat.format(date),
//                                entry.getHeader().getGtid(), String.valueOf(delayTime) });
                }

                continue;
            }

            if (entry.getEntryType() == EntryType.ROWDATA) {
                RowChange rowChage = null;
                try {
                    rowChage = RowChange.parseFrom(entry.getStoreValue());
                } catch (Exception e) {
                    throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                }

                EventType eventType = rowChage.getEventType();

//                logger.info(row_format,
//                    new Object[] { entry.getHeader().getLogfileName(),
//                            String.valueOf(entry.getHeader().getLogfileOffset()), entry.getHeader().getSchemaName(),
//                            entry.getHeader().getTableName(), eventType,
//                            String.valueOf(entry.getHeader().getExecuteTime()), simpleDateFormat.format(date),
//                            entry.getHeader().getGtid(), String.valueOf(delayTime) });

                if (eventType == EventType.QUERY || rowChage.getIsDdl()) {
                    logger.info(" sql ----> " + rowChage.getSql() + SEP);
                    continue;
                }

                printXAInfo(rowChage.getPropsList());

                // 组装插入HBase表的数据
                Map<String, String> hbaseData = new HashMap<>();
                hbaseData.put("execute_file",logfileName);
                hbaseData.put("execute_position",String.valueOf(execute_position));
                hbaseData.put("execute_time",String.valueOf(executeTime));

                for (RowData rowData : rowChage.getRowDatasList()) {
                    if (eventType == EventType.DELETE) {
                        deleteData(rowData.getBeforeColumnsList(),hbaseData);
                    } else if (eventType == EventType.INSERT) {
                        printColumn(rowData.getAfterColumnsList(),hbaseData);
                    } else {
                        printColumn(rowData.getAfterColumnsList(),hbaseData);
                    }
                }

//                logger.info("-------createHBaseTable start");
//                // 创建HBase表
//                createHBaseTable();
//                logger.info("-------createHBaseTable end");
//
                // 为HBase表插入数据
                logger.info("-------准备将数据插入HBase表---------");
                HbaseUtil.insertData(hbaseData);
                logger.info("-------数据插入HBase表结束---------");
            }
        }
    }

    protected void printColumn(List<Column> columns) {
        for (Column column : columns) {
            StringBuilder builder = new StringBuilder();
            try {
                if (StringUtils.containsIgnoreCase(column.getMysqlType(), "BLOB")
                    || StringUtils.containsIgnoreCase(column.getMysqlType(), "BINARY")) {
                    // get value bytes
                    builder.append(column.getName() + " : "
                                   + new String(column.getValue().getBytes("ISO-8859-1"), "UTF-8"));
                } else {
                    builder.append(column.getName() + " : " + column.getValue());
                }
            } catch (UnsupportedEncodingException e) {
            }
            builder.append("    type=" + column.getMysqlType());
            if (column.getUpdated()) {
                builder.append("    update=" + column.getUpdated());
            }
            builder.append(SEP);
            logger.info(builder.toString());
        }
    }

    protected void printColumn(List<Column> columns,Map<String,String> hbaseData) {
        for (Column column : columns) {
            StringBuilder builder = new StringBuilder();
            try {
                if (StringUtils.containsIgnoreCase(column.getMysqlType(), "BLOB")
                        || StringUtils.containsIgnoreCase(column.getMysqlType(), "BINARY")) {
                    // get value bytes
                    builder.append(column.getName() + " : "
                            + new String(column.getValue().getBytes("ISO-8859-1"), "UTF-8"));
                } else {
                    builder.append(column.getName() + " : " + column.getValue());

                    // 组装插入HBase表的数据
//                    if ("id".equals(column.getName()) || "name".equals(column.getName())) {
//                        hbaseData.put(column.getName(),column.getValue());
//                    }
                    hbaseData.put(column.getName(),column.getValue());
                }
            } catch (UnsupportedEncodingException e) {
            }
            builder.append("    type=" + column.getMysqlType());
            if (column.getUpdated()) {
                builder.append("    update=" + column.getUpdated());
            }
            builder.append(SEP);
            logger.info(builder.toString());
        }
    }

    /**
     * 删除数据
     *
     * @Author Liuyongfei
     * @Date 下午5:54 2020/8/11
     * @param columns
     * @param hbaseData
     * @return void
     **/
    protected void deleteData(List<Column> columns,Map<String,String> hbaseData) {
        for (Column column : columns) {
            StringBuilder builder = new StringBuilder();
            try {
                if (StringUtils.containsIgnoreCase(column.getMysqlType(), "BLOB")
                        || StringUtils.containsIgnoreCase(column.getMysqlType(), "BINARY")) {
                    // get value bytes
                    builder.append(column.getName() + " : "
                            + new String(column.getValue().getBytes("ISO-8859-1"), "UTF-8"));
                } else {
                    builder.append(column.getName() + " : " + column.getValue());

                    // 组装插入HBase表的数据
//                    if ("id".equals(column.getName()) || "name".equals(column.getName())) {
//                        hbaseData.put(column.getName(),column.getValue());
//                    }
                    if ("id".equals(column.getName())) {
                        logger.info("删除数据，主键id：" + column.getValue() + "\n");
                    }

                    hbaseData.put(column.getName(),column.getValue());
                }
            } catch (UnsupportedEncodingException e) {
            }
            builder.append("    type=" + column.getMysqlType());
            if (column.getUpdated()) {
                builder.append("    update=" + column.getUpdated());
            }
            builder.append(SEP);
            logger.info(builder.toString());
        }
    }

    protected void printXAInfo(List<Pair> pairs) {
        if (pairs == null) {
            return;
        }

        String xaType = null;
        String xaXid = null;
        for (Pair pair : pairs) {
            String key = pair.getKey();
            if (StringUtils.endsWithIgnoreCase(key, "XA_TYPE")) {
                xaType = pair.getValue();
            } else if (StringUtils.endsWithIgnoreCase(key, "XA_XID")) {
                xaXid = pair.getValue();
            }
        }

        if (xaType != null && xaXid != null) {
            logger.info(" ------> " + xaType + " " + xaXid);
        }
    }

    public void setConnector(CanalConnector connector) {
        this.connector = connector;
    }

    /**
     * 获取当前Entry的 GTID信息示例
     * 
     * @param header
     * @return
     */
    public static String getCurrentGtid(CanalEntry.Header header) {
        List<CanalEntry.Pair> props = header.getPropsList();
        if (props != null && props.size() > 0) {
            for (CanalEntry.Pair pair : props) {
                if ("curtGtid".equals(pair.getKey())) {
                    return pair.getValue();
                }
            }
        }
        return "";
    }

    /**
     * 获取当前Entry的 GTID Sequence No信息示例
     * 
     * @param header
     * @return
     */
    public static String getCurrentGtidSn(CanalEntry.Header header) {
        List<CanalEntry.Pair> props = header.getPropsList();
        if (props != null && props.size() > 0) {
            for (CanalEntry.Pair pair : props) {
                if ("curtGtidSn".equals(pair.getKey())) {
                    return pair.getValue();
                }
            }
        }
        return "";
    }

    /**
     * 获取当前Entry的 GTID Last Committed信息示例
     * 
     * @param header
     * @return
     */
    public static String getCurrentGtidLct(CanalEntry.Header header) {
        List<CanalEntry.Pair> props = header.getPropsList();
        if (props != null && props.size() > 0) {
            for (CanalEntry.Pair pair : props) {
                if ("curtGtidLct".equals(pair.getKey())) {
                    return pair.getValue();
                }
            }
        }
        return "";
    }

    /**
     * 创建Hbase表
     *
     * @Author Liuyongfei
     * @Date 下午11:23 2020/8/6
     * @return void
     **/
    private void createHBaseTable() {
        HbaseUtil.createTable("lyf_test3");

    }

}
