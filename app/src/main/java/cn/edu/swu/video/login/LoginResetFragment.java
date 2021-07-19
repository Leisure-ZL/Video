package cn.edu.swu.video.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.swu.video.R;


public class LoginResetFragment extends Fragment implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_reset, container, false);
        view.findViewById(R.id.l_rs_toSet).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.l_rs_toSet:
                Navigation.findNavController(getView()).navigate(R.id.action_loginResetFragment_to_loginSetFragment);
                break;
            default:
                break;
        }

    }
}