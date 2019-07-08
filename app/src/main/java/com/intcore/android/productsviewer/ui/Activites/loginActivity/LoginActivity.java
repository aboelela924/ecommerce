package com.intcore.android.productsviewer.ui.Activites.loginActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.intcore.android.productsviewer.R;
import com.intcore.android.productsviewer.data.cache.UserData;
import com.intcore.android.productsviewer.data.user.LoginBody;
import com.intcore.android.productsviewer.data.user.User;
import com.intcore.android.productsviewer.presenters.loginPresenter.LoginPresenter;
import com.intcore.android.productsviewer.presenters.loginPresenter.LoginView;
import com.intcore.android.productsviewer.ui.Activites.MainActivity.MainActivity;
import com.intcore.android.productsviewer.ui.Activites.signupActivity.SignupActivity;
import com.wang.avi.AVLoadingIndicatorView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mSigninButton;
    private TextView mGoToSignupTextView;
    private LoginPresenter mPresenter;
    private AVLoadingIndicatorView mAVLoadingIndicatorView;
    private  UserData mUserData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailEditText = findViewById(R.id.email_text_view);
        mPasswordEditText = findViewById(R.id.password_text_view);
        mSigninButton = findViewById(R.id.login_button);
        mGoToSignupTextView = findViewById(R.id.create_account_text_view);
        mAVLoadingIndicatorView = findViewById(R.id.loading_indicator);
        mUserData = UserData.getInstance();


        mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick();
            }
        });

        mGoToSignupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        mPresenter = new LoginPresenter(this);


        if(!mUserData.getApiToken().equals("") && mUserData.getApiToken() != null){
            startAnim();
            mPresenter.login(mUserData.getApiToken());
        }

        if (!this.isNetworkAvailable()) {
            Intent i = MainActivity.newIntent(this, null);
            startActivity(i);
        }






    }


    public void onButtonClick() {
        startAnim();
        mPresenter.login(new LoginBody(
                mEmailEditText.getText().toString(),
                mPasswordEditText.getText().toString(),
                121212121,
                "andriod"
        ));
    }

    @Override
    public void showError(String errorMessage) {
        stopAnim();
        Toast.makeText(LoginActivity.this,
                errorMessage,
                Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void finishedLoading(User user) {
        stopAnim();
        mUserData.setToken(user.getApiToken());
        Intent i = MainActivity.newIntent(this, user);
        startActivity(i);
        finish();
    }

    void startAnim(){
        mAVLoadingIndicatorView.show();
        mAVLoadingIndicatorView.setVisibility(View.VISIBLE);
    }

    void stopAnim(){
        mAVLoadingIndicatorView.hide();
        mAVLoadingIndicatorView.setVisibility(View.GONE);
        // or avi.smoothToHide();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
