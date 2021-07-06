package me.gabeg.sicksends.addclimb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import me.gabeg.sicksends.R;
import me.gabeg.sicksends.shared.SsSharedPreferences;

import java.util.List;

/**
 * Grade section in the fragment for adding climbs.
 */
public class SsAddClimbGradingSystemSection
	extends SsAddClimbSection
	implements MaterialButtonToggleGroup.OnButtonCheckedListener
{

	/**
	 * Log tag name.
	 */
	private static final String TAG = "SsAddClimbGradingSystemSection";

	/**
	 * Toggle group for the material buttons.
	 */
	private MaterialButtonToggleGroup mGradingSystemToggleGroup;

	/**
	 */
	public SsAddClimbGradingSystemSection(View root, LayoutInflater inflater)
	{
		super(root);

		this.mSection = root.findViewById(R.id.grade_system_section);
		this.mQaTextView = root.findViewById(R.id.grade_system_qa);
		this.mEntryLayout = root.findViewById(R.id.grade_system_entry);
		this.mGradingSystemToggleGroup = root.findViewById(R.id.grade_system_entry_group);

		this.buildGradingSystems(inflater);
	}

	/**
	 * Build buttons.
	 *
	 * @param  items  Text for each button that will be created.
	 */
	public void buildGradingSystems(LayoutInflater inflater)
	{
		MaterialButtonToggleGroup group = this.getToggleGroup();
		List<String> systems = this.getAllGradeNames();
		int size = systems.size();

		for (String text : systems)
		{
			View view = inflater.inflate(R.layout.btn_grade, group, true);
			MaterialButton button = view.findViewById(R.id.grade_button);
			int id = View.generateViewId();

			button.setId(id);
			button.setText(text);
			button.setCheckable(true);

			if (size == 1)
			{
				button.setChecked(true);
			}
		}

		group.addOnButtonCheckedListener(this);
	}

	/**
	 * @return All grade names for a type of climbing.
	 */
	private List<String> getAllGradeNames()
	{
		Context context = this.getEntryLayout().getContext();
		SsSharedPreferences shared = new SsSharedPreferences(context);
		List<String> gradeNames = shared.getAllUseBoulderGrades();

		return gradeNames;
	}

	/**
	 * @return The question to ask in the Q/A TextView.
	 */
	public int getQuestion()
	{
		return R.string.question_climb_grading_system;
	}

	/**
	 * @return The toggle group for material buttons.
	 */
	private MaterialButtonToggleGroup getToggleGroup()
	{
		return this.mGradingSystemToggleGroup;
	}

	/**
	 * @see SsAddClimbSection#getUserInput()
	 */
	public String getUserInput()
	{
		MaterialButtonToggleGroup group = this.getToggleGroup();
		int count = group.getChildCount();

		for (int i=0; i < count; i++)
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
	 */
	@Override
	public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId,
		boolean isChecked)
	{
		this.done();
	}

}
