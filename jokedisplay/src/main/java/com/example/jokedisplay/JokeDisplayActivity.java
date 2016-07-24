package com.example.jokedisplay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static final String JOKE_TEXT = "joke_text";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        Bundle extras = getIntent().getExtras();

        if (extras!=null && extras.containsKey(JOKE_TEXT)) {
            ((TextView)findViewById(R.id.joke_text)).setText(extras.getString(JOKE_TEXT, ""));
        }

    }
}
