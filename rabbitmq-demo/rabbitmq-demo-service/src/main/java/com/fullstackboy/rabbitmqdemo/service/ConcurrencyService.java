package com.fullstackboy.rabbitmqdemo.service;

import com.fullstackboy.rabbitmqdemo.mapper.ProductMapper;
import com.fullstackboy.rabbitmqdemo.mapper.ProductRobbingRecordMapper;
import com.fullstackboy.rabbitmqdemo.model.Product;
import com.fullstackboy.rabbitmqdemo.model.ProductRobbingRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     *
     * @Author Liuyongfei
     * @Date 上午5:59 2020/6/2
     * @param mobile
     * @return void
     **/
    public void manageRobbing(String mobile) {
        try {
            Product product = productMapper.selectByProductNo(ProductNo);

            // 有库存
            if (product != null && product.getTotal() > 0) {
                // 更新库存
                int updateResult = productMapper.updateTotal(product);
                if (updateResult > 0) {
                    // 向抢单信息表里插入用户数据
                    ProductRobbingRecord productRobbingRecord = new ProductRobbingRecord();
                    productRobbingRecord.setMobile(mobile);
                    productRobbingRecord.setProductId(product.getId());
                    productRobbingRecordMapper.insertRecord(productRobbingRecord);
                }
            }
        } catch (Exception e) {
            log.error("处理抢单发生异常: [{}]",mobile);
        }

    }
}
