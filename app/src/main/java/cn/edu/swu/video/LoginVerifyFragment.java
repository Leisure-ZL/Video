package cn.edu.swu.video;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LoginVerifyFragment extends Fragment implements View.OnClickListener {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login_verify, container, false);
        view.findViewById(R.id.ver_re_btn).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Navigation.findNavController(getView()).navigate(R.id.action_verifyLoginFragment_to_mainActivity);
    }
}