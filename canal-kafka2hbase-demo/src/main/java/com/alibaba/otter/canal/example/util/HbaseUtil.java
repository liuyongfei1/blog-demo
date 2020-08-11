package com.alibaba.otter.canal.example.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Desc:    Hbase客户端
 */
public class HbaseUtil {
    private static final Logger SERVICELOGGER = LoggerFactory.getLogger("serviceLogger");
    private static final Logger LOGGER = LoggerFactory.getLogger(com.alibaba.otter.canal.example.util.HbaseUtil.class);

    public static Configuration configuration;

    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "10.200.23.109,10.200.23.110,10.200.23.111");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
    }

    /**
     * 创建表
     *
     * @param tableName 表名
     */
    public static void createTable(String tableName) {
        SERVICELOGGER.info("HbaseUtil.createTable 创建Hbase表[" + tableName + "]......");
        Connection connection = null;
        Admin admin = null;
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
            // 如果存在要创建的表，那么先删除，再创建
            if (!admin.tableExists(TableName.valueOf(tableName))) {
                HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
                tableDescriptor.addFamily(new HColumnDescriptor("info"));
                tableDescriptor.addFamily(new HColumnDescriptor("update_info"));
                admin.createTable(tableDescriptor);
                SERVICELOGGER.info("HbaseUtil.createTable 创建Hbase表[" + tableName + "]成功!");
            } else {
                SERVICELOGGER.info("HbaseUtil.createTable 表名为:" + tableName + "的数据已存在!");
            }
        } catch (Exception ex) {
            LOGGER.error("HbaseUtil.createTable 表名为:" + tableName + " 创建失败!原因:" + ex.getMessage());
        } finally {
            try {
                if (admin != null) {
                    admin.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (Exception ex2) {
                LOGGER.error("HbaseUtil.createTable 表名为:" + tableName + "创建失败!原因:关闭连接失败" + ex2.getMessage());
            }

        }
    }

    public static void insertData(Map<String,String> hbaseData) {
        Connection connection = null;

        try {
            Put put = new Put(Bytes.toBytes(hbaseData.get("id")));

            for(Map.Entry<String, String> entry : hbaseData.entrySet()){
                String mapKey = entry.getKey();
                String mapValue = entry.getValue();
                if ("execute_file".equals(mapKey) || "execute_position".equals(mapKey) || "execute_time".equals(mapKey)) {
                    // 创建列簇和列
                    put.addColumn(Bytes.toBytes("update_info"), Bytes.toBytes(mapKey), Bytes.toBytes(hbaseData.get(mapKey)));
                } else {
                    put.addColumn(Bytes.toBytes("info"), Bytes.toBytes(mapKey), Bytes.toBytes(hbaseData.get(mapKey)));
                }
            }

            // 创建列簇和列
//            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("id"), Bytes.toBytes(hbaseData.get("id")));
//            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes(hbaseData.get("name")));
//            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("status"), Bytes.toBytes(hbaseData.get("status")));
//            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("content"), Bytes.toBytes(hbaseData.get("content")));

            // 创建列簇和列
//            put.addColumn(Bytes.toBytes("update_info"), Bytes.toBytes("execute_file"),Bytes.toBytes(hbaseData.get("execute_file")));
//            put.addColumn(Bytes.toBytes("update_info"), Bytes.toBytes("execute_position"),Bytes.toBytes(hbaseData.get("logfileOffset")));
//            put.addColumn(Bytes.toBytes("update_info"), Bytes.toBytes("execute_time"),Bytes.toBytes(hbaseData.get("execute_time")));


            // 组装 binlog 里的 column 数据
//            JSONObject columnData = new JSONObject();
//            columnData.put("name",hbaseData.get("name"));
//            String columnDataStr = JSONObject.toJSONString(columnData);

            connection = ConnectionFactory.createConnection(configuration);
            TableName tableName = TableName.valueOf("lyf_canal_test");
            Table table = connection.getTable(tableName);

            // 如果 update_info（列族） execute_time（列） 不存在值就插入数据，如果存在则返回false
            boolean res1 = table.checkAndMutate(Bytes.toBytes(hbaseData.get("id")), Bytes.toBytes("update_info"))
                    .qualifier(Bytes.toBytes("execute_time"))
                    .ifNotExists()
                    .thenPut(put);

            System.out.println("res1:" + res1);

            if (!res1) {
                // 如果 update_info（列族） execute_time（列） 不等于 val2，则插入put
//                boolean res2 = table.checkAndMutate(Bytes.toBytes(hbaseData.get("id")), Bytes.toBytes(
//                        "update_info"))
//                        .qualifier(Bytes.toBytes("execute_time"))
//                        .ifMatches(CompareOperator.NOT_EQUAL,Bytes.toBytes("val2"))
//                        .thenPut(put);

                boolean res2  = table.checkAndPut(Bytes.toBytes(hbaseData.get("id")), Bytes.toBytes("update_info"),
                        Bytes.toBytes("execute_time"), CompareFilter.CompareOp.GREATER, Bytes.toBytes(hbaseData.get("execute_time")),
                        put);
                System.out.println("res2:" + res2);
                
                boolean res3  = table.checkAndPut(Bytes.toBytes(hbaseData.get("id")), Bytes.toBytes("update_info"),
                        Bytes.toBytes("execute_position"), CompareFilter.CompareOp.GREATER, Bytes.toBytes(hbaseData.get(
                                "execute_position")),
                        put);

                System.out.println("res3:" + res3);

//                boolean res3 = table.checkAndMutate(Bytes.toBytes(hbaseData.get("id")), Bytes.toBytes("update_info"),CompareFilter.CompareOp.GREATER,)
//                        .qualifier(Bytes.toBytes("execute_time"))
//                        .ifNotExists()
//                        .thenPut(put);
            }

            // 列：exe_position
//            Put put3 = new Put(Bytes.toBytes(hbaseData.get("id")));
//            put3.addColumn(Bytes.toBytes("update_info"), Bytes.toBytes("exe_position"), Bytes.toBytes(hbaseData.get("logfileOffset")));
//            boolean res3 = table.checkAndMutate(Bytes.toBytes(hbaseData.get("id")), Bytes.toBytes("update_info"))
//                    .qualifier(Bytes.toBytes("exe_position"))
//                    .ifNotExists()
//                    .thenPut(put3);
//            System.out.println("res3:" + res3);
//
//            if (!res3) {
//                // 如果 update_info（列族） exe_position（列） 不等于 val2，则插入put
//                boolean res4 = table.checkAndMutate(Bytes.toBytes(hbaseData.get("id")), Bytes.toBytes("update_info"))
//                        .qualifier(Bytes.toBytes("exe_position"))
//                        .ifMatches(CompareOperator.NOT_EQUAL,Bytes.toBytes("val2"))
//                        .thenPut(put3);
//            }

            // 列：columnData
//            Put put4 = new Put(Bytes.toBytes(hbaseData.get("id")));
//            put4.addColumn(Bytes.toBytes("update_info"), Bytes.toBytes("columnData"), Bytes.toBytes(columnDataStr));
//
//            boolean res5 = table.checkAndMutate(Bytes.toBytes(hbaseData.get("id")), Bytes.toBytes("update_info"))
//                    .qualifier(Bytes.toBytes("columnData"))
//                    .ifNotExists()
//                    .thenPut(put4);
//            System.out.println("res5:" + res5);
//
//            if (!res5) {
//                boolean res6 = table.checkAndMutate(Bytes.toBytes(hbaseData.get("id")), Bytes.toBytes("update_info"))
//                        .qualifier(Bytes.toBytes("columnData"))
//                        .ifMatches(CompareOperator.NOT_EQUAL,Bytes.toBytes("val2"))
//                        .thenPut(put4);
//            }
            System.out.println("----------hbase操作 end---------");

            // 更新
//            if (!insertResult) {
//                // 如果相等就更新execute_time的值
//                Put put2 = new Put(Bytes.toBytes(hbaseData.get("id")));
//                put2.addColumn(Bytes.toBytes("update_info"), Bytes.toBytes("execute_time"), Bytes.toBytes(hbaseData.get("execute_time")));
                // 备注：这里要先从hbase表里取出当前列的值，然后再做判断
//                updateResult = table.checkAndMutate(Bytes.toBytes(hbaseData.get("id")), Bytes.toBytes("update_info"))
//                        .qualifier(Bytes.toBytes("execute_time")).ifEquals(Bytes.toBytes("112"))
//                        .thenPut(put2);

                // 插入列:exe_position
//                if (!updateResult) {
//                    // 如果列exe_position不存在值就插入数据，如果存在则返回false
//                    Put put3 = new Put(Bytes.toBytes(hbaseData.get("id")));
//                    put3.addColumn(Bytes.toBytes("update_info"), Bytes.toBytes("exe_position"), Bytes.toBytes(hbaseData.get("logfileOffset")));
//                    boolean res3 = table.checkAndMutate(Bytes.toBytes(hbaseData.get("id")), Bytes.toBytes("update_info"))
//                            .qualifier(Bytes.toBytes("exe_position"))
//                            .ifNotExists()
//                            .thenPut(put3);
//
//                    // 更新列 exe_position 的值
//                    if (!res3) {
//                        Put put4 = new Put(Bytes.toBytes(hbaseData.get("id")));
//                        put4.addColumn(Bytes.toBytes("update_info"), Bytes.toBytes("exe_position"), Bytes.toBytes(hbaseData.get("logfileOffset")));
//
//                        // 备注：这里要先从hbase表里取出当前列的值，然后再做判断
//                        // 如果row1 update_info exe_position 666 存在就插入新数据
//                        boolean res4 = table.checkAndMutate(Bytes.toBytes(hbaseData.get("id")), Bytes.toBytes("update_info"))
//                                .qualifier(Bytes.toBytes("exe_position")).ifEquals(Bytes.toBytes("666"))
//                                .thenPut(put4);
//                        LOGGER.info("列exe_position更新结果:" + res4);
//                    } else {
//                        LOGGER.info("列exe_position插入值成功");
//                    }
//                } else {
//                    LOGGER.info("列execute_time更新值成功");
//                }
//            } else {
//                LOGGER.info("列execute_time插入值成功");
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
