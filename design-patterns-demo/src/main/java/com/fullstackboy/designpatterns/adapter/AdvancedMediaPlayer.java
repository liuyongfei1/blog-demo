package com.fullstackboy.designpatterns.adapter;

/**
 * 定义 先进的媒体播放器接口
 *
 * @author Liuyongfei
 * @date 2021/2/26 下午3:36
 */
public interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}
