package me.gabeg.sicksends;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.view.View;

/**
 * Control the focus of the soft keyboard.
 */
public class SsSoftKeyboard
{

	/**
	 * Hide the soft keyboard.
	 */
	public static void hide(Context context, View view)
	{
		InputMethodManager imm = (InputMethodManager) context.getSystemService(
			Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(view.getWindowToken(),
			InputMethodManager.HIDE_NOT_ALWAYS);
	}

	/**
	 * Show the soft keyboard.
	 */
	public static void show(Context context, View view)
	{
		InputMethodManager imm = (InputMethodManager) context.getSystemService(
			Context.INPUT_METHOD_SERVICE);

		view.requestFocus();
		imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
	}

}
