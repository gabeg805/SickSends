package me.gabeg.sicksends;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.Integer;
import java.util.List;

/**
 * Add new climbs.
 */
public class SsAddClimbFragment
	extends Fragment
	implements View.OnClickListener,
		SsAddClimbSection.OnSectionDoneListener
{

	/**
	 * Log tag name.
	 */
	public static final String TAG = "SsAddClimbFragment";

	/**
	 * Shared preferences.
	 */
	private SsSharedPreferences mSharedPreferences;

	/**
	 * Climbing source fragment ID.
	 */
	private int mClimbingSourceId;

	/**
	 */
	private SsAddClimbNameSection mNameSection;

	/**
	 */
	private SsAddClimbAttemptsSection mAttemptsSection;

	/**
	 */
	private SsAddClimbGradeSection mGradeSection;

	/**
	 * @return The climbing source ID.
	 */
	private int getClimbingSourceId()
	{
		return this.mClimbingSourceId;
	}

	/**
	 * @return The shared constants.
	 */
	private SsSharedConstants getSharedConstants()
	{
		return this.getSharedPreferences().getConstants();
	}

	/**
	 * @return The shared preferences.
	 */
	private SsSharedPreferences getSharedPreferences()
	{
		return this.mSharedPreferences;
	}

	/**
	 */
	@Override
	public void onClick(View view)
	{
		int id = view.getId();

		this.mNameSection.unfocus();
		this.mAttemptsSection.unfocus();
		this.mGradeSection.unfocus();

		if (id == R.id.name_section)
		{
			this.mNameSection.focus();
		}
		else if (id == R.id.attempts_section)
		{
			this.mAttemptsSection.focus();
		}
		else if (id == R.id.grade_section)
		{
			this.mGradeSection.focus();
		}
	}

	/**
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Context context = getContext();
		this.mSharedPreferences = new SsSharedPreferences(context);
	}

	/**
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState)
	{
		Bundle args = getArguments();
		View root = inflater.inflate(R.layout.frg_add_climb, container, false);

		this.mClimbingSourceId = SsBundle.getAddClimbSrcId(args);
		this.mNameSection = new SsAddClimbNameSection(root);
		this.mAttemptsSection = new SsAddClimbAttemptsSection(root);
		this.mGradeSection = new SsAddClimbGradeSection(root, inflater);

		Log.i(TAG, "onCreateView! " + this.mClimbingSourceId);

		return root;
	}

	/**
	 */
	@Override
	public void onSectionDone(SsAddClimbSection section)
	{
		Log.i(TAG, "onSectionDone! Showing attempts section");

		if (section instanceof SsAddClimbNameSection)
		{
			this.mAttemptsSection.focus();
		}
		else if (section instanceof SsAddClimbAttemptsSection)
		{
			this.mGradeSection.focus();
		}
		else if (section instanceof SsAddClimbGradeSection)
		{
			Log.i(TAG, "Show next section after grade");
		}
	}

	/**
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		this.setupListeners();
		this.mNameSection.focus();
	}

	/**
	 * Setup the listeners.
	 */
	private void setupListeners()
	{
		this.mNameSection.setOnClickListener(this);
		this.mNameSection.setOnSectionDoneListener(this);
		this.mAttemptsSection.setOnClickListener(this);
		this.mAttemptsSection.setOnSectionDoneListener(this);
		this.mGradeSection.setOnClickListener(this);
		this.mGradeSection.setOnSectionDoneListener(this);
	}

}
