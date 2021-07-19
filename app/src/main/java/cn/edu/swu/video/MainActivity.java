package cn.edu.swu.video;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import cn.edu.swu.video.person.PersonFragment;
import cn.edu.swu.video.play.PlayCityCitypickerFragment;
import cn.edu.swu.video.play.PlayFragment;

public class MainActivity extends AppCompatActivity {
    PlayFragment mPlayFragment;
    PersonFragment mPersonFragment;
    BottomNavigationView mBtmNav;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        immersion();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void immersion() {

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            //让应用主题内容占用系统状态栏的空间,注意:下面两个参数必须一起使用 stable 牢固的
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //设置状态栏颜色为透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
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