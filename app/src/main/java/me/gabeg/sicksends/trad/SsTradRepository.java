package me.gabeg.sicksends.trad;

import android.app.Application;

import me.gabeg.sicksends.problem.SsProblemDatabase;
import me.gabeg.sicksends.problem.SsProblemRepository;

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
