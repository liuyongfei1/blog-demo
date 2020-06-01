package com.fullstackboy.rabbitmqdemo.mapper;

import com.fullstackboy.rabbitmqdemo.model.ProductRobbingRecord;

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
}
