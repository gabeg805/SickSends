package me.gabeg.sicksends.toprope;

import android.app.Application;

import me.gabeg.sicksends.problem.SsProblemDatabase;
import me.gabeg.sicksends.problem.SsProblemRepository;

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
