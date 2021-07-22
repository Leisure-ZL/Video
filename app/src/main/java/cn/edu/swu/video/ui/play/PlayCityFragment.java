package cn.edu.swu.video.ui.play;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.swu.video.R;
import cn.edu.swu.video.javaBean.Video;

import static cn.edu.swu.video.utils.Util.setNetVideoBitmap;

public class PlayCityFragment extends Fragment {

    RecyclerView mVideoRecyclerView;
    List<Video> mVideoData = new ArrayList<Video>();
    LayoutInflater mVideoLayoutInflater;
    VideoAdapter mVideoAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_city, container, false);

        Video item = new Video();
        item.setSrc("http://vjs.zencdn.net/v/oceans.mp4");
        mVideoData.add(item);
        mVideoData.add(item);
        mVideoData.add(item);
        mVideoData.add(item);

        mVideoRecyclerView = view.findViewById(R.id.p_c_rv);
        mVideoAdapter = new VideoAdapter();
        GridLayoutManager manager1 = new GridLayoutManager(getContext(),2);
        mVideoLayoutInflater = getLayoutInflater();
        mVideoRecyclerView.setLayoutManager(manager1);
        mVideoRecyclerView.setAdapter(mVideoAdapter);

        return view;
    }

    class VideoViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.v_f_img);
        }
    }

    class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder>{

        @NonNull
        @Override
        public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mVideoLayoutInflater.inflate(R.layout.recyclerview_play_video,parent,false);
            return new VideoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
            Video item = mVideoData.get(position);
            setNetVideoBitmap(item.getSrc(),holder.imageView);
        }

        @Override
        public int getItemCount() {
            return mVideoData.size();
        }
    }


}