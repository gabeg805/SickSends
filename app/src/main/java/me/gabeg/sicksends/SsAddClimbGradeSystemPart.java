package me.gabeg.sicksends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.material.button.MaterialButton;
import java.util.Collection;
import java.util.List;

/**
 * Grading system section part.
 */
public class SsAddClimbGradeSystemPart
	extends SsAddClimbGradePart
	implements MaterialButton.OnCheckedChangeListener
{

	/**
	 * Log tag name.
	 */
	private static final String TAG = "SsAddClimbGradeSystemPart";

	/**
	 */
	public SsAddClimbGradeSystemPart(View root, LayoutInflater inflater)
	{
		super(root, inflater);

		this.mPartLayout = root.findViewById(R.id.grade_entry_system);
	}

	/**
	 * @see SsAddClimbPart#focus()
	 */
	@Override
	public void focus()
	{
		Collection<String> systems = this.getAllGradeNames();
		this.build(systems);

		//for (String name : this.getAllGradeNames())
		//{
		//	View view = inflater.inflate(R.layout.btn_grade, section, true);
		//	MaterialButton button = view.findViewById(R.id.grade_button);
		//	int id = View.generateViewId();

		//	button.setId(id);
		//	button.setText(name);
		//	button.setCheckable(true);
		//	button.addOnCheckedChangeListener(this);
		//}
	}

	/**
	 * @return All grade names for a type of climbing.
	 */
	private List<String> getAllGradeNames()
	{
		Context context = this.getPartLayout().getContext();
		SsSharedPreferences shared = new SsSharedPreferences(context);
		List<String> gradeNames = shared.getAllUseBoulderGrades();

		return gradeNames;
	}

	/**
	 */
	@Override
	public void onCheckedChanged(MaterialButton button, boolean isChecked)
	{
		String name = button.getText().toString();

		this.setupGradingSystem(name);
		this.done();
	}

	/**
	 * Setup the grading system that will be used.
	 *
	 * @param  name  Name of a grading system.
	 */
	private void setupGradingSystem(String name)
	{
		Context context = this.getPartLayout().getContext();
		SsSharedConstants cons = new SsSharedConstants(context);
		SsGradingSystem system = null;

		if (cons.isGradeBoulderFont(name))
		{
			system = new SsBoulderGradingSystem.Font();
		}
		else if (cons.isGradeBoulderUk(name))
		{
			system = new SsBoulderGradingSystem.Uk();
		}
		else if (cons.isGradeBoulderVscale(name))
		{
			system = new SsBoulderGradingSystem.Vscale();
		}
		else
		{
			system = new SsBoulderGradingSystem.Font();
		}

		this.setGradingSystem(system);
	}

}
