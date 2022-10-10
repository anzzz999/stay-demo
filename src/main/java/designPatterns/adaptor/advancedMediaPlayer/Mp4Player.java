package designPatterns.adaptor.advancedMediaPlayer;

import designPatterns.adaptor.advancedMediaPlayer.AdvancedMediaPlayer;

/**
 * @Author: zhanmingwei
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}
