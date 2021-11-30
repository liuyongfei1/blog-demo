package com.fullstackboy.mybatis.rabbitmqdemo.mapper;

import com.fullstackboy.mybatis.rabbitmqdemo.model.ProductRobbingRecord;

/**
 * TODO Liuyongfei
 *
 * @Author: Liuyongfei
 * @Date: 2020/6/2 05:51
 */
public interface ProductRobbingRecordMapper {
    /**
     * 插入记录
     *
     * @Author Liuyongfei
     * @Date 上午5:56 2020/6/2
     * @param productRobbingRecord
     * @return int
     **/
    int insertRecord(ProductRobbingRecord productRobbingRecord);

    /**
     * 判断该用户是否已经秒杀成功了
     *
     * @Author Liuyongfei
     * @Date 上午6:30 2020/6/4
     * @param mobile
     * @return com.fullstackboy.rabbitmqdemo.model.ProductRobbingRecord
     **/
    ProductRobbingRecord detail(String mobile);
}
