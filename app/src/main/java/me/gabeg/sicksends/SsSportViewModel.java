package me.gabeg.sicksends;

import android.app.Application;

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
