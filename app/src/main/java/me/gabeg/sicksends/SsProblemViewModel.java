package me.gabeg.sicksends;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

/**
 * Problem view model.
 */
public abstract class SsProblemViewModel<T extends SsProblemRepository, S extends SsProblem>
	extends AndroidViewModel
{

	public static final String TAG = "SsProblemViewModel";

	/**
	 * Repository of the climbing problems.
	 */
	protected T mRepository;

	/**
	 * Live data list of all climbing problems of a particular type.
	 */
	protected LiveData<List<S>> mAllProblems;

	/**
	 */
	public SsProblemViewModel(Application app)
	{
		super(app);

		this.buildRepository(app);
		this.buildAllProblems(this.getRepository());
	}

	/**
	 * Build the live data list of all climbing problems.
	 */
	public void buildAllProblems(T repo)
	{
		this.mAllProblems = repo.getAllProblems();
	}

	/**
	 * Build the repository of climbing problems.
	 */
	public abstract void buildRepository(Application app);

	/**
	 * @return The live data list of a type of climbing problems.
	 */
	public LiveData<List<S>> getAllProblems()
	{
		return this.mAllProblems;
	}

	/**
	 * @return Repository of the climbing problems.
	 */
	public T getRepository()
	{
		return this.mRepository;
	}

	/**
	 * Insert a type of climbing problem into the database via the repository.
	 */
	public void insert(S problem)
	{
		Log.i(TAG, "Inserting problem into the repository!");
		this.getRepository().insert(problem);
	}

}
