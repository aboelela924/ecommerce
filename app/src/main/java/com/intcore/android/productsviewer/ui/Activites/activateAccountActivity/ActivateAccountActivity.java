package com.intcore.android.productsviewer.ui.Activites.activateAccountActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.intcore.android.productsviewer.R;
import com.intcore.android.productsviewer.data.user.ActivationBody;
import com.intcore.android.productsviewer.data.user.User;
import com.intcore.android.productsviewer.presenters.activationPresenter.ActivationPresenter;
import com.intcore.android.productsviewer.presenters.activationPresenter.ActivationView;

public class ActivateAccountActivity extends AppCompatActivity implements ActivationView {
    private final static String TAG = "ActivateAccountActivity";
    private static final String API_TOKEN = "API_TOKEN";

    public static Intent newIntent(Context context, String apiToke){
        Intent i = new Intent(context, ActivateAccountActivity.class);
        i.putExtra(API_TOKEN,apiToke);
        return i;
    }

    private EditText mActivationCodeEditText;
    private Button mActivateButton;
    private ActivationPresenter mPresenter;
    private String apiToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_account);

        mPresenter = new ActivationPresenter(this);

        Intent i = getIntent();
        if(i != null){
            apiToken = i.getStringExtra(API_TOKEN);
        }

        mActivationCodeEditText = findViewById(R.id.activation_code_edit_text);
        mActivateButton = findViewById(R.id.activate_button);

        mActivateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.activateAccount(new ActivationBody(
                        mActivationCodeEditText.getText().toString(),
                        apiToken
                ));
            }
        });
    }


    @Override
    public void onLoadFinish(User user) {

        Log.d(TAG, "onLoadFinish: done");

    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(ActivateAccountActivity.this, errorMessage, Toast.LENGTH_LONG).show();
    }
}
