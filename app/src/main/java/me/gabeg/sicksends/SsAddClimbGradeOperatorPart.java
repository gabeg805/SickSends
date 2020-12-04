package me.gabeg.sicksends;

import android.view.LayoutInflater;
import android.view.View;
import com.google.android.material.button.MaterialButton;
import java.util.Collection;

/**
 * Grade operator section part.
 */
public class SsAddClimbGradeOperatorPart
	extends SsAddClimbGradePart
	implements MaterialButton.OnCheckedChangeListener
{

	/**
	 * Log tag name.
	 */
	private static final String TAG = "SsAddClimbGradeOperatorPart";

	/**
	 * Level.
	 */
	private String mGradeLevel;

	/**
	 * Letter.
	 */
	private String mGradeLetter;

	/**
	 */
	public SsAddClimbGradeOperatorPart(View root, LayoutInflater inflater)
	{
		super(root, inflater);

		this.mPartLayout= root.findViewById(R.id.grade_entry_operator);
	}

	/**
	 * @see SsAddClimbPart#focus()
	 */
	@Override
	public void focus()
	{
		SsGradingSystem system = this.getGradingSystem();
		String level = this.getGradeLevel();
		String letter = this.getGradeLetter();
		Collection<String> operators = system.getOperators(level, letter);

		this.build(operators);

		//for (String operator : system.getOperators(level, letter))
		//{
		//	Log.i(TAG, "Showing grade operator ! " + operator);
		//	View view = inflater.inflate(R.layout.btn_grade, section, true);
		//	MaterialButton button = view.findViewById(R.id.grade_button);
		//	int id = View.generateViewId();

		//	button.setId(id);
		//	button.setText(operator);
		//	button.setCheckable(true);
		//	button.addOnCheckedChangeListener(this);
		//}
	}

	/**
	 * @return The grade letter.
	 */
	public String getGradeLetter()
	{
		return this.mGradeLetter;
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
		String name = button.getText().toString();

		//Log.i(TAG, "Grade operator selected. Continue to next section! " + operator);
		//this.getGradeQa().append(operator);
	}

	/**
	 * Set the grade letter.
	 */
	public void setGradeLetter(String letter)
	{
		this.mGradeLetter = letter;
	}

	/**
	 * Set the grade level.
	 */
	public void setGradeLevel(String level)
	{
		this.mGradeLevel = level;
	}

}
