package designPatterns.adaptor.advancedMediaPlayer;

import designPatterns.adaptor.advancedMediaPlayer.AdvancedMediaPlayer;

/**
 * @Author: zhanmingwei
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // 什么也不做
    }
}
