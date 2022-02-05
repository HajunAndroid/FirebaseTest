package com.exmaple.firebasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.amplitude.api.Amplitude;

public class MainActivity2 extends AppCompatActivity {
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Amplitude.getInstance().initialize(this, "fde7681adf0941e90df3f34a236b3680").enableForegroundTracking(getApplication());
        Amplitude.getInstance().logEvent("Hello Amplitude");
    }

    public void btn(View view){
        Amplitude.getInstance().logEvent("Hello Amplitude "+i);
        i++;
    }
}