package com.zb.basic.acts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zb.basic.R;
import com.zb.basic.fragment.BaseFragment;
import com.zb.basic.fragment.FragmentTxtBtn;
import com.zb.basic.fragment.FragmentTxtBtnSub;

public class FragActivity extends BaseActivity implements View.OnClickListener {

    private FragmentManager fManager;
    private Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);

        fManager = getSupportFragmentManager();

        btn1 = findViewById(R.id.frag_butt1);
        btn2 = findViewById(R.id.frag_butt2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    private FragmentTxtBtn f1, f2, currentF;

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(R.id.frag_butt1 == id) {
            if(null == f1)
                f1 = new FragmentTxtBtn();
            Bundle b = new Bundle();
            b.putString("message", "这是第一个fragment");
            f1.setArguments(b);
            replaceFragment(f1);
        } else if(R.id.frag_butt2 == id) {
            if(null == f2)
                f2 = new FragmentTxtBtnSub();
            Bundle b = new Bundle();
            b.putString("message", "这是第二个fragment");
            f2.setArguments(b);
            replaceFragment(f2);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void replaceFragment(FragmentTxtBtn fragment) {
        FragmentTransaction transaction = fManager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.setReorderingAllowed(true);
//        transaction.addToBackStack(null);
       transaction.commit();

    }

    private void addFragment(Fragment fragment) {
//        getFragmentManager()
        fManager.beginTransaction().add(new FragmentTxtBtn(), "1");
        fManager.beginTransaction().add(new FragmentTxtBtn(), "2");
        fManager.beginTransaction().add(new FragmentTxtBtn(), "3");
        fManager.beginTransaction().add(new FragmentTxtBtn(), "4");
    }


}