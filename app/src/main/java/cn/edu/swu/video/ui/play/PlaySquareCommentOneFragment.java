package cn.edu.swu.video.ui.play;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.swu.video.R;
import cn.edu.swu.video.javaBean.Comment;


public class PlaySquareCommentOneFragment extends Fragment {

    RecyclerView mRecyclerview;
    LayoutInflater mLayoutInflater;
    CommentAdapter mAdapter;

    List<Comment> mData = new ArrayList<>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_square_comment_one, container, false);

        Comment comment = new Comment();
        mData.add(comment);
        mData.add(comment);
        mData.add(comment);
        mData.add(comment);
        mData.add(comment);
        mData.add(comment);
        mData.add(comment);


        mRecyclerview = view.findViewById(R.id.p_s_c_o_rv);
        mAdapter = new CommentAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mLayoutInflater = getLayoutInflater();
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(manager);

        return view;
    }




    static class CommentViewHolder extends RecyclerView.ViewHolder{

        ImageView headImg;
        TextView username;
        TextView content;
        ImageButton likeBtn;
        TextView likeNum;
        TextView time;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            headImg = itemView.findViewById(R.id.p_s_c_o_headImg);
            username = itemView.findViewById(R.id.p_s_c_o_username);
            content = itemView.findViewById(R.id.p_s_c_o_content);
            likeBtn = itemView.findViewById(R.id.p_s_c_o_like);
            likeNum = itemView.findViewById(R.id.p_s_c_o_likeNum);
            time = itemView.findViewById(R.id.p_s_c_o_time);

        }
    }

    class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder>{


        @NonNull
        @Override
        public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mLayoutInflater.inflate(R.layout.recyclerview_play_square_comment_one,parent,false);
            return new CommentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
            //   User user = list.get(position);
            //    holder.mTextView.setText(user.getName());
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }




}