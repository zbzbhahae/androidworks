package com.zb.review.acts;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

import com.zb.review.R;
import com.zb.review.utils.T;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;

public class PopupActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.popup_menu_button)
    Button menuBtn;
    @BindView(R.id.popup_window_button)
    Button windowBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        menuBtn.setOnClickListener(this);
        windowBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.popup_menu_button:
                showPopupMenu(v);
                break;
            case R.id.popup_window_button:
                showPopupWindow(v);
                break;
        }
    }

    private PopupMenu menu;
    private Context context = PopupActivity.this;
    void showPopupMenu(View v) {
        if(null == menu) {
            menu = new PopupMenu(this, v);
            MenuInflater inflater = menu.getMenuInflater();
            inflater.inflate(R.menu.navigation_bar_bottom, menu.getMenu());
            menu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                @Override
                public void onDismiss(PopupMenu menu) {
                    T.t(context, "popupMenu->onDismiss");
                }
            });
            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    T.t(context, "popupMenu->onMenuItemClick");
                    return false;
                }
            });
        }
        menu.show();
    }

    private PopupWindow window;
    void  showPopupWindow(View v) {
        if(null == window) {

            View content = View.inflate(this, R.layout.popup_window, null);

            window = new PopupWindow(content, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
            window.setOutsideTouchable(true);
            //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
            window.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            //防止点击外部不dismiss
            window.setBackgroundDrawable(new ColorDrawable());
            Button b = content.findViewById(R.id.popup_button);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T.t(context, "popupwindow中的按钮点击了");
                    window.dismiss();
                }
            });
            window.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    T.t(context, "popupwindow->onDismiss");
                    setWindowAlpha(1);
                }
            });
        }
        window.showAsDropDown(v);
//        window.showAtLocation(v, Gravity.NO_GRAVITY, (int)v.getX(), (int)v.getY());
        setWindowAlpha(0.5f);
    }

    void setWindowAlpha(float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = alpha;
        getWindow().setAttributes(lp);
    }
}
