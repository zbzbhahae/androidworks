package com.zb.customview.widgets.decoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zb.customview.R;

/**
 * RecyclerView grid的分割线
 */
public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {

    private int dividerSize = 1;
    private Paint dividerPaint;
    private int dividerColor = 0xFF00FFFF;

    public GridDividerItemDecoration() {
        super();
        dividerPaint = new Paint();
        dividerPaint.setColor(dividerColor);
        dividerPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawVertical(c, parent);
        drawHorizontal(c, parent);
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin
                    + dividerSize;
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + dividerSize;
            temRect.set(left, top, right, bottom);
            c.drawRect(temRect, dividerPaint);
        }
    }

    private Rect temRect = new Rect();

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + dividerSize;

            temRect.set(left, top, right, bottom);
            c.drawRect(temRect, dividerPaint);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, dividerSize, dividerSize);
        if(isLastColum(parent.getChildLayoutPosition(view), parent)) {
            outRect.right = 0;
        }
        if(isLastRow(parent.getChildLayoutPosition(view), parent)) {
            outRect.bottom = 0;
        }
    }


    /**
     * 当前位置是否是最后一列
     * @param position
     * @param parent
     * @return
     */
    private boolean isLastColum(int position, RecyclerView parent) {
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) manager).getSpanCount();
            if((position + 1) % spanCount == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 当前位置是否是最后一行
     * @param position
     * @param parent
     * @return
     */
    private boolean isLastRow(int position, RecyclerView parent) {
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) manager).getSpanCount();
            int childCount = parent.getAdapter().getItemCount();
            if(childCount - 1 - position < spanCount) {
                return true;
            }
        }
        return false;
    }
}
