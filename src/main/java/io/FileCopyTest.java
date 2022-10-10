package io;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;


/**
 * Java有多种比较典型的文件拷贝实现方式，比如：
 * 1. 利用java.io类库，直接为源文件构建一个FileInputStream读取，然后再为目标文件构建一个FileOutputStream，完成写入工作。
 * 2. 利用java.nio类库提供的transferTo或transferFrom方法实现。

  */
public class FileCopyTest {

    public static void main(String[] args) {
//        Files.copy();
    }

    public static void copyFileByStream(File source, File dest) throws IOException {
        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(dest);
        ) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }

    public static void copyFileByChannel(File scouce, File dest) throws IOException{
        FileChannel sourceChannel = new FileInputStream(scouce).getChannel();
        FileChannel targetChannel = new FileOutputStream(dest).getChannel();
        for (long count = sourceChannel.size(); count > 0;){
            long transferred = sourceChannel.transferTo(sourceChannel.position(), count, targetChannel);
            sourceChannel.position(sourceChannel.position() + transferred);
            count -= transferred;
        }
    }
}
