package com.fullstackboy.rabbitmqdemo.service;

import com.fullstackboy.rabbitmqdemo.mapper.ProductMapper;
import com.fullstackboy.rabbitmqdemo.model.Product;

/**
 * 多线程处理抢单
 *
 * @Author: Liuyongfei
 * @Date: 2020/6/1 09:38
 */
public class ConcurrencyService {
    private static final String ProductNo = "product_10010";

    ProductMapper productMapper;
    public void manageRobbing(String mobile) {
        Product product = productMapper.selectByProductNo(ProductNo);

        // 有库存
        if (product != null && product.getTotal() > 0) {
            // 更新库存
            int updateResult = productMapper.updateTotal(product);
            if (updateResult > 0) {
                // 向抢单信息表里插入该用户数据
            }
        }

    }
}
