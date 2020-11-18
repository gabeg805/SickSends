package me.gabeg.sicksends;

import android.app.Application;

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
