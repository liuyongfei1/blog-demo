package com.fullstackboy.designpatterns.adapter;

/**
 * 创建实现 AdvancedMediaPlayer 接口的实体类
 *
 * @author Liuyongfei
 * @date 2021/2/26 15:39
 */
public class Mp4Player implements AdvancedMediaPlayer{
    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }

    @Override
    public void playVlc(String fileName) {
        // 什么也不做
    }
}
