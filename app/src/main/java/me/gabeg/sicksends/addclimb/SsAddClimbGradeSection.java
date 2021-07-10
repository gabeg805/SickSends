package me.gabeg.sicksends.addclimb;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import me.gabeg.sicksends.R;
import me.gabeg.sicksends.problem.gradingsystem.SsGradeNode;
import me.gabeg.sicksends.problem.gradingsystem.SsGradingSystem;
import me.gabeg.sicksends.shared.SsSharedPreferences;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;

/**
 * Grade section in the fragment for adding climbs.
 */
public class SsAddClimbGradeSection
	extends SsAddClimbSection
	implements MaterialButtonToggleGroup.OnButtonCheckedListener
{

	/**
	 * Log tag name.
	 */
	private static final String TAG = "SsAddClimbGradeSection";

	/**
	 * Layout inflater.
	 */
	private LayoutInflater mLayoutInflater;

	/**
	 * Toggle group for grade levels.
	 */
	private MaterialButtonToggleGroup mLevelToggleGroup;

	/**
	 * Toggle group for letters of a grade level.
	 */
	private MaterialButtonToggleGroup mLetterToggleGroup;

	/**
	 * Toggle group for operators of a letter of a grade level.
	 */
	private MaterialButtonToggleGroup mOperatorToggleGroup;

	/**
	 * The grading system.
	 */
	private SsGradingSystem mGradingSystem;

	/**
	 * The grading system.
	 */
	private String mGradingSystemName;

	/**
	 */
	public SsAddClimbGradeSection(View root, LayoutInflater inflater)
	{
		super(root);

		this.mLayoutInflater = inflater;
		this.mGradingSystemName = "";
		this.mSection = root.findViewById(R.id.grade_section);
		this.mQuestionTextView = root.findViewById(R.id.grade_question);
		this.mAnswerTextView = root.findViewById(R.id.grade_answer);
		this.mEntryLayout = root.findViewById(R.id.grade_entry);
		this.mLevelToggleGroup = root.findViewById(R.id.grade_entry_level);
		this.mLetterToggleGroup = root.findViewById(R.id.grade_entry_letter);
		this.mOperatorToggleGroup = root.findViewById(R.id.grade_entry_operator);
	}

	/**
	 * Build the grade entry letters.
	 */
	private void buildLettersToggleGroup()
	{
		MaterialButtonToggleGroup letterGroup = this.getLetterToggleGroup();
		Set<String> letters = this.getLettersInGradingSystem();

		Log.i(TAG, "Building letters toggle group!");
		this.buildToggleGroup(letterGroup, letters);
	}

	/**
	 * Build the grade entry levels.
	 */
	private void buildLevelsToggleGroup()
	{
		MaterialButtonToggleGroup levelGroup = this.getLevelToggleGroup();
		Set<String> levels = this.getLevelsInGradingSystem();

		this.buildToggleGroup(levelGroup, levels);
	}

	/**
	 * Build the grade entry operators.
	 */
	private void buildOperatorsToggleGroup()
	{
		MaterialButtonToggleGroup operatorGroup = this.getOperatorToggleGroup();
		Set<String> operators = this.getOperatorsInGradingSystem();

		this.buildToggleGroup(operatorGroup, operators);
	}

	/**
	 * Build a toggle group.
	 */
	private void buildToggleGroup(MaterialButtonToggleGroup group,
		Set<String> names)
	{
		LayoutInflater inflater = this.getLayoutInflater();
		String selectedText = this.getSelectedText(group);

		for (String text : names)
		{
			View view = inflater.inflate(R.layout.btn_grade, group, true);
			MaterialButton button = view.findViewById(R.id.grade_button);
			int id = View.generateViewId();

			if (!selectedText.isEmpty() && selectedText.equals(text))
			{
				button.setChecked(true);
			}

			button.setId(id);
			button.setText(text);
			button.setCheckable(true);
		}

		if (group.getChildCount() > 0)
		{
			group.addOnButtonCheckedListener(this);
			group.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * @see SsAddClimbSection#focus()
	 */
	@Override
	public void focus()
	{
		super.focus();

		this.setupGradingSystem();
		this.removeLevelToggleGroupViews();
		this.removeLetterToggleGroupViews();
		this.removeOperatorToggleGroupViews();
		this.buildLevelsToggleGroup();
	}

	/**
	 * Get the grading system.
	 *
	 * @return The grading system.
	 */
	public SsGradingSystem getGradingSystem()
	{
		return this.mGradingSystem;
	}

	/**
	 * Get the name of the grading system.
	 *
	 * @return The name of the grading system.
	 */
	public String getGradingSystemName()
	{
		return this.mGradingSystemName;
	}

	/**
	 * Get the layout inflater.
	 *
	 * @return The layout inflater.
	 */
	public LayoutInflater getLayoutInflater()
	{
		return this.mLayoutInflater;
	}

	/**
	 * Get the entry view for the grade letter.
	 *
	 * @return The entry view for the grade letter.
	 */
	public MaterialButtonToggleGroup getLetterToggleGroup()
	{
		return this.mLetterToggleGroup;
	}

	/**
	 * Get the letters in the grading system.
	 *
	 * @return The letters in the grading system.
	 */
	public Set<String> getLettersInGradingSystem()
	{
		SsGradingSystem system = this.getGradingSystem();
		String level = this.getSelectedLevel();
		SsGradeNode node = system.getChild(level);

		if (node.hasLetter())
		{
			return node.getChildKeys();
		}
		else
		{
			return Collections.emptySet();
		}
	}

	/**
	 * Get the entry view for the grade level.
	 *
	 * @return The entry view for the grade level.
	 */
	public MaterialButtonToggleGroup getLevelToggleGroup()
	{
		return this.mLevelToggleGroup;
	}

	/**
	 * Get the levels in the grading system.
	 *
	 * @return The levels in the grading system.
	 */
	public Set<String> getLevelsInGradingSystem()
	{
		SsGradingSystem system = this.getGradingSystem();
		return system.getChildKeys();
	}

	/**
	 * Get the entry view for the grade operator.
	 *
	 * @return The entry view for the grade operator.
	 */
	public MaterialButtonToggleGroup getOperatorToggleGroup()
	{
		return this.mOperatorToggleGroup;
	}

	/**
	 * Get the operators in the grading system.
	 *
	 * @return The operators in the grading system.
	 */
	public Set<String> getOperatorsInGradingSystem()
	{
		SsGradingSystem system = this.getGradingSystem();
		String level = this.getSelectedLevel();
		String letter = this.getSelectedLetter();
		SsGradeNode node = system.getChild(level);

		if (!letter.isEmpty())
		{
			node = node.getChild(letter);
		}

		if (node.hasOperator())
		{
			return node.getChildKeys();
		}
		else
		{
			return Collections.emptySet();
		}
	}

	/**
	 * Get the selected letter.
	 *
	 * @return The selected letter.
	 */
	public String getSelectedLetter()
	{
		MaterialButtonToggleGroup letterGroup = this.getLetterToggleGroup();
		return this.getSelectedText(letterGroup);
	}

	/**
	 * Get the selected level.
	 *
	 * @return The selected level.
	 */
	public String getSelectedLevel()
	{
		MaterialButtonToggleGroup levelGroup = this.getLevelToggleGroup();
		return this.getSelectedText(levelGroup);
	}

	/**
	 * Get the selected operator.
	 *
	 * @return The selected operator.
	 */
	public String getSelectedOperator()
	{
		MaterialButtonToggleGroup operatorGroup = this.getOperatorToggleGroup();
		return this.getSelectedText(operatorGroup);
	}

	/**
	 * Get the selected text 
	 *
	 * @return The selected text in the MaterialButtonToggleGroup.
	 */
	public String getSelectedText(MaterialButtonToggleGroup group)
	{
		for (int i=0; i < group.getChildCount(); i++)
		{
			MaterialButton button = (MaterialButton) group.getChildAt(i);

			if (button.isChecked())
			{
				return button.getText().toString();
			}
		}

		return "";
	}

	/**
	 * @see SsAddClimbSection#getUserInput()
	 */
	public String getUserInput()
	{
		Locale locale = Locale.getDefault();
		String level = this.getSelectedLevel();
		String letter = this.getSelectedLetter();
		String operator = this.getSelectedOperator();

		return String.format(locale, "%1$s%2$s%3$s", level, letter, operator);
	}

	/**
	 */
	@Override
	public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId,
		boolean isChecked)
	{
		MaterialButtonToggleGroup levelGroup = this.getLevelToggleGroup();
		MaterialButtonToggleGroup letterGroup = this.getLetterToggleGroup();
		MaterialButtonToggleGroup operatorGroup = this.getOperatorToggleGroup();

		if (group == levelGroup)
		{
			this.showGradeLetters();
		}
		else if (group == letterGroup)
		{
			this.showGradeOperators();
		}
		else if (group == operatorGroup)
		{
			this.done();
		}
		else
		{
			this.done();
		}
	}

	/**
	 * Remove letter toggle group views.
	 */
	public void removeLetterToggleGroupViews()
	{
		MaterialButtonToggleGroup group = this.getLetterToggleGroup();

		group.removeAllViews();
		group.setVisibility(View.GONE);
	}

	/**
	 * Remove level toggle group views.
	 */
	public void removeLevelToggleGroupViews()
	{
		MaterialButtonToggleGroup group = this.getLevelToggleGroup();

		group.removeAllViews();
		group.setVisibility(View.GONE);
	}

	/**
	 * Remove operator toggle group views.
	 */
	public void removeOperatorToggleGroupViews()
	{
		MaterialButtonToggleGroup group = this.getOperatorToggleGroup();

		group.removeAllViews();
		group.setVisibility(View.GONE);
	}

	/**
	 * Set the grading system.
	 *
	 * @param  system  The grading system.
	 */
	public void setGradingSystem(SsGradingSystem system)
	{
		this.mGradingSystem = system;
	}

	/**
	 * Set the name of the grading system.
	 */
	public void setGradingSystemName(String name)
	{
		this.mGradingSystemName = name;
	}

	/**
	 * Setup the grading system.
	 */
	public void setupGradingSystem()
	{
		Context context = this.getEntryLayout().getContext();
		String name = this.getGradingSystemName();
		SsSharedPreferences shared = new SsSharedPreferences(context);
		SsGradingSystem system = shared.getGradingSystem(name);

		this.setGradingSystem(system);
	}

	/**
	 * Show the letters.
	 */
	public void showGradeLetters()
	{
		ViewGroup letterGroup = this.getLetterToggleGroup();

		this.buildLettersToggleGroup();

		if (letterGroup.getChildCount() == 0)
		{
			this.showGradeOperators();
		}
	}

	/**
	 * Show the operators.
	 */
	public void showGradeOperators()
	{
		ViewGroup operatorGroup = this.getOperatorToggleGroup();

		this.buildOperatorsToggleGroup();

		if (operatorGroup.getChildCount() == 0)
		{
			this.done();
		}
	}

}
