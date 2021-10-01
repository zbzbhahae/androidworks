package com.zb.review.acts;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.zb.review.R;
import com.zb.review.data.room.bean.Student;
import com.zb.review.utils.P;

import butterknife.BindView;
import butterknife.OnClick;

public class AnimatorActivity extends BaseActivity {

    @BindView(R.id.animator_button)
    Button button;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

    }

    @OnClick(R.id.animator_button)
    public void onClick(Button b) {
        //ArrayMap没有处理hash冲突
//        ArrayMap<Object, Object> am = new ArrayMap<>();
//        Student s = new Student();
//        am.put(s, 1);
//        s = new Student();
//        am.put(s, 2);
//        s = new Student();
//        am.put(s, 3);
//        s = new Student();
//        am.put(s, 4);
//        P.p("ArrayMap hash->size -> " + am.size());

        int i = Integer.MAX_VALUE;
        int j = Integer.MAX_VALUE;
        P.p("----------max : " + i + "   >>>1 :" + (i >>> 1) + "  add >>>: " + ((i + j) >>> 1));
        P.p("onClick   view x, y->  " + button.getX() + "  -  " + button.getY());

        useObjectAnimator();
        useObjectAnimatorWithTypeEvaluator();


    }


    void useAnimate() {
        button.animate()
                .setDuration(5000)
                .setInterpolator(new LinearInterpolator())
                .translationX(100)
                .translationY(100)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                    }
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        super.onAnimationRepeat(animation);
                    }
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                    }
                    @Override
                    public void onAnimationPause(Animator animation) {
                        super.onAnimationPause(animation);
                    }
                    @Override
                    public void onAnimationResume(Animator animation) {
                        super.onAnimationResume(animation);
                    }
                });
    }

    void useObjectAnimator() {
        /** 通过设置的属性translationX去调用setTranslationX方法*/
        ObjectAnimator oa = new ObjectAnimator();
        oa.setDuration(5000);
        oa.setInterpolator(new LinearInterpolator());
        oa.setTarget(button);
        oa.setPropertyName("translationX");
        oa.setFloatValues(10);
        oa.start();
    }

    void useObjectAnimatorWithTypeEvaluator() {
        P.p("background->" + button.getCurrentTextColor());
        ObjectAnimator oa = ObjectAnimator.ofInt(button, "TextColor", 0xFFFF0000, 0xFF00FF00);
        oa.setDuration(5000);
//        oa.setInterpolator(new LinearInterpolator());
        oa.setEvaluator(new HSVEvaluator());//HSV色彩空间更加自然
        oa.setEvaluator(new ArgbEvaluator());
        oa.start();
    }

    /** 自定义的求值器 可以返回任意对象**/
    class HSVEvaluator implements TypeEvaluator<Integer> {

        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            float[] startHsv = new float[3];
            float[] endHsv = new float[3];
            Color.colorToHSV(startValue, startHsv);
            Color.colorToHSV(endValue, endHsv);

            float[] result = new float[3];
            result[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction;
            result[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction;
            result[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction;

            return Color.HSVToColor(0xFF, result);
        }
    }
}
