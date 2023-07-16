package com.liu.springboot.utils;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

import java.io.File;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-03  14:27
 * @Description:
 * @Author: LiuHaoYu
 */
public class Mp3Util {
    /**
     * 获取musicUtil对象
     * @param file 歌曲在磁盘中的地址
     * @return {@link MusicUtil}
     */
    public static MusicUtil getMp3Info(File file) {
        MusicUtil musicUtil = null;
        try {
            MP3File mp3File = (MP3File) AudioFileIO.read(file);
            AbstractID3v2Tag v2tag = mp3File.getID3v2Tag();
            // 歌手名
            String singName = v2tag.getFirst(FieldKey.ARTIST);
            // 專輯名
            v2tag.getFirst(FieldKey.ALBUM);
            // 歌名
            String songName = v2tag.getFirst(FieldKey.TITLE);
            // mp3文件頭部信息
            MP3AudioHeader header = mp3File.getMP3AudioHeader();
            int length = header.getTrackLength();
            // 歌曲時長
            String songLength = length / 60 + ":" + length % 60;
            musicUtil = new MusicUtil(singName,songName,songLength);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return musicUtil;
    }
}
