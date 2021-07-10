package me.gabeg.sicksends.addclimb;

import android.content.Context;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;

import me.gabeg.sicksends.R;

import java.lang.Float;

/**
 * Feel scale section for the fragment for adding climbs.
 */
public class SsAddClimbHoldTypeSection
	extends SsAddClimbSection
{

	/**
	 * Log tag name.
	 */
	public static final String TAG = "SsAddClimbHoldTypeSection";

	/**
	 */
	public SsAddClimbHoldTypeSection(View root)
	{
		super(root);

		this.mSection = root.findViewById(R.id.feel_scale_section);
		this.mQuestionTextView = root.findViewById(R.id.feel_scale_question);
		this.mAnswerTextView = root.findViewById(R.id.feel_scale_answer);
		this.mEntryLayout = root.findViewById(R.id.feel_scale_entry);
		//this.mHoldTypeSlider = root.findViewById(R.id.feel_scale_entry_slider);

		//this.getHoldTypeSlider().setLabelFormatter(this);
		//this.getAttemptsEntry().setOnEditorActionListener(this);
	}

	/**
	 * @see SsAddClimbSection#getUserInput()
	 */
	public String getUserInput()
	{
		//return "Crimp, Jug, Sloper!";
		return "";
	}

}
