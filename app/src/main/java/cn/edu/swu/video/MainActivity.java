package cn.edu.swu.video;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity {

    BottomNavigationBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        bottomBar = (BottomNavigationBar) findViewById(R.id.bottom_bar);
        bottomBar.addItem(new BottomNavigationItem(R.drawable.ic_baseline_begin,""))
                .addItem(new BottomNavigationItem(R.drawable.ic_baseline_add_location,""))
                .addItem(new BottomNavigationItem(R.drawable.ic_baseline_message,""))
                .addItem(new BottomNavigationItem(R.drawable.ic_baseline_person,""))
                .initialise();
    }










}