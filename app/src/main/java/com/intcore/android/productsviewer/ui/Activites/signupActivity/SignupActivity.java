package com.intcore.android.productsviewer.ui.Activites.signupActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.intcore.android.productsviewer.R;
import com.intcore.android.productsviewer.data.user.SignupBody;
import com.intcore.android.productsviewer.data.user.User;
import com.intcore.android.productsviewer.presenters.signupPresenter.SignupPresenter;
import com.intcore.android.productsviewer.presenters.signupPresenter.SignupView;
import com.intcore.android.productsviewer.ui.Activites.activateAccountActivity.ActivateAccountActivity;
import com.intcore.android.productsviewer.ui.Activites.loginActivity.LoginActivity;

public class SignupActivity extends AppCompatActivity implements SignupView {
    private static final String TAG = "SignupActivity";


    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mPhoneNumberEditText;
    private EditText mPasswordEditText;
    private EditText mPasswordEditTextSceond;
    private Button mSignupButton;
    private TextView mGoToLoginTextView;

    private SignupPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mPresenter = new SignupPresenter(this);

        mNameEditText = findViewById(R.id.name_edit_text_signup);
        mEmailEditText = findViewById(R.id.email_edit_text_signup);
        mPhoneNumberEditText = findViewById(R.id.phone_number_edit_text_signup);
        mPasswordEditText = findViewById(R.id.password_second_edit_Text_signup);
        mPasswordEditTextSceond = findViewById(R.id.password_second_edit_Text_signup);
        mSignupButton = findViewById(R.id.singup_button_signup);
        mGoToLoginTextView = findViewById(R.id.go_to_login_text_view_signup);


        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.startLoading(new SignupBody(
                        mNameEditText.getText().toString(),
                        mEmailEditText.getText().toString(),
                        Integer.parseInt(mPhoneNumberEditText.getText().toString()),
                        mPasswordEditText.getText().toString()
                ));
            }
        });


        mGoToLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

    }

    @Override
    public void onFinishLoading(User user) {
        if(user != null){
            Log.d(TAG, "onFinishLoading: "+user.getActivation());
            Intent i = ActivateAccountActivity.newIntent(SignupActivity.this, user.getApiToken());
            startActivity(i);
        }
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(SignupActivity.this, errorMessage, Toast.LENGTH_LONG).show();
    }
}
