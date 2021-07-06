package me.gabeg.sicksends.toprope;

import android.app.Application;

import me.gabeg.sicksends.problem.SsProblemViewModel;

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
