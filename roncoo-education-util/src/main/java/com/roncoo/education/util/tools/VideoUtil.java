package com.roncoo.education.util.tools;

import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

import java.io.File;

public class VideoUtil {

    /**
     * 获取视频时长：秒
     *
     * @param file
     * @return
     */
    public static String getVedioTime(File file) {
        try {
            MultimediaObject instance = new MultimediaObject(file);
            MultimediaInfo result = instance.getInfo();
            long ls = result.getDuration() / 1000;
            return DateUtil.secondToTime(ls);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
