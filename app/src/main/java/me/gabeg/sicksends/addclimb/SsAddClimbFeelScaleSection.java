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
public class SsAddClimbFeelScaleSection
	extends SsAddClimbSection
	implements LabelFormatter
{

	/**
	 * Log tag name.
	 */
	public static final String TAG = "SsAddClimbFeelScaleSection";

	/**
	 * Slider to change how a climb felt.
	 */
	private Slider mFeelScaleSlider;

	/**
	 */
	public SsAddClimbFeelScaleSection(View root)
	{
		super(root);

		this.mSection = root.findViewById(R.id.feel_scale_section);
		this.mQuestionTextView = root.findViewById(R.id.feel_scale_question);
		this.mAnswerTextView = root.findViewById(R.id.feel_scale_answer);
		this.mEntryLayout = root.findViewById(R.id.feel_scale_entry);
		this.mFeelScaleSlider = root.findViewById(R.id.feel_scale_entry_slider);

		this.getFeelScaleSlider().setLabelFormatter(this);
	}

	/**
	 * @return The slider indicating how a climb felt.
	 */
	public Slider getFeelScaleSlider()
	{
		return this.mFeelScaleSlider;
	}

	/**
	 * Set the label that should appear when a user slides over a tick mark.
	 */
	@Override
	public String getFormattedValue(float value)
	{
		Log.i(TAG, "Formatted value : " + value);

		if (Float.compare(value, 0f) == 0)
		{
			return "Very easy";
		}
		else if (Float.compare(value, 1f) == 0)
		{
			return "Easy";
		}
		else if (Float.compare(value, 2f) == 0)
		{
			return "Normal";
		}
		else if (Float.compare(value, 3f) == 0)
		{
			return "Hard";
		}
		else if (Float.compare(value, 4f) == 0)
		{
			return "Very hard";
		}
		else
		{
			return "Normal";
		}
	}

	/**
	 * @see SsAddClimbSection#getUserInput()
	 */
	public String getUserInput()
	{
		Slider slider = this.getFeelScaleSlider();

		//slider.set
		return "Very hard";
	}

}
