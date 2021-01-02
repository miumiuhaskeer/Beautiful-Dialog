package com.miumiuhaskeer.beautifuldialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

public class BeautifulDialog {

    private final Context context;
    private View view;

    private final AlertDialog.Builder builder;
    private AlertDialog dialog;

    //Object with default color for dialog (border, background and text)
    private BeautifulDialogColors colors;

    private boolean withoutBorder = false;

    private TextView messageView;
    private TextView titleText;
    private TextView positiveButton;
    private TextView negativeButton;

    //If title (or message etc) used -> true, else -> false
    private boolean messageIsActive = false;
    private boolean titleIsActive = false;
    private boolean positiveButtonIsActive = false;
    private boolean negativeButtonIsActive = false;

    private BeautifulDialog(Context context) {
        this.context = context;

        builder = new AlertDialog.Builder(context);
    }

    /**
     * Init colors and get dialog view
     *
     * @param context
     * @param addView will be add to result dialog (can be null)
     */
    public BeautifulDialog(Context context, View addView) {
        this(context);

        this.colors = BeautifulDialogColors.getDefaultColors(context);
        this.view = getView(addView);
    }

    /**
     * Init colors and get dialog view
     *
     * @param context
     * @param colors for dialog
     * @param addView will be add to result dialog (can be null)
     */
    public BeautifulDialog(Context context, BeautifulDialogColors colors, View addView) {
        this(context);

        this.colors = colors;
        this.view = getView(addView);
    }

    /**
     * Set title for dialog
     *
     * @param res text resource for title text
     * @return dialog
     */
    public BeautifulDialog setTitle(@StringRes int res){
        return setTitle(getString(context, res));
    }

    /**
     * Set title for dialog
     *
     * @param title
     * @return dialog
     */
    public BeautifulDialog setTitle(String title){
        titleIsActive = true;

        titleText.setText(title);

        if(colors != null)
            titleText.setTextColor(colors.getTextColor());

        return this;
    }

    /**
     * Set right button and listener in dialog
     *
     * @param res text resource for button
     * @param listener click event
     * @return dialog
     */
    public BeautifulDialog setPositiveButton(@StringRes int res, BeautifulDialogInterface listener){
        return setPositiveButton(getString(context, res), listener);
    }

    /**
     * Set right button and listener in dialog
     *
     * @param name for button
     * @param listener click event
     * @return dialog
     */
    public BeautifulDialog setPositiveButton(String name, BeautifulDialogInterface listener){
        positiveButtonIsActive = true;

        setButtonListener(positiveButton, name, listener);

        return this;
    }

    /**
     * Set left button and listener in dialog
     *
     * @param res text resource for button
     * @param listener click event
     * @return dialog
     */
    public BeautifulDialog setNegativeButton(@StringRes int res, BeautifulDialogInterface listener){
        return setNegativeButton(getString(context, res), listener);
    }

    /**
     * Set left button and listener in dialog
     *
     * @param name for button
     * @param listener click event
     * @return dialog
     */
    public BeautifulDialog setNegativeButton(String name, BeautifulDialogInterface listener){
        negativeButtonIsActive = true;

        setButtonListener(negativeButton, name, listener);

        return this;
    }

    /**
     * Determines if the dialog will have a border
     *
     * @param withoutBorder true -> dialog will have a border
     *                      false -> dialog will NOT have a border
     * @return dialog
     */
    public BeautifulDialog withoutBorder(boolean withoutBorder){
        this.withoutBorder = withoutBorder;

        return this;
    }

    /**
     * Set text to messageView in dialog
     *
     * @param res string res for message
     * @return dialog
     */
    public BeautifulDialog setMessage(@StringRes int res){
        return setMessage(getString(context, res));
    }

    /**
     * Set text to messageView in dialog
     *
     * @param message for messageView
     * @return dialog
     */
    public BeautifulDialog setMessage(String message){
        messageIsActive = true;
        messageView.setText(message);

        return this;
    }

    public BeautifulDialog setMessageTextSize(float size){
        messageView.setTextSize(size);

        return this;
    }

    public BeautifulDialog setMessageTextColor(@ColorRes int res){
        messageView.setTextColor(context.getColor(res));

        return this;
    }

    /**
     * Handle cancel event
     *
     * @param listener do on cancel dialog
     * @return dialog
     */
    public BeautifulDialog setOnCancelListener(DialogInterface.OnCancelListener listener){
        dialog.setOnCancelListener(listener);

        return this;
    }

    /**
     * Show dialog and add custom view to dialog
     */
    public void show(){
        dialog = builder.create();

        hideIfNotActive(messageIsActive, messageView);
        hideIfNotActive(titleIsActive, titleText);
        hideIfNotActive(positiveButtonIsActive, positiveButton);
        hideIfNotActive(negativeButtonIsActive, negativeButton);

        if(!messageIsActive)
            messageView.setVisibility(View.GONE);
        else
            messageView.setVisibility(View.VISIBLE);

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setView(view);
        dialog.show();
    }

    /**
     * Dismiss dialog
     */
    public void dismiss(){
        if(dialog != null)
            dialog.dismiss();
    }

    /**
     * Get dialog view with adding custom view (addView)
     *
     * @param addView custom view
     * @return result of init view
     */
    private View getView(View addView){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null);
        CardView card = view.findViewById(R.id.dialog_card);

        messageView = view.findViewById(R.id.dialog_message);
        titleText = view.findViewById(R.id.dialog_title);
        positiveButton = view.findViewById(R.id.dialog_positive_button);
        negativeButton = view.findViewById(R.id.dialog_negative_button);

        FrameLayout layout = view.findViewById(R.id.dialog_add_view);
        layout.removeAllViews();

        if(colors != null) {
            if(withoutBorder)
                card.setBackground(colors.getCardDrawable());
            else
                card.setBackground(colors.getCardDrawableWithBorder());

            messageView.setTextColor(colors.getTextColor());
        }

        if(addView != null)
            layout.addView(addView);

        return view;
    }

    /**
     * Init style and set listener for button
     *
     * @param button (positive or negative button)
     * @param name for button
     * @param listener to handle click event for button
     */
    private void setButtonListener(TextView button, String name, BeautifulDialogInterface listener){
        button.setText(name.toUpperCase());

        if(colors != null) {
            setRippleEffect(colors.getBackgroundDrawable(), colors.getBorderAndHighlightColor(), button);
            button.setTextColor(colors.getTextColor());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null)
                    listener.onClick();

                dismiss();
            }
        });
    }

    /**
     * Hide view if isActive == false
     *
     * @param isActive true -> visibility will set to VISIBLE, else -> GONE
     * @param hideableView view that will be hide or not
     */
    private void hideIfNotActive(boolean isActive, View hideableView){
        if(isActive)
            hideableView.setVisibility(View.VISIBLE);
        else
            hideableView.setVisibility(View.GONE);
    }

    /**
     * Get string from resource
     *
     * @param context
     * @param id - resource of string
     * @return result string
     */
    private String getString(Context context, @StringRes int id){
        return context.getString(id);
    }

    /**
     * Set ripple effect for some view
     *
     * @param background for view to add ripple effect
     * @param hintColor color that will be displayed on touch
     * @param view that will be get ripple effect
     */
    private void setRippleEffect(Drawable background, int hintColor, View view){
        RippleDrawable rippleDrawable = new RippleDrawable(
                ColorStateList.valueOf(hintColor),
                background,
                null
        );

        view.setBackground(rippleDrawable);
    }
}
