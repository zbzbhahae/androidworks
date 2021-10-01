package com.zb.basic.acts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.zb.basic.R;
import com.zb.common.utils.P;
import com.zb.common.utils.T;

public class AlertAndPopupActivity extends BaseActivity {
    private Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_and_popup);

        createHandler();
    }

    private void createHandler() {
        h = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case 100:
                        T.t(AlertAndPopupActivity.this, "收到了ProgressDialog的Dismiss Message");
                        break;
                }

            }
        };
    }

    public void onClick(View view) {
//        showPopupWindow(view);

        showProgressDialog();

    }

    private void showProgressDialog() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("这是progressdialog的title");
        pd.setMessage("这是ProgressDialog的message");
        pd.setIcon(R.mipmap.ic_launcher);
        //是否可以按返回取消
        pd.setCancelable(true);
        //设置进度条是否明确
        pd.setIndeterminate(false);
        //进度条风格，是旋转的还是横向的
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //如果是横向的，设置进度条最大值
        pd.setMax(100);
        //增加进度条进度
        pd.incrementProgressBy(3);
        //设置进度条进度
        pd.setProgress(11);
        //弹窗范围外是否可以点击取消
        pd.setCanceledOnTouchOutside(false);
        pd.setButton(ProgressDialog.BUTTON_POSITIVE, "POSITIVE BUTTON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                T.t(AlertAndPopupActivity.this, "点击了POSITIVE Button");
            }
        });
        pd.setButton(ProgressDialog.BUTTON_NEGATIVE, "NEGATIVE BUTTON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                T.t(AlertAndPopupActivity.this, "点击了NEGATIVE Button");
            }
        });
        pd.setButton(ProgressDialog.BUTTON_NEUTRAL, "NEUTRAL BUTTON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                T.t(AlertAndPopupActivity.this, "点击了NEUTRAL Button");
            }
        });
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                T.t(AlertAndPopupActivity.this, "ProgressDialog::setOnCancelListener::onCancel()");
            }
        });
        Message dismissMessage = Message.obtain(h, 100);
        pd.setDismissMessage(dismissMessage);
        pd.show();

    }

    protected void showPopupWindow(View achor) {
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_custom, null);
        Button btnHaha = popupView.findViewById(R.id.popup_button_haha);
        Button btnHouHou = popupView.findViewById(R.id.popup_button_houhou);
        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xffff00ff));

        btnHaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.t(AlertAndPopupActivity.this, "btnHaha::onClick");
                popupWindow.dismiss();
            }
        });
        btnHouHou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.t(AlertAndPopupActivity.this, "btnHouHou::onClick");
                popupWindow.dismiss();
            }
        });

        popupWindow.showAsDropDown(achor);

    }

    protected void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(LayoutInflater.from(this).inflate(R.layout.dialog_custom, null));
        builder.setIcon(R.mipmap.compose);
        builder.setTitle("标题");
        builder.setMessage("这是信息");
        builder.setCancelable(true);//如果为false，则点击外侧不可取消，返回键无效
        //由于点按按钮而取消的，不会调用onCancel监听
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                T.t(AlertAndPopupActivity.this, "PositiveButton::onClick");
            }
        });
        builder.setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                T.t(AlertAndPopupActivity.this, "NegativeButton::onClick");
            }
        });
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                T.t(AlertAndPopupActivity.this, "NeutralButton::onClick");
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                T.t(AlertAndPopupActivity.this, "OnCancelListener::onCancel");
                P.p("OnCancelListener::onCancel");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}