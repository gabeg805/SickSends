package me.gabeg.sicksends.addclimb;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import me.gabeg.sicksends.R;
import me.gabeg.sicksends.system.SsBundle;
import me.gabeg.sicksends.shared.SsSharedConstants;
import me.gabeg.sicksends.shared.SsSharedPreferences;

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
	 * Name of a climb.
	 */
	private SsAddClimbNameSection mNameSection;

	/**
	 * Number of attempts.
	 */
	private SsAddClimbAttemptsSection mAttemptsSection;

	/**
	 * Grading system of the climb.
	 */
	private SsAddClimbGradingSystemSection mGradingSystemSection;

	/**
	 * Grade of the climb.
	 */
	private SsAddClimbGradeSection mGradeSection;

	/**
	 * Feel scale.
	 */
	private SsAddClimbFeelScaleSection mFeelScaleSection;

	/**
	 * Types of holds.
	 */
	private SsAddClimbHoldTypeSection mHoldTypeSection;

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

		// Only need to unfocus the correct one
		this.mNameSection.unfocus();
		this.mAttemptsSection.unfocus();
		this.mGradingSystemSection.unfocus();
		this.mGradeSection.unfocus();
		this.mFeelScaleSection.unfocus();
		this.mHoldTypeSection.unfocus();
		Log.i(TAG, "onClick()!");

		if (id == R.id.name_section)
		{
			Log.i(TAG, "Focusing the name section");
			this.mNameSection.focus();
		}
		else if (id == R.id.attempts_section)
		{
			Log.i(TAG, "Focusing the attempts section");
			this.mAttemptsSection.focus();
		}
		else if (id == R.id.grade_system_section)
		{
			Log.i(TAG, "Focusing the grading system section");
			this.mGradingSystemSection.focus();
		}
		else if (id == R.id.grade_section)
		{
			Log.i(TAG, "Focusing the grade section");
			this.mGradeSection.focus();
		}
		else if (id == R.id.feel_scale_section)
		{
			Log.i(TAG, "Focusing the feel scale section");
			this.mFeelScaleSection.focus();
		}
		else if (id == R.id.hold_type_section)
		{
			Log.i(TAG, "Focusing the hold type section");
			this.mHoldTypeSection.focus();
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
		this.mGradingSystemSection = new SsAddClimbGradingSystemSection(root, inflater);
		this.mGradeSection = new SsAddClimbGradeSection(root, inflater);
		this.mFeelScaleSection = new SsAddClimbFeelScaleSection(root);
		this.mHoldTypeSection = new SsAddClimbHoldTypeSection(root);

		Log.i(TAG, "onCreateView! " + this.mClimbingSourceId);

		return root;
	}

	/**
	 */
	@Override
	public void onSectionDone(SsAddClimbSection section)
	{
		if (section instanceof SsAddClimbNameSection)
		{
			Log.i(TAG, "onSectionDone! Showing attempts section");
			this.mAttemptsSection.focus();
		}
		else if (section instanceof SsAddClimbAttemptsSection)
		{
			Log.i(TAG, "onSectionDone! Showing grading system section");
			this.mGradingSystemSection.focus();
		}
		else if (section instanceof SsAddClimbGradingSystemSection)
		{
			String systemName = this.mGradingSystemSection.getUserInput();
			Log.i(TAG, "onSectionDone! Showing grade section! " + systemName + "~");
			this.mGradeSection.setGradingSystemName(systemName);
			this.mGradeSection.focus();
		}
		else if (section instanceof SsAddClimbGradeSection)
		{
			Log.i(TAG, "Show feel scale section");
			this.mFeelScaleSection.focus();
		}
		else if (section instanceof SsAddClimbFeelScaleSection)
		{
			Log.i(TAG, "Show next section after feel scale");
			this.mHoldTypeSection.focus();
		}
		else if (section instanceof SsAddClimbHoldTypeSection)
		{
			Log.i(TAG, "Show next section after hold type");
		}
		else
		{
			Log.i(TAG, "Not showing any other section");
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
		this.mGradingSystemSection.setOnClickListener(this);
		this.mGradingSystemSection.setOnSectionDoneListener(this);
		this.mGradeSection.setOnClickListener(this);
		this.mGradeSection.setOnSectionDoneListener(this);
		this.mFeelScaleSection.setOnClickListener(this);
		this.mFeelScaleSection.setOnSectionDoneListener(this);
		this.mHoldTypeSection.setOnClickListener(this);
		this.mHoldTypeSection.setOnSectionDoneListener(this);
	}

}
