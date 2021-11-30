package com.fullstackboy.mybatis.designpatterns.adapter;

/**
 * 创建实现 AdvancedMediaPlayer 接口的实体类
 *
 * @author Liuyongfei
 * @date 2021/2/26 15:37
 */
public class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // 什么也不做
    }
}
