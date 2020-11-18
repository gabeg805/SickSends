package me.gabeg.sicksends;

import android.app.Application;

/**
 * Repository for top rope problems.
 */
public class SsTopRopeRepository
	extends SsProblemRepository<SsTopRopeDao, SsTopRope>
{

	/**
	 */
	public SsTopRopeRepository(Application app)
	{
		super(app);
	}

	/**
	 * @see SsProblemRepository#buildDao(SsProblemDatabase)
	 */
	@Override
	protected void buildDao(SsProblemDatabase db)
	{
		this.mProblemDao = db.topRopeDao();
	}

}
