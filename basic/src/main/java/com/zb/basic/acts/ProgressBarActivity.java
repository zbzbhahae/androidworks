package com.zb.basic.acts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.os.Bundle;
import android.view.View;

import com.zb.basic.R;

public class ProgressBarActivity extends AppCompatActivity {

    private ContentLoadingProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        progressBar = findViewById(R.id.content_progressbar);


    }

    public void onClick(View view) {
        if(progressBar.isShown())
            progressBar.hide();
        else
            progressBar.show();
    }
}