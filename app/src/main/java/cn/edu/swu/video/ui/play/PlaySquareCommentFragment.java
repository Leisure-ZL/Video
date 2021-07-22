package cn.edu.swu.video.ui.play;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import cn.edu.swu.video.R;
import cn.edu.swu.video.view.MyVideoView;


public class PlaySquareCommentFragment extends BottomSheetDialogFragment {

    ScreenSlidePagerAdapter mCollectionAdapter;
    ViewPager2 viewPager;
    List<Fragment> mFragmentList = new ArrayList<Fragment>();
    String[] mTitle = {"热评", "最新", "私密"};


    public static PlaySquareShareFragment getInstance() {
        return new PlaySquareShareFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return new BottomSheetDialog(this.getContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onStart() {
        super.onStart();
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        dialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        FrameLayout bottomSheet = dialog.getDelegate().findViewById(R.id.design_bottom_sheet);

        //动态改变视频大小
        Fragment videoFragment = getParentFragment();
        VideoView videoView = videoFragment.getActivity().findViewById(R.id.p_s_videoView);
        int heig = 1 - getPeekHeight();
        int wid = getResources().getDisplayMetrics().widthPixels / 4;
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(wid, heig);
        layoutParams1.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        videoView.setLayoutParams(layoutParams1);
//
        if (bottomSheet != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams();
            layoutParams.height = getPeekHeight();
            bottomSheet.setLayoutParams(layoutParams);
            final BottomSheetBehavior<FrameLayout> behavior = BottomSheetBehavior.from(bottomSheet);

            behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    Log.d("debug","change!");
                    RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels
                            , getResources().getDisplayMetrics().heightPixels);
                    layoutParams3.addRule(RelativeLayout.CENTER_IN_PARENT);
                    videoView.setLayoutParams(layoutParams3);
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    Log.d("debug", String.valueOf(slideOffset));
                    slideOffset = -slideOffset;
                    int newWid = (int)(wid + (((3*getResources().getDisplayMetrics().widthPixels)/4)*slideOffset));
                    int newHeig = (int)(heig + (((3*getResources().getDisplayMetrics().heightPixels)/4)*slideOffset));
                    RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(newWid, newHeig);
                    layoutParams2.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                    videoView.setLayoutParams(layoutParams2);
                }
            });
            behavior.setPeekHeight(getPeekHeight());
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    protected int getPeekHeight() {
        int peekHeight = getResources().getDisplayMetrics().heightPixels;
        return peekHeight - peekHeight / 4;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_square_comment, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mFragmentList.add(new PlaySquareCommentOneFragment());
        mFragmentList.add(new PlaySquareCommentOneFragment());
        mFragmentList.add(new PlaySquareCommentThreeFragment());

        mCollectionAdapter = new ScreenSlidePagerAdapter(this, mFragmentList);
        viewPager = view.findViewById(R.id.com_pager);
        viewPager.setAdapter(mCollectionAdapter);
        TabLayout tabLayout = view.findViewById(R.id.com_tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(mTitle[position])
        ).attach();


    }

    public class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        List<Fragment> mList;

        public ScreenSlidePagerAdapter(Fragment fragment, List<Fragment> list) {
            super(fragment);
            mList = list;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {

            return mList.get(position);
        }

        @Override
        public int getItemCount() {
            return mTitle.length;
        }
    }


}