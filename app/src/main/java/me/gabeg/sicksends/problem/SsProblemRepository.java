package me.gabeg.sicksends.problem;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

/**
 */
public abstract class SsProblemRepository<T extends SsProblemDao<S>, S extends SsProblem>
{

	/**
	 * Data access object for a type of climbing problem.
	 */
	protected T mProblemDao;

	/**
	 * Live data list of all climbing problems of a particular type.
	 */
	protected LiveData<List<S>> mAllProblems;

	/**
	 */
	public SsProblemRepository(Application app)
	{
		SsProblemDatabase db = SsProblemDatabase.getInstance(app);

		this.buildDao(db);
		this.buildLiveData(this.getDao());
	}

	/**
	 * Build the data access object for a type of climbing problem.
	 */
	protected abstract void buildDao(SsProblemDatabase db);

	/**
	 * Build the live data list of all climbing problems.
	 */
	protected void buildLiveData(T dao)
	{
		this.mAllProblems = dao.getAll();
	}

	/**
	 * @return The live data list of all climbing problems.
	 */
	public LiveData<List<S>> getAllProblems()
	{
		return this.mAllProblems;
	}

	/**
	 * @return The data access object for a type of climbing problem.
	 */
	public T getDao()
	{
		return this.mProblemDao;
	}

	/**
	 * Insert a type of climbing problem, asynchronously, into the database.
	 */
	public void insert(S problem)
	{
		T dao = this.getDao();
		new insertAsyncTask<T,S>(dao).execute(problem);
	}

	/**
	 * Asynchronously insert a type of climbing problem into the database.
	 */
	private static class insertAsyncTask<U extends SsProblemDao<V>, V extends SsProblem>
		extends AsyncTask<V, Void, Void>
	{

		/**
		 * Asynchronous data access object for a type of climbing problem.
		 */
		private U mAsyncProblemDao;

		/**
		 */
		public insertAsyncTask(U dao)
		{
			this.mAsyncProblemDao = dao;
		}

		/**
		 * Execute the insert in the background.
		 */
		@Override
		protected Void doInBackground(final V... params)
		{
			this.getDao().insert(params[0]);
			return null;
		}

		/**
		 * @return The asynchronous data access object for a type of climbing
		 *     problem.
		 */
		public U getDao()
		{
			return this.mAsyncProblemDao;
		}

	}

}
