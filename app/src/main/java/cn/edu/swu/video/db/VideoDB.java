package cn.edu.swu.video.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import cn.edu.swu.video.dao.VideoDao;
import cn.edu.swu.video.javaBean.Video;

@Database(entities = Video.class,version = 1)
public abstract class VideoDB extends RoomDatabase {
    public abstract VideoDao videoDao();
}
