package cn.edu.swu.video.ui.play;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.edu.swu.video.R;
import cn.edu.swu.video.javaBean.Video;
import cn.edu.swu.video.view.MyDialog;
import cn.edu.swu.video.view.PagerLayoutManager;

public class PlaySquareFragment extends Fragment{


    PlayFragment mParentFragment;

    RecyclerView mRecyclerview;
    LayoutInflater mLayoutInflater;
    ItemAdapter mAdapter;
    List<Video> mData = new ArrayList<>();
    List<Video> mData2 = new ArrayList<>();
    View mView;

    ItemViewHolder mItemViewHolder;

    RecyclerView mRecyclerview2;

    PlaySquareFragment(PlayFragment t){
        mParentFragment = t;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_play_square, container, false);

        mRecyclerview = mView.findViewById(R.id.p_s_rv);
        mLayoutInflater = getLayoutInflater();
        PagerLayoutManager layoutManager = new PagerLayoutManager(getContext());
        mAdapter = new ItemAdapter();
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(layoutManager);

        mRecyclerview2 = mView.findViewById(R.id.p_s_rrv);
        mRecyclerview2.setAdapter(new RightAdapter());
        mRecyclerview2.setLayoutManager(new LinearLayoutManager(getContext()));


        Video item = new Video();
        item.setSrc("https://vd2.bdstatic.com/mda-jjvrrnwcmyag15tg/mda-jjvrrnwcmyag15tg.mp4?v_from_s=hkapp-haokan-suzhou&auth_key=1626934803-0-0-f96d79198ce6aaedfc5a11c51222260c&bcevod_channel=searchbox_feed&pd=1&pt=3&abtest=3000156_3");
        mData.add(item);
        mData.add(item);
        mData.add(item);
        mData.add(item);
        mData.add(item);

        mData2.add(item);
        mData2.add(item);
        mData2.add(item);
        mData2.add(item);
        mData2.add(item);
        mData2.add(item);
        mData2.add(item);

        reFresh(mView);

        slide();

        return mView;
    }


    private void slide() {
        DrawerLayout drawerLayout = mView.findViewById(R.id.dl);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                View content = drawerLayout.getChildAt(0);
                View menu = drawerView;
                float scale = 1-slideOffset;//1~0
                float rightScale = (float) (1-0.3*scale);
                float leftScale = (float) (0.85+0.15*scale);//0.7~1
              //  menu.setScaleX(rightScale);//1~0.7
              //  menu.setScaleY(rightScale);//1~0.7

                content.setScaleX(leftScale);
                content.setScaleY(leftScale);
                content.setTranslationX(-menu.getMeasuredWidth()*slideOffset);//0~width

                Log.d("scale", String.valueOf(scale));


                mItemViewHolder.shareBtn.setAlpha(scale);
                mItemViewHolder.likeBtn.setAlpha(scale);
                mItemViewHolder.collectBtn.setAlpha(scale);
                mItemViewHolder.commentBtn.setAlpha(scale);

                mParentFragment.camBtn.setAlpha(scale);
                mParentFragment.linear.setVisibility(View.VISIBLE);
                mParentFragment.linear.setAlpha(1-scale);

                mParentFragment.searchBtn.setAlpha((float) (0.5+scale/2));

                mItemViewHolder.locationText.setAlpha(scale);
                mItemViewHolder.userHeadImg.setAlpha(scale);
                mItemViewHolder.username.setAlpha(scale);
                mItemViewHolder.content.setAlpha(scale);
                mItemViewHolder.colTitle.setAlpha(scale);
                mItemViewHolder.colEsp.setAlpha(scale);

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                Log.d("silde","begin");
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


    }

    private void reFresh(View view) {
        RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.p_s_refreshLayout);
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
        private ImageButton likeBtn;
        private ImageButton collectBtn;

        private DrawerLayout drawerLayout;

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
            likeBtn = itemView.findViewById(R.id.p_s_right1);
            collectBtn = itemView.findViewById(R.id.p_s_right3);

            drawerLayout = itemView.findViewById(R.id.dl);
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
            mItemViewHolder = holder;

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
//            MediaController localMediaController = new MediaController(getContext());
//            videoView.setMediaController(localMediaController);
            videoView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (videoView.isPlaying()){
                        videoView.pause();
                        mView.findViewById(R.id.p_s_pause).setVisibility(View.VISIBLE);
                    }else {
                        videoView.start();
                        mView.findViewById(R.id.p_s_pause).setVisibility(View.INVISIBLE);
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
                    new PlaySquareCommentFragment().show(getChildFragmentManager(),"dialog2");
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


    class RightViewHolder extends RecyclerView.ViewHolder{
        private VideoView videoView2;
        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView2 = itemView.findViewById(R.id.p_s_rvideo);

        }
    }

    class RightAdapter extends RecyclerView.Adapter<RightViewHolder>{


        @NonNull
        @Override
        public RightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mLayoutInflater.inflate(R.layout.recyclerview_play_square_right,parent,false);

            return new RightViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RightViewHolder holder, int position) {
            Video item = mData2.get(position);
            holder.videoView2.setVideoURI(Uri.parse(item.getSrc()));
        }

        @Override
        public int getItemCount() {
            return mData2.size();
        }
    }


}