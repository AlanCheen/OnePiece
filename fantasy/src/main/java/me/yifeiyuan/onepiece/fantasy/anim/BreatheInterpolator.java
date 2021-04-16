package me.yifeiyuan.onepiece.fantasy.anim;

import android.animation.TimeInterpolator;


/**
 * Created by 程序亦非猿 on 2021/4/16.
 * <p>
 * Usage:
 * <pre>
 * ObjectAnimator.setInterpolator(new BreatheInterpolator());
 * </pre>
 * Ref: https://juejin.im/entry/58b4e2365c497d00560dad00
 */
public class BreatheInterpolator implements TimeInterpolator {

    @Override
    public float getInterpolation(final float input) {
        float x = 6 * input;
        float k = 1.0f / 3;
        int t = 6;
        int n = 1;
        float PI = 3.1416f;
        float output = 0;

        if (x >= ((n - 1) * t) && x < ((n - (1 - k)) * t)) {
            output = (float) (0.5 * Math.sin((PI / (k * t)) * ((x - k * t / 2) - (n - 1) * t)) + 0.5);

        } else if (x >= (n - (1 - k)) * t && x < n * t) {
            output = (float) Math.pow((0.5 * Math.sin((PI / ((1 - k) * t)) * ((x - (3 - k) * t / 2) - (n - 1) * t)) + 0.5), 2);
        }
        return output;
    }
}
