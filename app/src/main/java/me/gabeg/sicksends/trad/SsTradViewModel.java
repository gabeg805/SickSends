package me.gabeg.sicksends.trad;

import android.app.Application;

import me.gabeg.sicksends.problem.SsProblemViewModel;

/**
 * Trad view model.
 */
public class SsTradViewModel
	extends SsProblemViewModel<SsTradRepository, SsTrad>
{

	/**
	 */
	public SsTradViewModel(Application app)
	{
		super(app);
	}

	/**
	 * @see SsProblemViewModel#buildRepository(Application)
	 */
	public void buildRepository(Application app)
	{
		this.mRepository = new SsTradRepository(app);
	}

}
