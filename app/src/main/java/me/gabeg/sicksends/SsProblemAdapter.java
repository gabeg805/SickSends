package me.gabeg.sicksends;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * Card adapter where the cards contain information on a climbing problem.
 */
public class SsProblemAdapter<T>
	extends RecyclerView.Adapter<SsProblemViewHolder>
{

	protected String TAG = "SsProblemAdapter";

	/**
	 * Layout inflater to create view holders.
	 */
	private final LayoutInflater mInflater;

	/**
	 * List of problems (cached copy).
	 */
	private List<T> mProblems;

	/**
	 */
	public SsProblemAdapter(Context context)
	{
		this.mInflater = LayoutInflater.from(context);
	}

	/**
	 * @return The number of items in the problems list.
	 */
	@Override
	public int getItemCount()
	{
		List<T> problems = this.getProblems();
		return (problems != null) ? problems.size() : 0;
	}

	/**
	 * @return The layout inflater.
	 */
	private LayoutInflater getLayoutInflater()
	{
		return this.mInflater;
	}

	/**
	 * @return The list of problems.
	 */
	public List<T> getProblems()
	{
		return this.mProblems;
	}

	/**
	 * Create a view holder.
	 */
	@Override
	public SsProblemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		LayoutInflater inflater = this.getLayoutInflater();
		View itemView = inflater.inflate(R.layout.card_problem, parent, false);

		return new SsProblemViewHolder(itemView);
	}

	/**
	 * Bind a view holder to the adapter.
	 */
	@Override
	public void onBindViewHolder(SsProblemViewHolder holder, int position)
	{
		List<T> problems = this.getProblems();
		if (problems != null)
		{
			Log.i(TAG, "Binding problem view holder to adapter!");
			T current = problems.get(position);
			//holder.something.setText(current.getWord());
		}
		else
		{
			Log.i(TAG, "Nothing to bind to adapter!");
			//holder.something.setText("No word");
		}
	}

	/**
	 * Set new data into the adapter.
	 */
	public void setProblems(List<T> problems)
	{
		Log.i(TAG, "Setting problems! " + problems.size());
		this.mProblems = problems;
		notifyDataSetChanged();
	}
}
