package com.zb.basic.mvp.model;

import android.animation.ValueAnimator;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;
import com.zb.basic.acts.AlertAndPopupActivity;
import com.zb.basic.acts.ButtonActivity;
import com.zb.basic.acts.EditTestActivity;
import com.zb.basic.acts.FragActivity;
import com.zb.basic.acts.FrameAnimationActivity;
import com.zb.basic.acts.ImageViewActivity;
import com.zb.basic.acts.ListActivity;
import com.zb.basic.acts.LiveDataActivity;
import com.zb.basic.acts.NotificationActivity;
import com.zb.basic.acts.ProgressBarActivity;
import com.zb.basic.acts.RecyclerViewActivity;
import com.zb.basic.acts.ServerActivity;
import com.zb.basic.acts.SnackBarActivity;
import com.zb.basic.acts.StyleThemeActivity;
import com.zb.basic.acts.TextViewActivity;
import com.zb.basic.acts.ToolBarActivity;
import com.zb.basic.acts.TweenAnimationActivity;
import com.zb.basic.acts.ValueAnimationActivity;
import com.zb.basic.acts.ViewPagerActivity;
import com.zb.basic.acts.ViewModelBetweenFragmentActivity;
import com.zb.basic.acts.ViewPager2FragmentActivity;
import com.zb.basic.acts.ViewTouchActivity;
import com.zb.basic.acts.WXFragmentActivity;
import com.zb.basic.acts.constraint.ConstraintSetActivity;
import com.zb.basic.acts.customview.View1Activity;
import com.zb.basic.acts.intent.IntentTestActivity;
import com.zb.basic.acts.material.CardViewActivity;
import com.zb.basic.acts.material.FloatingButtonActivity;
import com.zb.basic.acts.okhttp.OkHttpActivity;
import com.zb.basic.acts.surface.SurfaceActivity;
import com.zb.basic.mvp.contract.MainContract;
import com.zb.common.acts.TextButtonActivity;

import java.util.ArrayList;
import java.util.List;

public class MainModel implements MainContract.IMainMode {

    private static List<String> data;

    private static List<Class> classData;

    static {
        initData();
    }
    private static void initData(){
        initTextData();
        initClassData();
    }

    private static void initClassData() {
        classData = new ArrayList<>();
        classData.add(TextViewActivity.class);
        classData.add(ButtonActivity.class);
        classData.add(EditTestActivity.class);
        classData.add(ImageViewActivity.class);
        classData.add(ProgressBarActivity.class);
        classData.add(NotificationActivity.class);
        classData.add(ToolBarActivity.class);
        classData.add(AlertAndPopupActivity.class);
        classData.add(ListActivity.class);
        classData.add(RecyclerViewActivity.class);
        classData.add(FrameAnimationActivity.class);
        classData.add(TweenAnimationActivity.class);
        classData.add(ValueAnimationActivity.class);
        classData.add(ViewPagerActivity.class);
        classData.add(FragActivity.class);
        classData.add(ViewPager2FragmentActivity.class);
        classData.add(LiveDataActivity.class);
        classData.add(ViewModelBetweenFragmentActivity.class);
        classData.add(WXFragmentActivity.class);
        classData.add(ViewTouchActivity.class);
        classData.add(View1Activity.class);
        classData.add(IntentTestActivity.class);
        classData.add(SnackBarActivity.class);
        classData.add(SurfaceActivity.class);
        classData.add(ServerActivity.class);
        classData.add(TextButtonActivity.class);
        classData.add(ConstraintSetActivity.class);
        classData.add(CardViewActivity.class);
        classData.add(StyleThemeActivity.class);
        classData.add(FloatingButtonActivity.class);
        classData.add(OkHttpActivity.class);
    }

    private static void initTextData() {
        data = new ArrayList<>();
        data.add("TextView");
        data.add("Button");
        data.add("EditText");
        data.add("ImageView");
        data.add("ProgressBar");
        data.add("Notification");
        data.add("ToolBar");
        data.add("AlertDialog&PopupWindow");
        data.add("ListView");
        data.add("RecyclerView");
        data.add("?????????");
        data.add("????????????Tween");
        data.add("????????????ValueAnimator");
        data.add("ViewPager1");
        data.add("Fragment");
        data.add("ViewPager2???fragment");
        data.add("livedata");
        data.add("livedata???activity???fragment???????????????");
        data.add("????????????");
        data.add("Touch??????");
        data.add("?????????View");
        data.add("??????????????????");
        data.add("Snackbar");
        data.add("SurfaceView??????");
        data.add("start????????????");
        data.add("TextButton????????????");
        data.add("??????CoordinatorLayout???????????????");
        data.add("CardView");
        data.add("Style???Theme");
        data.add("FloatingActionButton");
        data.add("??????OKHttp");

    }





    @Override
    public List<String> getInitData() {
        return data;
    }

    @Override
    public Class getDestinationWithPosition(int position) {
        if(null != classData && position >= 0 && position < classData.size())
            return classData.get(position);
        return null;
    }
}
