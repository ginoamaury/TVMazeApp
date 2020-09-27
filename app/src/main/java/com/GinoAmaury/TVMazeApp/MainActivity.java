package com.GinoAmaury.TVMazeApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.GinoAmaury.TVMazeApp.Interfaces.Login.ILoginView;
import com.GinoAmaury.TVMazeApp.Presenter.LoginPresenter;
import com.GinoAmaury.TVMazeApp.View.DashboardActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.GinoAmaury.TVMazeApp.Util.Utility.getPreference;
import static com.GinoAmaury.TVMazeApp.Util.Utility.goToNextActivityCleanStack;
import static com.GinoAmaury.TVMazeApp.Util.Utility.showSnackbarTopMsg;

public class MainActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    private LoginPresenter presenter;
    @BindView(R.id.input_pwd_login)
    TextInputEditText input;
    @BindView(R.id.passwordLayout)
    TextInputLayout passwordLayout;
    @BindView(R.id.btn_login)
    MaterialButton login;
    @BindView(R.id.fingerPrintImage)
    ImageView fingerPrint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getPreference(this,getString(R.string.dataSesion),getString(R.string.passwordSesion)).equals(getString(R.string.noData))){
            goToNextActivityCleanStack(this,DashboardActivity.class,true,null);
        }else{
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
            presenter = new LoginPresenter(this);
            login.setOnClickListener(this);
            fingerPrint.setOnClickListener(this);
        }
    }

    private void loginWithFingerprint (){
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Executor executor = ContextCompat.getMainExecutor(this);
                BiometricPrompt biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        showSnackbarTopMsg(getCurrentFocus(),getApplicationContext(),getResources().getString(R.string.errorAuth) + errString);
                    }

                    @Override
                    public void onAuthenticationSucceeded(
                            @NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        goToNextActivityCleanStack(MainActivity.this,DashboardActivity.class,true,null);
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        showSnackbarTopMsg(getCurrentFocus(),getApplicationContext(),getResources().getString(R.string.errorAuthFailed));
                    }
                });

                BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                        .setTitle(getResources().getString(R.string.biometricTitle))
                        .setSubtitle(getResources().getString(R.string.biometricSubtile))
                        .setNegativeButtonText(getResources().getString(R.string.biometricNegativeButton))
                        .build();

                biometricPrompt.authenticate(promptInfo);

                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                break;
            default:
                break;
        }
    }

    private boolean validateFormLogin (View v, String passwordValue){
         if(passwordValue.isEmpty()){
             passwordLayout.setError(getResources().getString(R.string.noPassword));
             showSnackbarTopMsg(v,this,getResources().getString(R.string.noPassword));
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void showResult(boolean result) {
        if(result){
            showSnackbarTopMsg(getCurrentFocus(),this,getResources().getString(R.string.wrongPassword));
        }else{
            goToNextActivityCleanStack(this, DashboardActivity.class,true,null);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                String password = input.getText().toString();
                if(validateFormLogin(v,password)){
                    presenter.login(getApplicationContext(),password);
                }
                break;
            case R.id.fingerPrintImage:
                loginWithFingerprint();
                break;
            default:
                break;
        }
    }
}