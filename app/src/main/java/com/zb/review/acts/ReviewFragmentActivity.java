package com.zb.review.acts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zb.review.utils.P;
import com.zb.review.R;
import com.zb.review.acts.fragment.ReviewFragment;

public class ReviewFragmentActivity extends BaseActivity implements ReviewFragment.OnClickListener {

    private Fragment fragment;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_fragment);
        fragment = getSupportFragmentManager().findFragmentById(R.id.review_fragment_fragment);
        if(fragment instanceof ReviewFragment)
            ((ReviewFragment) fragment).setListener(this);
    }

    @Override
    public void onClick(Fragment fragment, View view) {
        if(null != fragment && null != view)
            P.p("The View " + view.getClass().getSimpleName() + " 's OnClickListener called in fragment: " + fragment.getClass().getSimpleName());
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        super.onBackPressed();
    }
}
