package com.example.b2cappcenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.auth.Auth;
import com.microsoft.appcenter.auth.SignInResult;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.utils.async.AppCenterConsumer;

public class LoggedInActivity extends AppCompatActivity {

    String accountId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        AppCenter.start(getApplication(), "25f17c16-d747-496d-bc24-33eab8846a9a",
                Analytics.class, Crashes.class, Auth.class);

        accountId = this.getIntent().getStringExtra(MainActivity.AccountId);
    }

    public void B2CLogout(android.view.View view) {
        Auth.signOut();
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
}
