package cn.edu.swu.video;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    PlayFragment mPlayFragment;
    PersonFragment mPersonFragment;
    BottomNavigationView mBtmNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {


        mPlayFragment = new PlayFragment();

        mPersonFragment = new PersonFragment();
        mBtmNav = findViewById(R.id.btm_nav_bar);

        replaceFragment(mPlayFragment);

        mBtmNav.setOnNavigationItemSelectedListener((menuItem)->{
            switch (menuItem.getItemId()){
                case R.id.btm_nav_item1:
                    replaceFragment(mPlayFragment);
                    break;
                case R.id.btm_nav_item2:
                    replaceFragment(new PlayCityCitypickerFragment());
                    break;
//                case R.id.btm_nav_item3:
//                    break;
                case R.id.btm_nav_item4:
                    replaceFragment(mPersonFragment);
                    break;
                default:
                    break;
            }

            return true;
        });

    }

    public void  replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_activity_content,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }





}