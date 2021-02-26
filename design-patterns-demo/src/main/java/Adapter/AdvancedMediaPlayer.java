package Adapter;

/**
 * 定义 先进的媒体播放器接口
 *
 * @author Liuyongfei
 * @date 2021/2/26 下午3:36
 */
public interface AdvancedMediaPlayer {
    public void playVlc(String fileName);
    public void playMp4(String fileName);
}
