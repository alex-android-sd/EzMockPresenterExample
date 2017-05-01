package com.example.asanchez.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainView {

    EditText loginIdField;
    Button loginButton;
    MainPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginIdField = (EditText) findViewById(R.id.login_field);
        loginButton = (Button) findViewById(R.id.log_in_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickLogin(loginIdField.getText().toString());
            }
        });
        MyApplication application = (MyApplication) getApplication();
        presenter = application.getPresenterProvider().getMainPresenter();
    }

    @Override
    protected void onResume()
    {
        presenter.onAttach(this);
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        presenter.onDetach();
        super.onPause();
    }

    @Override
    public void showGreen() {
        startActivity(new Intent(getApplicationContext(), GreenActivity.class));
    }

    @Override
    public void showRed() {
        startActivity(new Intent(getApplicationContext(), RedActivity.class));
    }
}
