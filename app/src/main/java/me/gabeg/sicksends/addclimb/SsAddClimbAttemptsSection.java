package me.gabeg.sicksends.addclimb;

import android.content.Context;
import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import me.gabeg.sicksends.R;
import me.gabeg.sicksends.system.SsSoftKeyboard;

/**
 * Attempts section for the fragment for adding climbs.
 */
public class SsAddClimbAttemptsSection
	extends SsAddClimbSection
	implements View.OnClickListener,
		TextView.OnEditorActionListener
{

	/**
	 * Log tag name.
	 */
	public static final String TAG = "SsAddClimbAttemptsSection";

	/**
	 * Attempts entry box.
	 */
	private TextInputEditText mAttemptsEntry;

	/**
	 * Button to decrement the number of attempts.
	 */
	private MaterialButton mDecrementButton;

	/**
	 * Button to increment the number of attempts.
	 */
	private MaterialButton mIncrementButton;

	/**
	 */
	public SsAddClimbAttemptsSection(View root)
	{
		super(root);

		this.mSection = root.findViewById(R.id.attempts_section);
		this.mQuestionTextView = root.findViewById(R.id.attempts_question);
		this.mAnswerTextView = root.findViewById(R.id.attempts_answer);
		this.mEntryLayout = root.findViewById(R.id.attempts_entry);
		this.mAttemptsEntry = root.findViewById(R.id.attempts_entry_edittext);
		this.mDecrementButton = root.findViewById(R.id.attempts_decrement);
		this.mIncrementButton = root.findViewById(R.id.attempts_increment);

		this.getAttemptsEntry().setOnEditorActionListener(this);
		this.getDecrementButton().setOnClickListener(this);
		this.getIncrementButton().setOnClickListener(this);
	}

	/**
	 * Change the attempts entry by incrementing or decrementing.
	 *
	 * @param  id  View ID for the increment/decrement buttons.
	 */
	private void changeAttemptsEntry(int id)
	{
		TextInputEditText entry = this.getAttemptsEntry();
		String text = entry.getText().toString();
		int value = text.isEmpty() ? 0 : Integer.parseInt(text);

		if (id == R.id.attempts_decrement)
		{
			value = (value == 0) ? 0 : value-1;
		}
		else if (id == R.id.attempts_increment)
		{
			value += 1;
		}

		entry.setText(Integer.toString(value));
	}

	/**
	 * @see SsAddClimbSection#focus()
	 */
	@Override
	public void focus()
	{
		super.focus();

		TextInputEditText entry = this.getAttemptsEntry();
		Context context = entry.getContext();

		SsSoftKeyboard.show(context, entry);
	}

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
	private MaterialButton getDecrementButton()
	{
		return this.mDecrementButton;
	}

	/**
	 * @return The button increment the number of attempts.
	 */
	private MaterialButton getIncrementButton()
	{
		return this.mIncrementButton;
	}

	/**
	 * @see SsAddClimbSection#getUserInput()
	 */
	public String getUserInput()
	{
		TextInputEditText entry = this.getAttemptsEntry();
		return entry.getText().toString();
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
	}

	/**
	 */
	@Override
	public boolean onEditorAction(TextView tv, int actionId, KeyEvent event)
	{
		if (actionId == EditorInfo.IME_ACTION_DONE)
		{
			this.done();
		}

		return false;
	}

}
