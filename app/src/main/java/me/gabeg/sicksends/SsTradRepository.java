package me.gabeg.sicksends;

import android.app.Application;

/**
 * Repository for trad problems.
 */
public class SsTradRepository
	extends SsProblemRepository<SsTradDao, SsTrad>
{

	/**
	 */
	public SsTradRepository(Application app)
	{
		super(app);
	}

	/**
	 * @see SsProblemRepository#buildDao(SsProblemDatabase)
	 */
	@Override
	protected void buildDao(SsProblemDatabase db)
	{
		this.mProblemDao = db.tradDao();
	}

}
