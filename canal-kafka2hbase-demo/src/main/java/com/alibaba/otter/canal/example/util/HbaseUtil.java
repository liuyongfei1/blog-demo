package com.alibaba.otter.canal.example.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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

    public static void insertData() {
        Connection connection = null;

        try {
            Put put = new Put(Bytes.toBytes("row1"));
//            put.addColumn(Bytes.toBytes("update_info"),Bytes.toBytes("exe_time"),Bytes.toBytes("1596783162000"));
            put.addColumn(Bytes.toBytes("update_info"), Bytes.toBytes("exe_time"), Bytes.toBytes("111"));

            connection = ConnectionFactory.createConnection(configuration);
            TableName tableName = TableName.valueOf("lyf_test3");
            Table table = connection.getTable(tableName);

            // 如果列update_info不存在值就插入数据，如果存在则返回false
            boolean insertResult = table.checkAndMutate(Bytes.toBytes("row1"), Bytes.toBytes("update_info"))
                    .qualifier(Bytes.toBytes("exe_time"))
                    .ifNotExists()
                    .thenPut(put);

            boolean updateResult = false;

            // 更新
            if (!insertResult) {
                // 如果列表相等就更新数据
                Put put2 = new Put(Bytes.toBytes("row1"));
                put2.addColumn(Bytes.toBytes("update_info"), Bytes.toBytes("exe_time"), Bytes.toBytes("1596783162000"));
                updateResult = table.checkAndMutate(Bytes.toBytes("row1"), Bytes.toBytes("update_info"))
                        .qualifier(Bytes.toBytes("exe_time")).ifEquals(Bytes.toBytes("112"))
                        .thenPut(put2);

                // 插入列:exe_position
                if (!updateResult) {
                    // 如果列exe_position不存在值就插入数据，如果存在则返回false
                    Put put3 = new Put(Bytes.toBytes("row1"));
                    put3.addColumn(Bytes.toBytes("update_info"), Bytes.toBytes("exe_position"), Bytes.toBytes("666"));
                    boolean res3 = table.checkAndMutate(Bytes.toBytes("row1"), Bytes.toBytes("update_info"))
                            .qualifier(Bytes.toBytes("exe_position"))
                            .ifNotExists()
                            .thenPut(put3);

                    // 更新列 exe_position 的值
                    if (!res3) {
                        Put put4 = new Put(Bytes.toBytes("row1"));
                        put4.addColumn(Bytes.toBytes("update_info"), Bytes.toBytes("exe_position"), Bytes.toBytes("777"));

                        // 如果row1 update_info exe_position 666 存在就插入新数据
                        boolean res4 = table.checkAndMutate(Bytes.toBytes("row1"), Bytes.toBytes("update_info"))
                                .qualifier(Bytes.toBytes("exe_position")).ifEquals(Bytes.toBytes("666"))
                                .thenPut(put4);
                        LOGGER.info("列exe_position更新结果:" + res4);
                    } else {
                        LOGGER.info("列exe_position插入值成功");
                    }
                } else {
                    LOGGER.info("列exe_time更新值成功");
                }
            } else {
                LOGGER.info("列exe_time插入值成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
