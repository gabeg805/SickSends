package me.gabeg.sicksends;

import android.app.Application;

/**
 * Repository for lead problems.
 */
public class SsLeadRepository
	extends SsProblemRepository<SsLeadDao, SsLead>
{

	/**
	 */
	public SsLeadRepository(Application app)
	{
		super(app);
	}

	/**
	 * @see SsProblemRepository#buildDao(SsProblemDatabase)
	 */
	@Override
	protected void buildDao(SsProblemDatabase db)
	{
		this.mProblemDao = db.leadDao();
	}

}
