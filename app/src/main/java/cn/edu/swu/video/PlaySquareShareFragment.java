package cn.edu.swu.video;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import cn.edu.swu.video.javaBean.Function;
import cn.edu.swu.video.javaBean.User;


public class PlaySquareShareFragment extends BottomSheetDialogFragment {

    View mView;
    Context mContext;

    RecyclerView mRecyclerView1;
    RecyclerView mRecyclerView2;
    RecyclerView mRecyclerView3;

    List<User> mData1 = new ArrayList<User>();
    List<Function> mData2 = new ArrayList<Function>();
    List<Function> mData3 = new ArrayList<Function>();
    LayoutInflater mLayoutInflater;

    GeneralAdapter<User> mGeneralAdapter1;
    GeneralAdapter<Function> mGeneralAdapter2;
    GeneralAdapter<Function> mGeneralAdapter3;

    public static PlaySquareShareFragment getInstance() {
        return new PlaySquareShareFragment();
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return new BottomSheetDialog(this.getContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        dialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        FrameLayout bottomSheet = dialog.getDelegate().findViewById(R.id.design_bottom_sheet);
        if (bottomSheet != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams();
            layoutParams.height = getPeekHeight();
            bottomSheet.setLayoutParams(layoutParams);
            final BottomSheetBehavior<FrameLayout> behavior = BottomSheetBehavior.from(bottomSheet);
            behavior.setPeekHeight(getPeekHeight());
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    protected int getPeekHeight() {
        int peekHeight = getResources().getDisplayMetrics().heightPixels;
        return peekHeight / 2;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_play_square_share, container, false);
        mContext = getContext();

        initData();
        initRV();

        return mView;
    }

    private void initData(){
        User user = new User();
        Function function = new Function();
        mData1.add(user);
        mData1.add(user);
        mData1.add(user);
        mData1.add(user);
        mData1.add(user);
        mData1.add(user);

        mData2.add(function);
        mData2.add(function);
        mData2.add(function);
        mData2.add(function);
        mData2.add(function);
        mData2.add(function);
        mData2.add(function);

        mData3.add(function);
        mData3.add(function);
        mData3.add(function);
        mData3.add(function);
        mData3.add(function);
    }

    private void initRV(){
        mRecyclerView1 = mView.findViewById(R.id.p_s_s_rv1);
        mRecyclerView2 = mView.findViewById(R.id.p_s_s_rv2);
        mRecyclerView3 = mView.findViewById(R.id.p_s_s_rv3);

        mGeneralAdapter1 = new GeneralAdapter<User>(mData1);
        mGeneralAdapter2 = new GeneralAdapter<Function>(mData2);
        mGeneralAdapter3 = new GeneralAdapter<Function>(mData3);

        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager manager3 = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        mLayoutInflater = getLayoutInflater();

        mRecyclerView1.setLayoutManager(manager1);
        mRecyclerView1.setAdapter(mGeneralAdapter1);
        mRecyclerView2.setLayoutManager(manager2);
        mRecyclerView2.setAdapter(mGeneralAdapter2);
        mRecyclerView3.setLayoutManager(manager3);
        mRecyclerView3.setAdapter(mGeneralAdapter3);
    }



    static class GeneralViewHolder extends RecyclerView.ViewHolder{

        ImageView mImgView;
        TextView mTextView;
        public GeneralViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgView = itemView.findViewById(R.id.p_s_b_img);
            mTextView = itemView.findViewById(R.id.p_s_b_name);
        }
    }

    class GeneralAdapter<T> extends RecyclerView.Adapter<GeneralViewHolder>{

        private List<T> list;

        public GeneralAdapter(List<T> data){
            list = data;
        }

        @NonNull
        @Override
        public GeneralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mLayoutInflater.inflate(R.layout.recyclerview_play_square_share,parent,false);
            return new GeneralViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GeneralViewHolder holder, int position) {
         //   User user = list.get(position);
        //    holder.mTextView.setText(user.getName());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }


    }



}