package com.example.jinxin.myapplication;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by jinxin on 15/10/30.
 */
public class TextActivityFilter extends Activity {

    TextView tv_f1;
    TextView tv_f2;
    LinearLayout ll_f1;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.filter);

        tv_f1 = (TextView) findViewById(R.id.f1);
        tv_f2 = (TextView) findViewById(R.id.f2);
        ll_f1 = (LinearLayout) findViewById(R.id.ll_f1);
        ll_f1.setVisibility(View.GONE);
        tv_f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.util.Log.w("t1", "click");

                if (ll_f1.getVisibility() == View.GONE) {
                    expand();
                } else {
                    collapse();
                }
            }
        });
    }
    private void expand() {
        //set Visible
        ll_f1.setVisibility(View.VISIBLE);
        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        ll_f1.measure(widthSpec, heightSpec);
        ValueAnimator mAnimator = slideAnimator(0, ll_f1.getMeasuredHeight());
        mAnimator.setDuration(3000);
        mAnimator.start();
    }

    private void collapse() {
        int finalHeight = ll_f1.getHeight();

        ValueAnimator mAnimator = slideAnimator(finalHeight, 0);
        mAnimator.setDuration(3000);
        mAnimator.addListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animation) {}
            public void onAnimationEnd(Animator animation) {ll_f1.setVisibility(View.GONE);}
            public  void onAnimationCancel(Animator animation) {}
            public void onAnimationRepeat(Animator animation) {}
        });
        mAnimator.start();
    }

    private ValueAnimator slideAnimator(int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = ll_f1.getLayoutParams();
                layoutParams.height = value;
                ll_f1.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }
}
