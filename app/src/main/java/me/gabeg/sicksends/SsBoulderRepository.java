package me.gabeg.sicksends;

import android.app.Application;

/**
 * Repository for boulder problems.
 */
public class SsBoulderRepository
	extends SsProblemRepository<SsBoulderDao, SsBoulder>
{

	/**
	 */
	public SsBoulderRepository(Application app)
	{
		super(app);
	}

	/**
	 * @see SsProblemRepository#buildDao(SsProblemDatabase)
	 */
	@Override
	protected void buildDao(SsProblemDatabase db)
	{
		this.mProblemDao = db.boulderDao();
	}

}
