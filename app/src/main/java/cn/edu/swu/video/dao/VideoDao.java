package cn.edu.swu.video.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import cn.edu.swu.video.javaBean.Video;

@Dao
public interface VideoDao {

    @Query("SELECT * FROM Video")
    List<Video> getAll();

    @Insert
    void addVideos(List<Video> videos);

    @Insert
    void addVideo(Video video);



}
