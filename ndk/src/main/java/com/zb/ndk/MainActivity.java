package com.zb.ndk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.StatusBarManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.zb.ndk.acts.BaseActivity;
import com.zb.ndk.utils.L;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar.setTitle("主标题");
        actionBar.setSubtitle("副标题");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

//        //更换navigation up 按钮
//        actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher_round);
        //设置logo
        actionBar.setLogo(android.R.mipmap.sym_def_app_icon);
        actionBar.setDisplayUseLogoEnabled(true);

        addTab();

        getWindow().setStatusBarColor(0xffff0000);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("a")
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add("b")
                .setIcon(R.mipmap.ic_launcher_round)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        L.e("onOptionsItemSelected:" + item);

        return true;
//        return super.onOptionsItemSelected(item);
    }


    void addTab() {
        //增加actionbar 下面的tab按钮
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText("Tab 1").setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                Toast.makeText(MainActivity.this, "Tab 1 select", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) { }
            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) { }
        }));
        actionBar.addTab(actionBar.newTab().setText("Tab 2").setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                Toast.makeText(MainActivity.this, "Tab 2 select",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) { }
            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) { }
        }));
    }
}