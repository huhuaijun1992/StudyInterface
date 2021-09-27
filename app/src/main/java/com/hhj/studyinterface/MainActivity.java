package com.hhj.studyinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtils.inject(this);
    }

    @Onclick(value = {R.id.btn_hello})
    void onClik(View view) {
        Toast.makeText(MainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
    }
}