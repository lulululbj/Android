package luyao.android.ui;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TouchDelegateComposite extends TouchDelegate {
    private static final Rect USELESS_RECT = new Rect();
    private final List<TouchDelegate> mDelegates = new ArrayList<TouchDelegate>(8);
    public TouchDelegateComposite(@NonNull View view) {
        super(USELESS_RECT, view);
    }
    public void addDelegate(@NonNull TouchDelegate delegate) {
        mDelegates.add(delegate);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean res = false;
        float x = event.getX();
        float y = event.getY();
        for (TouchDelegate delegate : mDelegates) {
            event.setLocation(x, y);
            res = delegate.onTouchEvent(event) || res;
        }
        return res;
    }
}
