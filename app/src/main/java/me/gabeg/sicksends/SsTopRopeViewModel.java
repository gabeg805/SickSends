package me.gabeg.sicksends;

import android.app.Application;

/**
 * Top rope view model.
 */
public class SsTopRopeViewModel
	extends SsProblemViewModel<SsTopRopeRepository, SsTopRope>
{

	/**
	 */
	public SsTopRopeViewModel(Application app)
	{
		super(app);
	}

	/**
	 * @see SsProblemViewModel#buildRepository(Application)
	 */
	public void buildRepository(Application app)
	{
		this.mRepository = new SsTopRopeRepository(app);
	}

}
