package me.gabeg.sicksends;

import android.app.Application;

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
