package cn.edu.swu.video.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

import cn.edu.swu.video.R;


public class LoginIndexFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Timer timer=new Timer();
        TimerTask tast = new TimerTask() {
            @Override
            public void run(){
                Navigation.findNavController(getView()).navigate(R.id.action_loginIndexFragment_to_phoneLoginFragment);
            }
        };
        timer.schedule(tast,2000);



        return inflater.inflate(R.layout.fragment_login_index, container, false);
    }
}