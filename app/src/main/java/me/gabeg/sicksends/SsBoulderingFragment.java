package me.gabeg.sicksends;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Show bouldering climbs.
 */
public class SsBoulderingFragment
	extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.frg_bouldering, container, false);
	}

}
