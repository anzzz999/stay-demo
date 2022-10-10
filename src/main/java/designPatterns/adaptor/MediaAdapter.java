package designPatterns.adaptor;

import designPatterns.adaptor.advancedMediaPlayer.AdvancedMediaPlayer;
import designPatterns.adaptor.advancedMediaPlayer.Mp4Player;
import designPatterns.adaptor.advancedMediaPlayer.VlcPlayer;
import designPatterns.adaptor.mediaPlayer.MediaPlayer;

/**
 * @Author: zhanmingwei
 *  对象适配器，使用成员对象
 */
public class MediaAdapter implements MediaPlayer {

    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}

