package com.fullstackboy.canaldemo.client;


import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;

/**
 * 编写
 *
 * @Author: Liuyongfei
 * @Date: 2020/7/17 16:27
 */
public class CanalKafkaClient {
    public static void main(String[] args) {
        CanalConnector connector = CanalConnectors.newClusterConnector("10.200.23.109:2181", "yzcrm", "", "");

        int batchSize = 1000;
        int emptyCount = 0;

        try {
            connector.connect();
            System.out.println("test111");

            // 获取指定数量的数据
            Message message = connector.getWithoutAck(batchSize);
            long batchId = message.getId();
            int size = message.getEntries().size();

            System.out.println("batchId:" + batchId);
            System.out.println("size:" + size);
        } catch (CanalClientException e) {
            e.printStackTrace();
        } finally {
            System.out.println("发生异常");
        }
    }
}
