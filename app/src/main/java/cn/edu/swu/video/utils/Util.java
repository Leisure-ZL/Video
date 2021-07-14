package cn.edu.swu.video.utils;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.widget.ImageView;

import java.util.HashMap;

public class Util {

    public static void setNetVideoBitmap(String videoUrl, final ImageView img) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = null;
                        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                        try {
                            //根据url获取缩略图
                            retriever.setDataSource(videoUrl, new HashMap());
                            //获得第一帧图片
                            bitmap = retriever.getFrameAtTime();
                            Bitmap finalBitmap = bitmap;
                            img.post(new Runnable() {
                                @Override
                                public void run() {
                                    img.setImageBitmap(finalBitmap);
                                }
                            });
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } finally {
                            retriever.release();
                        }
                    }
                }
        ).start();
    }


}
