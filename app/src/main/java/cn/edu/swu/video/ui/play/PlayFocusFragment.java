package cn.edu.swu.video.ui.play;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.swu.video.R;
import cn.edu.swu.video.javaBean.User;
import cn.edu.swu.video.javaBean.Video;

import static cn.edu.swu.video.utils.Util.setNetVideoBitmap;

public class PlayFocusFragment extends Fragment {
    RecyclerView mUserRecyclerView;
    List<User> mUserData = new ArrayList<User>();
    LayoutInflater mUserLayoutInflater;
    UserAdapter mUserAdapter;

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
        View view = inflater.inflate(R.layout.fragment_play_focus, container, false);

        User user = new User();
        user.setName("123");
        mUserData.add(user);
        mUserData.add(user);
        mUserData.add(user);
        mUserData.add(user);
        mUserData.add(user);
        mUserData.add(user);
        mUserData.add(user);
        mUserData.add(user);

        mUserRecyclerView = view.findViewById(R.id.f_rv_user);
        mUserAdapter = new UserAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        mUserLayoutInflater = getLayoutInflater();
        mUserRecyclerView.setAdapter(mUserAdapter);
        mUserRecyclerView.setLayoutManager(manager);


        Video item = new Video();
        item.setSrc("http://vjs.zencdn.net/v/oceans.mp4");
        mVideoData.add(item);
        mVideoData.add(item);
        mVideoData.add(item);
        mVideoData.add(item);
        mVideoData.add(item);
        mVideoData.add(item);
        mVideoData.add(item);
        mVideoData.add(item);

        mVideoRecyclerView = view.findViewById(R.id.f_rv_video);
        mVideoAdapter = new VideoAdapter();
        GridLayoutManager manager1 = new GridLayoutManager(getContext(),2);
        mVideoLayoutInflater = getLayoutInflater();
        mVideoRecyclerView.setLayoutManager(manager1);
        mVideoRecyclerView.setAdapter(mVideoAdapter);

        reFresh(view);


        return view;
    }

    private void reFresh(View view) {
        RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }



    class UserViewHolder extends RecyclerView.ViewHolder{

        ImageView mImgView;
        TextView mTextView;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgView = itemView.findViewById(R.id.focus_user_headImg);
            mTextView = itemView.findViewById(R.id.focus_user_name);
        }
    }

    class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mUserLayoutInflater.inflate(R.layout.recyclerview_play_focus_user,parent,false);
            return new UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            User user = mUserData.get(position);
         //   holder.mImgView.setImageBitmap(getLoacalBitmap(user.getHeadImg()));
            holder.mTextView.setText(user.getName());
        }

        @Override
        public int getItemCount() {
            return mUserData.size();
        }

        public Bitmap getLoacalBitmap(String url) {
            try {
                FileInputStream fis = new FileInputStream(url);
                return BitmapFactory.decodeStream(fis);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
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