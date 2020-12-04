package me.gabeg.sicksends;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Grade section in the fragment for adding climbs.
 */
public class SsAddClimbGradeSection
	extends SsAddClimbSection
	implements SsAddClimbPart.OnPartDoneListener
{

	/**
	 * Log tag name.
	 */
	private static final String TAG = "SsAddClimbGradeSection";

	/**
	 * Grade entry section.
	 */
	//private LinearLayout mGradeEntrySection;
	private SsAddClimbGradeSystemPart mSystemPart;

	/**
	 * Section containing all the possible grade levels for a given grade name.
	 */
	//private LinearLayout mGradeLevelSection;
	private SsAddClimbGradeLevelPart mLevelPart;

	/**
	 * Section containing all the possible grade letters for a given grade level.
	 */
	//private LinearLayout mGradeLetterSection;
	private SsAddClimbGradeLetterPart mLetterPart;

	/**
	 * Section containing all the possible grade operators for a given grade level.
	 */
	//private LinearLayout mGradeOperatorSection;
	private SsAddClimbGradeOperatorPart mOperatorPart;

	/**
	 */
	public SsAddClimbGradeSection(View root, LayoutInflater inflater)
	{
		super(root);

		this.mSection = root.findViewById(R.id.name_section);
		//this.mTitleTextView = root.findViewById(R.id.grade_title);
		this.mSubtitleTextView = root.findViewById(R.id.grade_subtitle);
		this.mQaTextView = root.findViewById(R.id.grade_qa);
		this.mEntryLayout = root.findViewById(R.id.grade_entry);

		this.mSystemPart = new SsAddClimbGradeSystemPart(root, inflater);
		this.mLevelPart = new SsAddClimbGradeLevelPart(root, inflater);
		this.mLetterPart = new SsAddClimbGradeLetterPart(root, inflater);
		this.mOperatorPart = new SsAddClimbGradeOperatorPart(root, inflater);

		this.mSystemPart.setOnPartDoneListener(this);
		this.mLevelPart.setOnPartDoneListener(this);
		this.mLetterPart.setOnPartDoneListener(this);
		this.mOperatorPart.setOnPartDoneListener(this);
	}

	/**
	 * @see SsAddClimbSection#focus()
	 */
	@Override
	public void focus()
	{
		super.focus();
		this.mSystemPart.focus();
	}

	/**
	 * @see SsAddClimbSection#getUserInput()
	 */
	public String getUserInput()
	{
		return "AHHHHHH";
		//TextInputEditText entry = this.getAttemptsEntry();
		//return entry.getText().toString();
	}

	/**
	 */
	@Override
	public void onPartDone(SsAddClimbPart part)
	{
		SsGradingSystem system = this.mSystemPart.getGradingSystem();
		String text = ((SsAddClimbGradePart)part).getSelectedButtonText();

		if (part instanceof SsAddClimbGradeSystemPart)
		{
			this.getSubtitleTextView().setText(text);

			this.mLevelPart.setGradingSystem(system);
			this.mLetterPart.setGradingSystem(system);
			this.mOperatorPart.setGradingSystem(system);

			this.mLevelPart.focus();
		}
		else if (part instanceof SsAddClimbGradeLevelPart)
		{
			this.getSubtitleTextView().append(text);

			this.mLetterPart.setGradeLevel(text);
			this.mOperatorPart.setGradeLevel(text);

			if (system.hasLetter(text))
			{
				this.mLetterPart.focus();
			}
			else if (system.hasOperator(text))
			{
				this.mOperatorPart.focus();
			}
		}
		else if (part instanceof SsAddClimbGradeLetterPart)
		{
			this.getSubtitleTextView().append(text);

			if (system.hasOperator(text))
			{
				this.mOperatorPart.setGradeLetter(text);
				this.mOperatorPart.focus();
			}
			else
			{
				// Show next section
				//Log.i(TAG, "Show next section in grade entry letter!");
			}
		}
		else if (part instanceof SsAddClimbGradeOperatorPart)
		{
			this.getSubtitleTextView().append(text);
			// Show next section
		}
	}

	/**
	 * Show the grade section.
	 */
	//private void showGradeSection()
	//{
	//	this.showGradeNameSection();
	//	this.mGradeQa.setVisibility(View.VISIBLE);
	//	this.mGradeEntrySection.setVisibility(View.VISIBLE);
	//}

}
