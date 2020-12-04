package me.gabeg.sicksends;

import android.view.LayoutInflater;
import android.view.View;
import com.google.android.material.button.MaterialButton;
import java.util.Collection;

/**
 * Grade letter section part.
 */
public class SsAddClimbGradeLetterPart
	extends SsAddClimbGradePart
	implements MaterialButton.OnCheckedChangeListener
{

	/**
	 * Log tag name.
	 */
	private static final String TAG = "SsAddClimbGradeLetterPart";

	/**
	 * Level.
	 */
	private String mGradeLevel;

	/**
	 */
	public SsAddClimbGradeLetterPart(View root, LayoutInflater inflater)
	{
		super(root, inflater);

		this.mPartLayout = root.findViewById(R.id.grade_entry_letter);
	}

	/**
	 * @see SsAddClimbPart#focus()
	 */
	@Override
	public void focus()
	{
		SsGradingSystem system = this.getGradingSystem();
		String level = this.getGradeLevel();
		Collection<String> letters = system.getLetters(level);

		this.build(letters);

		//for (String letter : system.getLetters(level))
		//{
		//	Log.i(TAG, "Showing grade letter! " + letter);
		//	View view = inflater.inflate(R.layout.btn_grade, section, true);
		//	MaterialButton button = view.findViewById(R.id.grade_button);
		//	int id = View.generateViewId();

		//	button.setId(id);
		//	button.setText(letter);
		//	button.setCheckable(true);
		//	button.addOnCheckedChangeListener(this);
		//}
	}

	/**
	 * @return The grade level.
	 */
	public String getGradeLevel()
	{
		return this.mGradeLevel;
	}

	/**
	 */
	@Override
	public void onCheckedChanged(MaterialButton button, boolean isChecked)
	{
		String letter = button.getText().toString();

		this.done();
	}

	/**
	 * Set the grade level.
	 */
	public void setGradeLevel(String level)
	{
		this.mGradeLevel = level;
	}

}
