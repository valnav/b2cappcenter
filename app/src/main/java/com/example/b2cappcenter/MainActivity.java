package com.example.b2cappcenter;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.ConsoleMessage;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.auth.Auth;
import com.microsoft.appcenter.auth.SignInResult;
import com.microsoft.appcenter.utils.async.AppCenterConsumer;




public class MainActivity extends AppCompatActivity {

    public static final String AccountId = "accountId";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCenter.start(getApplication(), "25f17c16-d747-496d-bc24-33eab8846a9a",
                Analytics.class, Crashes.class);
        AppCenter.start(getApplication(), "25f17c16-d747-496d-bc24-33eab8846a9a",
                Auth.class);

    }

    public void B2CLogin(android.view.View view) {

        final Intent loggedIn = new Intent(this, LoggedInActivity.class);
        Auth.signOut();
        Auth.signIn().thenAccept(new AppCenterConsumer<SignInResult>() {
                                     @Override
                                     public void accept(SignInResult signInResult) {
                                         if (signInResult.getException() == null) {
                                             // Sign-in succeeded.
                                             String accountId = signInResult.getUserInformation().getAccountId();
                                             loggedIn.putExtra(AccountId, accountId);
                                         } else { // Do something with sign in failure.
                                             Exception signInFailureException = signInResult.getException();
                                         }
                                     }

                                 }
        );


        startActivity(loggedIn);
    }
}
