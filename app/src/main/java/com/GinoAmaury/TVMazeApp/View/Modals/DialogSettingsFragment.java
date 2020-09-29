package com.GinoAmaury.TVMazeApp.View.Modals;



import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.GinoAmaury.TVMazeApp.Interfaces.Settings.ISettingsView;
import com.GinoAmaury.TVMazeApp.Presenter.SettingsPresenter;
import com.GinoAmaury.TVMazeApp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.GinoAmaury.TVMazeApp.Util.Utility.showSnackbarTopMsg;

public class DialogSettingsFragment extends DialogFragment implements ISettingsView,View.OnClickListener {

    @BindView(R.id.input_pwd_settings)
    TextInputEditText input;
    @BindView(R.id.passwordLayout)
    TextInputLayout passwordLayout;
    @BindView(R.id.btn_setPassword)
    MaterialButton setPassword;

    private SettingsPresenter presenter;
    private View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_settings_fragment, null);
        ButterKnife.bind(this,view);
        presenter = new SettingsPresenter(this);
        setPassword.setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_setPassword:
                String password = input.getText().toString();
                if(validateFormSettings(v,password)){
                    view = v;
                    presenter.setPassword(getContext(),password);
                }
                break;
            default:
                break;
        }
    }

    private boolean validateFormSettings (View v, String passwordValue){
        if(passwordValue.isEmpty()){
            passwordLayout.setError(getResources().getString(R.string.noPassword));
            showSnackbarTopMsg(v,getContext(),getResources().getString(R.string.noPassword));
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void showResult(boolean result) {
        if(result){
            showSnackbarTopMsg(view,getContext(),getResources().getString(R.string.ok_message_pass));
        }else{
            showSnackbarTopMsg(view,getContext(),getResources().getString(R.string.bad_message_pass));
        }
    }
}
