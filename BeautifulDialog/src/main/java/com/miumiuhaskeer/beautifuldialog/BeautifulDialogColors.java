package com.miumiuhaskeer.beautifuldialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorRes;

/**
 * Colors for dialog (background, border and text)
 */
public class BeautifulDialogColors {

    private final int backgroundColor;
    private final int borderAndHighlightColor;
    private final int textColor;

    private GradientDrawable cardDrawable;

    public BeautifulDialogColors(Context context, @ColorRes int backgroundRes,
                                 @ColorRes int borderAndHighlightRes, @ColorRes int textRes) {
        this(context.getColor(backgroundRes), context.getColor(borderAndHighlightRes), context.getColor(textRes));
    }

    public BeautifulDialogColors(int backgroundColor, int borderAndHighlightColor, int textColor) {
        this.backgroundColor = backgroundColor;
        this.borderAndHighlightColor = borderAndHighlightColor;
        this.textColor = textColor;

        initCardBackground();
    }

    public static BeautifulDialogColors getDefaultColors(Context context){
        int background = getColorFromStyle(context, R.attr.colorBackgroundFloating, android.R.color.white);
        int border = getColorFromStyle(context, R.attr.colorControlActivated, android.R.color.black);
        int text = getColorFromStyle(context, R.attr.titleTextColor, android.R.color.black);

        return new BeautifulDialogColors(
                background,
                border,
                text
        );
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public Drawable getBackgroundDrawable(){
        return new ColorDrawable(backgroundColor);
    }

    public int getBorderAndHighlightColor() {
        return borderAndHighlightColor;
    }

    public int getTextColor() {
        return textColor;
    }

    /**
     * Get drawable for CardView without border (with corners)
     *
     * @return drawable for CardView
     */
    public Drawable getCardDrawable() {
        return cardDrawable;
    }

    /**
     * Get drawable for CardView with border (and corners)
     *
     * @return drawable for CardView
     */
    public Drawable getCardDrawableWithBorder() {
        cardDrawable.setStroke(4, borderAndHighlightColor);

        return cardDrawable;
    }

    /**
     * Init CardView background (background color and corners)
     */
    private void initCardBackground(){
        cardDrawable = new GradientDrawable();
        cardDrawable.setShape(GradientDrawable.RECTANGLE);
        cardDrawable.setCornerRadius(25);
        cardDrawable.setColor(backgroundColor);
    }

    /**
     * Get color from attribute style with default value
     *
     * @param context which contains color
     * @param res attribute for getting color
     * @param defValue return this if res doesn't exist
     * @return color if res exist, defValue - if res doesn't exist
     */
    private static int getColorFromStyle(Context context, @AttrRes int res, @ColorRes int defValue){
        int[] attrs = {res};
        TypedArray array = context.obtainStyledAttributes(attrs);
        int colorRes = array.getResourceId(0, defValue);
        array.recycle();

        return context.getColor(colorRes);
    }
}
