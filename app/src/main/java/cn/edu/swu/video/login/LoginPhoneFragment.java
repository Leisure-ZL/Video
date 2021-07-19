package cn.edu.swu.video.login;

import android.Manifest;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.swu.video.R;


public class LoginPhoneFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login_phone, container, false);
        view.findViewById(R.id.get_ver_btn).setOnClickListener(this);
        view.findViewById(R.id.l_p_toUser).setOnClickListener(this);
        view.findViewById(R.id.l_p_toQuick).setOnClickListener(this);


        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);


        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get_ver_btn:
                Navigation.findNavController(getView()).navigate(R.id.action_phoneLoginFragment_to_verifyLoginFragment);
                break;
            case R.id.l_p_toUser:
                Navigation.findNavController(getView()).navigate(R.id.action_phoneLoginFragment_to_loginUsernameFragment);
                break;
            case R.id.l_p_toQuick:
                Navigation.findNavController(getView()).navigate(R.id.action_phoneLoginFragment_to_loginQuickFragment);
                break;
            default:
                break;
        }


    }
}