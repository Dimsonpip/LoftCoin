package com.dimsonpip.loftcoin.ui.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.dimsonpip.loftcoin.R;
import com.dimsonpip.loftcoin.ui.main.MainActivity;
import com.dimsonpip.loftcoin.ui.welcome.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler();
    private Runnable goNext;

    private SharedPreferences pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        if (pref.getBoolean(WelcomeActivity.KEY_SHOW_WELCOME, true)) {
            goNext = () -> {
                startActivity(new Intent(this, WelcomeActivity.class));
            };
        } else {
            goNext = () -> {
                startActivity(new Intent(this, MainActivity.class));
            };
        }
        handler.postDelayed(goNext, 1500);
    }

    @Override
    protected void onStop() {
        handler.removeCallbacks(goNext);
        super.onStop();
    }
}
