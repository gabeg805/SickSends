package me.gabeg.sicksends.sport;

import android.app.Application;

import me.gabeg.sicksends.problem.SsProblemDatabase;
import me.gabeg.sicksends.problem.SsProblemRepository;

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
