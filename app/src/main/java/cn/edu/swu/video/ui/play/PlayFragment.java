package cn.edu.swu.video.ui.play;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import cn.edu.swu.video.R;


public class PlayFragment extends Fragment {

    ScreenSlidePagerAdapter mCollectionAdapter;
    ViewPager2 viewPager;
    List<Fragment> mFragmentList = new ArrayList<Fragment>();
    String[] mTitle = {"关注","重庆","广场"};

    public ImageButton camBtn;
    public LinearLayout linear;
    public ImageButton searchBtn;
    public TabLayout tabLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);

        camBtn = view.findViewById(R.id.p_camera);
        linear = view.findViewById(R.id.p_btm_visibility);
        tabLayout = view.findViewById(R.id.per_tab_layout);
        searchBtn = view.findViewById(R.id.p_searchBtn);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentList.add(new PlayFocusFragment());
        mFragmentList.add(new PlayCityFragment());
        mFragmentList.add(new PlaySquareFragment(this));

        mCollectionAdapter = new ScreenSlidePagerAdapter(this, mFragmentList);
        viewPager = view.findViewById(R.id.play_pager);

        viewPager.setUserInputEnabled(false);

        viewPager.setAdapter(mCollectionAdapter);

        viewPager.setCurrentItem(2,false);    //修改默认显示,去除动画

        TabLayout tabLayout = view.findViewById(R.id.play_tab_layout);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(mTitle[position]);
            }
        }
        ).attach();
    }

    public static class ScreenSlidePagerAdapter extends FragmentStateAdapter {
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
            return 3;
        }
    }

}