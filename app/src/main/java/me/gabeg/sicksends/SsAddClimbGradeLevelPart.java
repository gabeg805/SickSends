package me.gabeg.sicksends;

import android.view.LayoutInflater;
import android.view.View;
import com.google.android.material.button.MaterialButton;
import java.util.Collection;

/**
 * Grade level section part.
 */
public class SsAddClimbGradeLevelPart
	extends SsAddClimbGradePart
	implements MaterialButton.OnCheckedChangeListener
{

	/**
	 * Log tag name.
	 */
	private static final String TAG = "SsAddClimbGradeLevelPart";

	/**
	 */
	public SsAddClimbGradeLevelPart(View root, LayoutInflater inflater)
	{
		super(root, inflater);

		this.mPartLayout = root.findViewById(R.id.grade_entry_level);
	}

	/**
	 * @see SsAddClimbPart#focus()
	 */
	@Override
	public void focus()
	{
		SsGradingSystem system = this.getGradingSystem();
		Collection<String> levels = system.getChildKeys();
		this.build(levels);

		//for (String level : system.getChildKeys())
		//{
		//	Log.i(TAG, "Showing grade level! " + level);
		//	View view = inflater.inflate(R.layout.btn_grade, section, true);
		//	MaterialButton button = view.findViewById(R.id.grade_button);
		//	int id = View.generateViewId();

		//	button.setId(id);
		//	button.setText(level);
		//	button.setCheckable(true);
		//	button.addOnCheckedChangeListener(this);
		//}
	}

	/**
	 */
	@Override
	public void onCheckedChanged(MaterialButton button, boolean isChecked)
	{
		this.done();
	}

}
