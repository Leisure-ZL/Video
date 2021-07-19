package cn.edu.swu.video.play;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.swu.video.R;
import cn.edu.swu.video.javaBean.Video;
import cn.edu.swu.video.view.MyDialog;
import cn.edu.swu.video.view.PagerLayoutManager;

public class PlaySquareFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    RecyclerView mRecyclerview;
    LayoutInflater mLayoutInflater;
    ItemAdapter mAdapter;
    List<Video> mData = new ArrayList<>();

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_square, container, false);

        mRecyclerview = view.findViewById(R.id.p_s_rv);
        mLayoutInflater = getLayoutInflater();
        PagerLayoutManager layoutManager = new PagerLayoutManager(getContext());
        mAdapter = new ItemAdapter();
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(layoutManager);

        swipeRefreshLayout = view.findViewById(R.id.p_s_srl);
        swipeRefreshLayout.setOnRefreshListener(this);

        Video item = new Video();
        item.setSrc("http://vjs.zencdn.net/v/oceans.mp4");
        mData.add(item);
        mData.add(item);
        mData.add(item);
        mData.add(item);
        mData.add(item);



        return view;
    }

    @Override
    public void onRefresh() {
        //数据源更新...
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
//                adapter.clear();
//                list.add("这是第11个数据");
//                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }


    class ItemViewHolder extends RecyclerView.ViewHolder{

        private VideoView videoView;
        private TextView locationText;
        private ImageView userHeadImg;
        private TextView username;
        private TextView content;
        private TextView colTitle;
        private TextView colEsp;

        private ImageButton commentBtn;
        private ImageButton shareBtn;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            videoView = itemView.findViewById(R.id.p_s_videoView);
            locationText = itemView.findViewById(R.id.p_s_l_text);
            userHeadImg = itemView.findViewById(R.id.p_s_u_headImg);
            username = itemView.findViewById(R.id.p_s_u_name);
            content = itemView.findViewById(R.id.p_s_content);
            colTitle = itemView.findViewById(R.id.p_s_c_title);
            colEsp = itemView.findViewById(R.id.p_s_c_esp);

            commentBtn = itemView.findViewById(R.id.p_s_right2);
            shareBtn = itemView.findViewById(R.id.p_s_right4);
        }
    }

    class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> implements View.OnClickListener, View.OnLongClickListener {

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mLayoutInflater.inflate(R.layout.recyclerview_play_square,parent,false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            Video item = mData.get(position);
            holder.videoView.setVideoURI(Uri.parse(item.getSrc()));
            setVideo(holder.videoView);
            holder.videoView.start();

            holder.videoView.setOnLongClickListener(this);
            holder.commentBtn.setOnClickListener(this);
            holder.shareBtn.setOnClickListener(this);


            //...

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        @SuppressLint("ClickableViewAccessibility")
        public void setVideo(VideoView videoView){
            videoView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (videoView.isPlaying()){
                        videoView.pause();
                    }else {
                        videoView.start();
                    }
                    return false;
                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mPlayer) {
                    mPlayer.start();
                    mPlayer.setLooping(true);
                }
            });

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.p_s_right1:
                    break;
                case R.id.p_s_right2:
                    new PlaySquareCommentFragment().show(getFragmentManager(),"dialog2");
                    break;
                case R.id.p_s_right3:
                    break;
                case R.id.p_s_right4:
                    new PlaySquareShareFragment().show(getFragmentManager(), "dialog4");
                    break;
                default:
                    break;
            }

        }

        @Override
        public boolean onLongClick(View v) {

            MyDialog dialog = new MyDialog(getContext(), new MyDialog.setMyDialogListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()){
                        case R.id.d_p_s_btn1:
                                //...
                            break;
                        case R.id.d_p_s_btn2:
                            break;
                        case R.id.d_p_s_btn3:
                            break;
                        default:
                            break;
                    }
                }
            });
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.show();

            return false;
        }
    }

}