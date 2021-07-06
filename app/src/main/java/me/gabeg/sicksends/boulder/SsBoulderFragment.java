package me.gabeg.sicksends.boulder;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import me.gabeg.sicksends.R;

/**
 * Show boulder climbs.
 */
public class SsBoulderFragment
	extends Fragment
{

	public static final String TAG = "SsBoulderFragment";

	/**
	 * Boulder view model
	 */
	private SsBoulderViewModel mViewModel;

	/**
	 * @return The boulder view model.
	 */
	private SsBoulderViewModel getViewModel()
	{
		return this.mViewModel;
	}

	/**
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.frg_boulder, container, false);
	}

	/**
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		Context context = getContext();
		RecyclerView rv = view.findViewById(R.id.recycler_view);
		final SsBoulderAdapter adapter = new SsBoulderAdapter(context);
		LinearLayoutManager layoutManager = new LinearLayoutManager(context);

		rv.setAdapter(adapter);
		rv.setLayoutManager(layoutManager);

		this.mViewModel = new ViewModelProvider(this).get(SsBoulderViewModel.class);
		this.mViewModel.getAllProblems().observe(this,
			new Observer<List<SsBoulder>>()
			{
				@Override
				public void onChanged(@Nullable final List<SsBoulder> problems)
				{
					Log.i(TAG, "Setting problems in fragment!");
					adapter.setProblems(problems);
				}
			});
	}

}
