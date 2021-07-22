package cn.edu.swu.video.ui.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.swu.video.R;


public class LoginUsernameFragment extends Fragment implements View.OnClickListener {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_username, container, false);

        view.findViewById(R.id.l_u_toVer).setOnClickListener(this);
        view.findViewById(R.id.l_u_toReset).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.l_u_toVer:
                Navigation.findNavController(getView()).navigate(R.id.action_loginUsernameFragment_to_phoneLoginFragment);
                break;
            case R.id.l_u_toReset:
                Navigation.findNavController(getView()).navigate(R.id.action_loginUsernameFragment_to_loginResetFragment);
                break;
            default:
                break;
        }
    }
}