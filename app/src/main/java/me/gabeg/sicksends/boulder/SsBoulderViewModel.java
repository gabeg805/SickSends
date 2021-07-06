package me.gabeg.sicksends.boulder;

import android.app.Application;

import me.gabeg.sicksends.problem.SsProblemViewModel;

/**
 * Boulder view model.
 */
public class SsBoulderViewModel
	extends SsProblemViewModel<SsBoulderRepository, SsBoulder>
{

	/**
	 */
	public SsBoulderViewModel(Application app)
	{
		super(app);
	}

	/**
	 * @see SsProblemViewModel#buildRepository(Application)
	 */
	public void buildRepository(Application app)
	{
		this.mRepository = new SsBoulderRepository(app);
	}

}
