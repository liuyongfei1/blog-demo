package com.fullstackboy.mybatis.rabbitmqdemo.mapper;

import com.fullstackboy.mybatis.rabbitmqdemo.model.Product;

/**
 * TODO Liuyongfei
 *
 * @Author: Liuyongfei
 * @Date: 2020/6/1 10:02
 */
public interface ProductMapper {
    /**
     * 由商品编号查询商品信息
     *
     * @Author Liuyongfei
     * @Date 下午10:45 2020/6/1
     * @param productNo
     * @return com.fullstackboy.rabbitmqdemo.model.Product
     **/
    Product selectByProductNo(String productNo);

    /**
     * 更新该商品的库存数
     *
     * @Author Liuyongfei
     * @Date 下午10:46 2020/6/1
     * @param product
     * @return int
     **/
    int updateTotal(Product product);
}
