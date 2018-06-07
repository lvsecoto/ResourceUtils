package com.yjy.resourceutils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import static android.graphics.drawable.GradientDrawable.Orientation;

public class ShapeDrawableUtils {

    public static Builder builder(Context context) {
        return new Builder(context);
    }

    private static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }

    private static Orientation getOrientation(int angle) {
        angle %= 360;

        if (angle % 45 != 0) {
            throw new IllegalArgumentException("angle be a multiple of 45");
        }

        switch (angle) {
            case 0:
                return Orientation.LEFT_RIGHT;
            case 45:
                return Orientation.BL_TR;
            case 90:
                return Orientation.BOTTOM_TOP;
            case 135:
                return Orientation.BR_TL;
            case 180:
                return Orientation.RIGHT_LEFT;
            case 225:
                return Orientation.TR_BL;
            case 270:
                return Orientation.TOP_BOTTOM;
            case 315:
                return Orientation.TL_BR;
        }
        return null;
    }

    public static class Builder {
        private GradientDrawable mDrawable = new GradientDrawable();
        private Context mContext;

        private int[] mStartEndColor = new int[2];

        public Builder(Context context) {
            mContext = context;
        }

        public Builder solid(int color) {
            mDrawable.setColor(color);
            return this;
        }

        public Builder stroke(int dpWidth, int color) {
            mDrawable.setStroke(dpToPx(mContext, dpWidth), color);
            return this;
        }

        public Builder corner(int dpRadius) {
            mDrawable.setCornerRadius(dpToPx(mContext, dpRadius));
            return this;
        }

        public Builder startColor(int color) {
            mStartEndColor[0] = color;
            mDrawable.setColors(mStartEndColor);
            return this;
        }

        public Builder endColor(int color) {
            mStartEndColor[1] = color;
            mDrawable.setColors(mStartEndColor);
            return this;
        }

        public Builder angle(int angle) {
            mDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
            mDrawable.setOrientation(getOrientation(angle));
            return this;
        }

        public Drawable build() {
            return mDrawable;
        }
    }
}
