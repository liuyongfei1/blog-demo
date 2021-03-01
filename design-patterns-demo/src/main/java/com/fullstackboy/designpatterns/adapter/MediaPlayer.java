package com.fullstackboy.designpatterns.adapter;

/**
 * 适配器模式Demo
 *
 * 定义媒体播放器接口
 *
 * @author Liuyongfei
 * @date 2021/2/26 15:33
 */
public interface MediaPlayer {
    void play(String audioType, String fileName);
}
