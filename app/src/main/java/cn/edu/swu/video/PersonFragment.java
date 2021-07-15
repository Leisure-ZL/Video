package cn.edu.swu.video;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class PersonFragment extends Fragment {

    ScreenSlidePagerAdapter mCollectionAdapter;
    ViewPager2 viewPager;
    List<Fragment> mFragmentList = new ArrayList<Fragment>();
    String[] mTitle = {"作品","赞过","收藏","私密"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_person, container, false);


        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mFragmentList.add(new PersonItem1Fragment());
        mFragmentList.add(new PersonItem2Fragment());
        mFragmentList.add(new PersonItem2Fragment());
        mFragmentList.add(new PersonItem2Fragment());

        mCollectionAdapter = new ScreenSlidePagerAdapter(this,mFragmentList);
        viewPager = view.findViewById(R.id.per_pager);
        viewPager.setAdapter(mCollectionAdapter);

        TabLayout tabLayout = view.findViewById(R.id.per_tab_layout);
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
            return 4;
        }
    }


}
