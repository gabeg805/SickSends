package me.gabeg.sicksends;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.button.MaterialButton;
import java.util.Collection;

/**
 * A part of the grade section in the fragment for adding climbs.
 */
public abstract class SsAddClimbGradePart
	extends SsAddClimbPart
{

	/**
	 * Layout inflater.
	 */
	private LayoutInflater mLayoutInflater;

	/**
	 * Selected grade system.
	 */
	protected SsGradingSystem mGradingSystem;

	/**
	 */
	public SsAddClimbGradePart(View root, LayoutInflater inflater)
	{
		super(root, inflater);

		this.mLayoutInflater = inflater;
	}

	/**
	 * Build the section part.
	 *
	 * This will remove all previous Views in the part layout, before building
	 * the buttons.
	 *
	 * @param  items  Text for each button that will be created.
	 */
	public void build(Collection<String> items)
	{
		this.getPartLayout().removeAllViews();
		this.buildButtons(items);
	}

	/**
	 * Build buttons.
	 *
	 * @param  items  Text for each button that will be created.
	 */
	public void buildButtons(Collection<String> items)
	{
		LayoutInflater inflater = this.getLayoutInflater();
		ViewGroup section = this.getPartLayout();

		for (String text : items)
		{
			View view = inflater.inflate(R.layout.btn_grade, section, true);
			MaterialButton button = view.findViewById(R.id.grade_button);
			int id = View.generateViewId();

			button.setId(id);
			button.setText(text);
			button.setCheckable(true);
			//button.addOnCheckedChangeListener(this);
		}
	}

	/**
	 * @return The selected grading system.
	 */
	public SsGradingSystem getGradingSystem()
	{
		return this.mGradingSystem;
	}

	/**
	 * @return The layout inflater.
	 */
	private LayoutInflater getLayoutInflater()
	{
		return this.mLayoutInflater;
	}

	/**
	 * @return The part layout view group.
	 */
	public ViewGroup getPartLayout()
	{
		return this.mPartLayout;
	}

	/**
	 * @return The text of the selected button.
	 */
	public String getSelectedButtonText()
	{
		ViewGroup parent = this.getPartLayout();
		int count = parent.getChildCount();

		for (int i=0; i < count; i++)
		{
			MaterialButton button = (MaterialButton) parent.getChildAt(i);
			if(button.isChecked())
			{
				String selected = button.getText().toString();
				return selected;
			}
		}

		return null;
	}

	/**
	 * Set the selected grading system.
	 */
	public void setGradingSystem(SsGradingSystem system)
	{
		this.mGradingSystem = system;
	}

}
