package me.gabeg.sicksends.boulder;

import android.app.Application;

import me.gabeg.sicksends.problem.SsProblemDatabase;
import me.gabeg.sicksends.problem.SsProblemRepository;

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
