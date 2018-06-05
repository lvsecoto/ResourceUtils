package com.yjy.resourceutils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.res.ResourcesCompat;

public class ShapeDrawableUtils {

    private static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }

    public static class Builder {
        private GradientDrawable mDrawable = new GradientDrawable();

        private Context mContext;

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

        public Drawable build() {
            return mDrawable;
        }
    }
}
