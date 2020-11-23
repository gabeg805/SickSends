package me.gabeg.sicksends;

import android.app.Application;

/**
 * Repository for sport problems.
 */
public class SsSportRepository
	extends SsProblemRepository<SsSportDao, SsSport>
{

	/**
	 */
	public SsSportRepository(Application app)
	{
		super(app);
	}

	/**
	 * @see SsProblemRepository#buildDao(SsProblemDatabase)
	 */
	@Override
	protected void buildDao(SsProblemDatabase db)
	{
		this.mProblemDao = db.sportDao();
	}

}
