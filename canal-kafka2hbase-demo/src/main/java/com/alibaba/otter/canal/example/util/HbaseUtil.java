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
            put.addColumn(Bytes.toBytes("update_info"),Bytes.toBytes("exe_time"),Bytes.toBytes("111"));

            connection = ConnectionFactory.createConnection(configuration);
            TableName tableName = TableName.valueOf("lyf_test3");
            Table table = connection.getTable(tableName);

            //  如果row,family,qualifier,value存在，则不会执行put，并返回false
            boolean insertResult = table.checkAndPut(Bytes.toBytes("row1"),Bytes.toBytes("update_info"),Bytes.toBytes("exe_time"), null,put);

            boolean updateResult = false;

            // 输出结果看是否执行了put
            System.out.println("插入列结果:" + insertResult);

            if (!insertResult) {
                Put put2 = new Put(Bytes.toBytes("row1"));
                put2.addColumn(Bytes.toBytes("update_info"),Bytes.toBytes("exe_time"),Bytes.toBytes("112"));
                updateResult = table.checkAndMutate(Bytes.toBytes("row1"), Bytes.toBytes("update_info"))
                        .qualifier(Bytes.toBytes("exe_time")).ifEquals(Bytes.toBytes("1596783162000"))
                        .thenPut(put2);
                System.out.println("更新列结果:" + updateResult);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
