package me.yifeiyuan.onepiece.fantasy.anim;

import android.animation.TypeEvaluator;


/**
 * Created by 程序亦非猿 on 2021-04-16.
 * <p>
 * <p>
 * Usage:
 * <pre>
 * final GradientDrawable drawable = (GradientDrawable) view.getBackground();
 * int start = Color.parseColor("#333333");
 * int end = Color.parseColor("#FF6D2B");
 * final ValueAnimator anim = new ValueAnimator();
 * anim.setIntValues(start, end);
 * anim.setEvaluator(ArgbEvaluator.getInstance());
 * anim.setDuration(3000);
 * anim.addUpdateListener(new AnimatorUpdateListener() {
 *     @Override
 *     public void onAnimationUpdate(final ValueAnimator animation) {
 *         //set the color
 *         drawable.setColor((Integer) anim.getAnimatedValue());
 *     }
 * });
 * anim.start();
 * </pre>
 */
public class ArgbEvaluator implements TypeEvaluator {

    private static final ArgbEvaluator sInstance = new ArgbEvaluator();

    private ArgbEvaluator() {
    }

    public static ArgbEvaluator getInstance() {
        return sInstance;
    }

    public Object evaluate(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        float startA = (float) (startInt >> 24 & 255) / 255.0F;
        float startR = (float) (startInt >> 16 & 255) / 255.0F;
        float startG = (float) (startInt >> 8 & 255) / 255.0F;
        float startB = (float) (startInt & 255) / 255.0F;
        int endInt = (Integer) endValue;
        float endA = (float) (endInt >> 24 & 255) / 255.0F;
        float endR = (float) (endInt >> 16 & 255) / 255.0F;
        float endG = (float) (endInt >> 8 & 255) / 255.0F;
        float endB = (float) (endInt & 255) / 255.0F;
        startR = (float) Math.pow((double) startR, 2.2D);
        startG = (float) Math.pow((double) startG, 2.2D);
        startB = (float) Math.pow((double) startB, 2.2D);
        endR = (float) Math.pow((double) endR, 2.2D);
        endG = (float) Math.pow((double) endG, 2.2D);
        endB = (float) Math.pow((double) endB, 2.2D);
        float a = startA + fraction * (endA - startA);
        float r = startR + fraction * (endR - startR);
        float g = startG + fraction * (endG - startG);
        float b = startB + fraction * (endB - startB);
        a *= 255.0F;
        r = (float) Math.pow((double) r, 0.45454545454545453D) * 255.0F;
        g = (float) Math.pow((double) g, 0.45454545454545453D) * 255.0F;
        b = (float) Math.pow((double) b, 0.45454545454545453D) * 255.0F;
        return Math.round(a) << 24 | Math.round(r) << 16 | Math.round(g) << 8 | Math.round(b);
    }
}