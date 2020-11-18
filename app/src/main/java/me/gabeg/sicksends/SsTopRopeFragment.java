package me.gabeg.sicksends;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Show top rope climbs.
 */
public class SsTopRopeFragment
	extends Fragment
{

	/**
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.frg_top_rope, container, false);
	}

	/**
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		Context context = getContext();
		RecyclerView rv = view.findViewById(R.id.recycler_view);
		final SsTopRopeAdapter adapter = new SsTopRopeAdapter(context);
		LinearLayoutManager layoutManager = new LinearLayoutManager(context);

		rv.setAdapter(adapter);
		rv.setLayoutManager(layoutManager);
	}

}
