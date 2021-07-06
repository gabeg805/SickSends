package me.gabeg.sicksends.sport;

import android.app.Application;

import me.gabeg.sicksends.problem.SsProblemViewModel;

/**
 * Sport view model.
 */
public class SsSportViewModel
	extends SsProblemViewModel<SsSportRepository, SsSport>
{

	/**
	 */
	public SsSportViewModel(Application app)
	{
		super(app);
	}

	/**
	 * @see SsProblemViewModel#buildRepository(Application)
	 */
	public void buildRepository(Application app)
	{
		this.mRepository = new SsSportRepository(app);
	}

}
