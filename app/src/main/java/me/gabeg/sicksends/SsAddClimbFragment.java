package me.gabeg.sicksends;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.Integer;
import java.util.List;

/**
 * Add new climbs.
 */
public class SsAddClimbFragment
	extends Fragment
	implements View.OnClickListener,
		TextView.OnEditorActionListener
{

	public static final String TAG = "SsAddClimbFragment";

	/**
	 * Shared preferences.
	 */
	private SsSharedPreferences mSharedPreferences;

	/**
	 * Name entry box.
	 */
	private TextInputEditText mNameEntry;

	/**
	 * Attempts entry box.
	 */
	private TextInputEditText mAttemptsEntry;

	/**
	 * Button to decrement the number of attempts.
	 */
	private MaterialButton mDecrementAttempts;

	/**
	 * Button to increment the number of attempts.
	 */
	private MaterialButton mIncrementAttempts;

	/**
	 * Section containing all the possible names of grade available.
	 */
	private LinearLayout mGradeNameSection;

	/**
	 * Section containing all the possible grade levels for a given grade name.
	 */
	private LinearLayout mGradeLevelSection;

	/**
	 */
	public void buildGrades(int srcId)
	{
		Log.i(TAG, "Building grades!");
	}

	/**
	 * Change the attempts entry by incrementing or decrementing.
	 */
	private void changeAttemptsEntry(int id)
	{
		TextInputEditText attemptsEntry = this.getAttemptsEntry();
		String text = attemptsEntry.getText().toString();
		int value = text.isEmpty() ? 0 : Integer.parseInt(text);

		if (id == R.id.attempts_decrement)
		{
			value = (value == 0) ? 0 : value-1;
		}
		else if (id == R.id.attempts_increment)
		{
			value += 1;
		}

		attemptsEntry.setText(Integer.toString(value));
	}

	/**
	 * @return The list of grades corresponding to the source fragment that
	 *     started this fragment.
	 */
	//public List<String> getGrades(int srcId)
	//{
	//}

	/**
	 * @return The attempts entry box.
	 */
	private TextInputEditText getAttemptsEntry()
	{
		return this.mAttemptsEntry;
	}

	/**
	 * @return The button decrement the number of attempts.
	 */
	private MaterialButton getDecrementAttempts()
	{
		return this.mDecrementAttempts;
	}

	/**
	 * @return The button increment the number of attempts.
	 */
	private MaterialButton getIncrementAttempts()
	{
		return this.mIncrementAttempts;
	}

	/**
	 * @return The section containing grade levels.
	 */
	private LinearLayout getGradeLevelSection()
	{
		return this.mGradeLevelSection;
	}

	/**
	 * @return The section containing grade names.
	 */
	private LinearLayout getGradeNameSection()
	{
		return this.mGradeNameSection;
	}

	/**
	 * @return The name entry box.
	 */
	private TextInputEditText getNameEntry()
	{
		return this.mNameEntry;
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

		if ((id == R.id.attempts_decrement) || (id == R.id.attempts_increment))
		{
			this.changeAttemptsEntry(id);
		}
		else
		{
			LinearLayout gradeNameSection = this.getGradeNameSection();
			LinearLayout gradeLevelSection = this.getGradeLevelSection();

			if (gradeNameSection.indexOfChild(view) != -1)
			{
				String name = ((MaterialButton)view).getText().toString();
				Log.i(TAG, "Grade name section is visible! " + name);
				this.showGradeLevelSection(name);
			}
			else if (gradeLevelSection.indexOfChild(view) != -1)
			{
				Log.i(TAG, "Grade value section is visible!");
			}
			else
			{
				Log.i(TAG, "AHHHHHH waht's happening!");
			}
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
		int srcId = SsBundle.getAddClimbSrcId(args);

		Log.i(TAG, "onCreateView! " + srcId);
		View root = inflater.inflate(R.layout.frg_add_climb, container, false);

		this.mNameEntry = root.findViewById(R.id.name_entry_edittext);
		this.mAttemptsEntry = root.findViewById(R.id.attempts_entry_edittext);
		this.mDecrementAttempts = root.findViewById(R.id.attempts_decrement);
		this.mIncrementAttempts = root.findViewById(R.id.attempts_increment);
		this.mGradeNameSection = root.findViewById(R.id.grade_entry_name);
		this.mGradeLevelSection = root.findViewById(R.id.grade_entry_level);

		return root;
	}

	/**
	 */
	@Override
	public boolean onEditorAction(TextView tv, int actionId, KeyEvent event)
	{
		Context context = getContext();
		int id = tv.getId();
		Log.i(TAG, "onEditorAction! " + id);

		if (actionId == EditorInfo.IME_ACTION_DONE)
		{
			// Return false hides, return true does not
			//SsSoftKeyboard.hide(context, tv);

			if (id == R.id.name_entry_edittext)
			{
				Log.i(TAG, "onEditorAction! Showing attempts entry!");
				this.showAttemptsSection();
			}
			else if (id == R.id.attempts_entry_edittext)
			{
				Log.i(TAG, "onEditorAction! Showing grade section!");
				this.showGradeSection();
			}
		}

		return false;
	}

	/**
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		Bundle args = getArguments();
		int srcId = SsBundle.getAddClimbSrcId(args);
		TextInputEditText nameEntry = this.getNameEntry();
		TextInputEditText attemptsEntry = this.getAttemptsEntry();

		Log.i(TAG, "onViewCreated! " + srcId);
		Log.i(TAG, "Name    entry : " + nameEntry.getId());
		Log.i(TAG, "Attempt entry : " + attemptsEntry.getId());

		this.setupListeners();
		this.showNameSection();
	}

	/**
	 * Setup the listeners.
	 */
	private void setupListeners()
	{
		TextInputEditText nameEntry = this.getNameEntry();
		TextInputEditText attemptsEntry = this.getAttemptsEntry();
		MaterialButton decrementAttempts = this.getDecrementAttempts();
		MaterialButton incrementAttempts = this.getIncrementAttempts();

		nameEntry.setOnEditorActionListener(this);
		attemptsEntry.setOnEditorActionListener(this);
		decrementAttempts.setOnClickListener(this);
		incrementAttempts.setOnClickListener(this);
	}

	/**
	 * Show the name section.
	 */
	private void showNameSection()
	{
		Context context = getContext();
		TextInputEditText nameEntry = this.getNameEntry();

		SsSoftKeyboard.show(context, nameEntry);
	}

	/**
	 * Show the attempts section.
	 */
	private void showAttemptsSection()
	{
		Context context = getContext();
		TextInputEditText attemptsEntry = this.getAttemptsEntry();

		SsSoftKeyboard.show(context, attemptsEntry);
	}

	/**
	 * Show the grade section.
	 */
	private void showGradeSection()
	{
		this.showGradeNameSection();
	}

	/**
	 * Show the grade section where the user selects the name of the grade they
	 * want to use.
	 */
	private void showGradeNameSection()
	{
		LayoutInflater inflater = getLayoutInflater();
		LinearLayout section = this.getGradeNameSection();
		SsSharedPreferences shared = this.getSharedPreferences();
		List<String> useGrades = shared.getAllUseBoulderGrades();

		for (String grade : useGrades)
		{
			View view = inflater.inflate(R.layout.btn_grade, section, true);
			MaterialButton button = view.findViewById(R.id.grade_button);
			int id = View.generateViewId();

			button.setId(id);
			button.setText(grade);
			button.setOnClickListener(this);
		}
	}

	/**
	 * Show the grade section where the user selects the difficulty grade of
	 * their climb.
	 */
	private void showGradeLevelSection(String name)
	{
		LayoutInflater inflater = getLayoutInflater();
		LinearLayout section = this.getGradeLevelSection();
		SsSharedPreferences shared = this.getSharedPreferences();
		List<String> allGrades = shared.getAllBoulderGrades(name);

		//section.setVisibility(View.VISIBLE);

		for (String grade : allGrades)
		{
			Log.i(TAG, "Showing grade level! " + grade);
			View view = inflater.inflate(R.layout.btn_grade, section, true);
			MaterialButton button = view.findViewById(R.id.grade_button);
			int id = View.generateViewId();

			button.setId(id);
			button.setText(grade);
			button.setOnClickListener(this);
		}
	}

}
