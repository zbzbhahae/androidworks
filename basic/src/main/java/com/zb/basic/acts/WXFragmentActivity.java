package com.zb.basic.acts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zb.basic.R;
import com.zb.basic.adapter.ViewPager2Adapter;

public class WXFragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayoutCompat llChat, llContacts, llFind, llProfile;
    private TextView chatTxt, contactsTxt, findTxt, profileTxt;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxfragment);

        initViewPager();
        initBottomLayout();
    }

    private void initBottomLayout() {
        llChat = findViewById(R.id.wx_chat);
        llContacts = findViewById(R.id.wx_contacts);
        llFind = findViewById(R.id.wx_find);
        llProfile = findViewById(R.id.wx_profile);

        chatTxt = findViewById(R.id.wx_chat_txt);
        contactsTxt = findViewById(R.id.wx_contacts_txt);
        findTxt = findViewById(R.id.wx_find_txt);
        profileTxt = findViewById(R.id.wx_profile_txt);

        llChat.setOnClickListener(this);
        llContacts.setOnClickListener(this);
        llFind.setOnClickListener(this);
        llProfile.setOnClickListener(this);
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.wx_view_pager);
        viewPager.setAdapter(new ViewPager2Adapter(this));
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                selectPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i=0;
        switch (v.getId()) {
            case R.id.wx_chat:
                selectPage(0);
                i = 0;
                break;
            case R.id.wx_contacts:
                selectPage(1);
                i = 1;
                break;
            case R.id.wx_find:
                selectPage(2);
                i = 2;
                break;
            case R.id.wx_profile:
                selectPage(3);
                i = 3;
                break;
        }
        viewPager.setCurrentItem(i, true);
    }

    private void selectPage(int i) {
        llChat.setSelected(false);
        llContacts.setSelected(false);
        llFind.setSelected(false);
        llProfile.setSelected(false);
        chatTxt.setSelected(false);
        contactsTxt.setSelected(false);
        findTxt.setSelected(false);
        profileTxt.setSelected(false);
        switch (i) {
            case 0:
                llChat.setSelected(true);
                chatTxt.setSelected(true);
                break;
            case 1:
                llContacts.setSelected(true);
                contactsTxt.setSelected(true);
                break;
            case 2:
                llFind.setSelected(true);
                findTxt.setSelected(true);
                break;
            case 3:
                llProfile.setSelected(true);
                profileTxt.setSelected(true);
                break;
        }

    }
}