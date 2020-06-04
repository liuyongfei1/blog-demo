package com.fullstackboy.rabbitmqdemo.service;

import com.fullstackboy.rabbitmqdemo.mapper.ProductMapper;
import com.fullstackboy.rabbitmqdemo.mapper.ProductRobbingRecordMapper;
import com.fullstackboy.rabbitmqdemo.model.Product;
import com.fullstackboy.rabbitmqdemo.model.ProductRobbingRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 多线程处理抢单
 *
 * @Author: Liuyongfei
 * @Date: 2020/6/1 09:38
 */
@Slf4j
@Service
public class ConcurrencyService {
    private static final String ProductNo = "product_10010";

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductRobbingRecordMapper productRobbingRecordMapper;


    /**
     * 处理抢单逻辑
     * 减少库存和下单的操作必须是原子性的
     * @param mobile
     * @return void
     * @Author Liuyongfei
     * @Date 上午5:59 2020/6/2
     **/
    @Transactional(rollbackFor = Exception.class)
    public void manageRobbing(String mobile) {
        try {
            Product product = productMapper.selectByProductNo(ProductNo);
            if (isCanRobbing(mobile, product)) {
                // 有库存
                // 更新库存
                log.info("此时商品库存为: [{}]", product.getTotal());
                int updateResult = productMapper.updateTotal(product);

                // 可以减库存
                if (updateResult > 0) {
                    // 向抢单信息表里插入用户数据
                    ProductRobbingRecord productRobbingRecord = new ProductRobbingRecord();
                    productRobbingRecord.setMobile(mobile);
                    productRobbingRecord.setProductId(product.getId());
                    String timeStamp = Long.toString(System.currentTimeMillis() / 1000);
                    productRobbingRecord.setCreateTime(Integer.parseInt(timeStamp));
                    productRobbingRecordMapper.insertRecord(productRobbingRecord);

                    // 异步通知（发短信）用户抢单成功
                    log.info("已经给用户成功发送短信");
                }
            }
        } catch (Exception e) {
            log.error("处理抢单发生异常: [{}]", e);
        }
    }

    /**
     * 判断该用户是否能够抢单
     *
     * @param mobile
     * @param product
     * @return boolean
     * @Author Liuyongfei
     * @Date 上午6:50 2020/6/4
     **/
    public boolean isCanRobbing(String mobile, Product product) {
        // 1.不存在该商品或者库存小于0，则直接返回
        if (product == null || product.getTotal() <= 0) {
            return false;
        }

        // 2.判断是否已经秒杀过了
        ProductRobbingRecord record = productRobbingRecordMapper.detail(mobile);
        if (record != null) {
            return false;
        }
        return true;
    }
}
