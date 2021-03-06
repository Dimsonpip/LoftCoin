package com.dimsonpip.loftcoin.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import com.dimsonpip.loftcoin.widget.CircleIndicator;

import com.dimsonpip.loftcoin.databinding.ActivityWelcomeBinding;
import com.dimsonpip.loftcoin.ui.main.MainActivity;

public class WelcomeActivity extends AppCompatActivity {

    public static final String KEY_SHOW_WELCOME = "show_welcome";

    private ActivityWelcomeBinding binding;

    private SnapHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recycler.setLayoutManager(new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL, false));
        binding.recycler.setAdapter(new WelcomeAdapter());
        binding.recycler.addItemDecoration(new CircleIndicator(this));
        binding.recycler.setHasFixedSize(true);
        helper = new PagerSnapHelper();
        helper.attachToRecyclerView(binding.recycler);
        binding.btnStart.setOnClickListener((v) -> {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                    .putBoolean(KEY_SHOW_WELCOME, false)
                    .apply();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        helper.attachToRecyclerView(null);
        binding.recycler.setAdapter(null);
        super.onDestroy();
    }
}
