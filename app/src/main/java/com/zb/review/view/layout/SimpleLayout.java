package com.zb.review.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zb.review.utils.P;

import java.util.ArrayList;

public class SimpleLayout extends ViewGroup {
    public SimpleLayout(Context context) {
        this(context, null);
    }

    public SimpleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SimpleLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //处理自定义在style中的属性
    }

    private ArrayList<View> matchViews = new ArrayList<>();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        int maxHeight = 0;
        int usedHeight = 0;
        int maxWidth = 0;
        int selfWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int selfWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int selfHeightSize = MeasureSpec.getSize(heightMeasureSpec);

        matchViews.clear();


        if(childCount > 0) {
            for (int i = 0; i <= childCount; i++) {
                View child = getChildAt(i);
                if(null == child)
                    break;
                LayoutParams params = child.getLayoutParams();
                int childWidthSpec;
                int childHeightSpec;
                switch (params.width) {
                    case LayoutParams.MATCH_PARENT:
                        if (selfWidthMode == MeasureSpec.EXACTLY || selfWidthMode == MeasureSpec.AT_MOST) {
                            widthMeasureSpec = MeasureSpec.makeMeasureSpec(selfWidthSize, MeasureSpec.EXACTLY);
                        } else {
                            widthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                        }
                        break;
                    case LayoutParams.WRAP_CONTENT:
                        if (selfWidthMode == MeasureSpec.EXACTLY || selfWidthMode == MeasureSpec.AT_MOST) {
                            widthMeasureSpec = MeasureSpec.makeMeasureSpec(selfWidthSize, MeasureSpec.AT_MOST);
                        } else {
                            widthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                        }
                        break;
                    default:
                        widthMeasureSpec = MeasureSpec.makeMeasureSpec(params.width, MeasureSpec.EXACTLY);
                        break;
                }

                switch (params.height) {
                    case LayoutParams.MATCH_PARENT:
                        if (selfWidthMode == MeasureSpec.EXACTLY || selfWidthMode == MeasureSpec.AT_MOST) {
                            int unusedHeight = Math.max(selfHeightSize - usedHeight, 0);
                            heightMeasureSpec = MeasureSpec.makeMeasureSpec(unusedHeight, MeasureSpec.EXACTLY);
                        } else {
                            heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                        }
                        break;
                    case LayoutParams.WRAP_CONTENT:
                        if (selfWidthMode == MeasureSpec.EXACTLY || selfWidthMode == MeasureSpec.AT_MOST) {
                            int unusedHeight = Math.max(selfHeightSize - usedHeight, 0);
                            heightMeasureSpec = MeasureSpec.makeMeasureSpec(unusedHeight, MeasureSpec.AT_MOST);
                        } else {
                            heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                        }
                        break;
                    default:
                        heightMeasureSpec = MeasureSpec.makeMeasureSpec(params.height, MeasureSpec.EXACTLY);
                        break;
                }
                child.measure(widthMeasureSpec, heightMeasureSpec);
                if(params.width == LayoutParams.MATCH_PARENT) {
                    matchViews.add(child);
                } else {
                    maxWidth = Math.max(child.getMeasuredWidth(), maxWidth);
                }

                usedHeight += child.getMeasuredHeight();


            }
        }

        if(matchViews.size() > 0) {
            if(maxWidth > 0) {
                usedHeight = 0;
                for (int i = 0; i <= childCount; i++) {
                    View child = getChildAt(i);
                    if(null == child)
                        break;
                    LayoutParams params = child.getLayoutParams();
                    int childWidthSpec;
                    int childHeightSpec;
                    switch (params.width) {
                        case LayoutParams.MATCH_PARENT:
                            widthMeasureSpec = MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.EXACTLY);
                            break;
                        case LayoutParams.WRAP_CONTENT:
                            if (selfWidthMode == MeasureSpec.EXACTLY || selfWidthMode == MeasureSpec.AT_MOST) {
                                widthMeasureSpec = MeasureSpec.makeMeasureSpec(selfWidthSize, MeasureSpec.AT_MOST);
                            } else {
                                widthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                            }
                            break;
                        default:
                            widthMeasureSpec = MeasureSpec.makeMeasureSpec(params.width, MeasureSpec.EXACTLY);
                            break;
                    }

                    switch (params.height) {
                        case LayoutParams.MATCH_PARENT:
                            if (selfWidthMode == MeasureSpec.EXACTLY || selfWidthMode == MeasureSpec.AT_MOST) {
                                int unusedHeight = Math.max(selfHeightSize - usedHeight, 0);
                                heightMeasureSpec = MeasureSpec.makeMeasureSpec(unusedHeight, MeasureSpec.EXACTLY);
                            } else {
                                heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                            }
                            break;
                        case LayoutParams.WRAP_CONTENT:
                            if (selfWidthMode == MeasureSpec.EXACTLY || selfWidthMode == MeasureSpec.AT_MOST) {
                                int unusedHeight = Math.max(selfHeightSize - usedHeight, 0);
                                heightMeasureSpec = MeasureSpec.makeMeasureSpec(unusedHeight, MeasureSpec.AT_MOST);
                            } else {
                                heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                            }
                            break;
                        default:
                            heightMeasureSpec = MeasureSpec.makeMeasureSpec(params.height, MeasureSpec.EXACTLY);
                            break;
                    }
                    child.measure(widthMeasureSpec, heightMeasureSpec);

                    usedHeight += child.getMeasuredHeight();


                }
                //需要对所有子view重新测量一遍 防止宽度是match_parent的子view宽度改变导致的高度改变
//                for (int i = 0; i < matchViews.size(); i++) {
//                    View child = matchViews.get(i);
//                    child.measure(MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.EXACTLY), child.getMeasuredHeightAndState());
//                }
            } else {
                maxWidth = matchViews.get(0).getMeasuredWidth();
            }
            matchViews.clear();
        }

        int measuredWidth = 0;
        int measuredHeight = 0;
        //测量自己
        if(selfWidthMode == MeasureSpec.EXACTLY) {
            measuredWidth = selfWidthSize;
        } else if(selfWidthMode == MeasureSpec.AT_MOST) {
            measuredWidth = Math.min(maxWidth, selfWidthSize);
        } else {
            measuredWidth = maxWidth;
        }

        if(selfHeightMode == MeasureSpec.EXACTLY) {
            measuredHeight = selfHeightSize;
        } else if(selfHeightMode == MeasureSpec.AT_MOST) {
            measuredHeight = Math.min(usedHeight, selfHeightSize);
        } else {
            measuredHeight = usedHeight;
        }

        P.p("given width height :" + selfWidthSize + " x " + selfHeightSize + "  and measured out : " + measuredWidth + " x " + measuredHeight);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int heightAdd = 0;
        for(int i=0; i<childCount; i++) {
            View child = getChildAt(i);
            child.layout(0, heightAdd, child.getMeasuredWidth(), child.getMeasuredHeight() + heightAdd);
            heightAdd += child.getMeasuredHeight();
        }
    }
}
