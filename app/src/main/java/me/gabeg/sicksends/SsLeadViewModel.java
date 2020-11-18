package me.gabeg.sicksends;

import android.app.Application;

/**
 * Lead view model.
 */
public class SsLeadViewModel
	extends SsProblemViewModel<SsLeadRepository, SsLead>
{

	/**
	 */
	public SsLeadViewModel(Application app)
	{
		super(app);
	}

	/**
	 * @see SsProblemViewModel#buildRepository(Application)
	 */
	public void buildRepository(Application app)
	{
		this.mRepository = new SsLeadRepository(app);
	}

}
