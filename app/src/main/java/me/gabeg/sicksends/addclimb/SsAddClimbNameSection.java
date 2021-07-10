package me.gabeg.sicksends.addclimb;

import android.content.Context;
import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;

import me.gabeg.sicksends.R;
import me.gabeg.sicksends.system.SsSoftKeyboard;

/**
 * Name section for the fragment for adding climbs.
 */
public class SsAddClimbNameSection
	extends SsAddClimbSection
	implements TextView.OnEditorActionListener
{

	/**
	 * Log tag name.
	 */
	public static final String TAG = "SsAddClimbNameSection";

	/**
	 * Name entry box.
	 */
	private TextInputEditText mNameEntry;

	/**
	 */
	public SsAddClimbNameSection(View root)
	{
		super(root);

		this.mSection = root.findViewById(R.id.name_section);
		this.mQuestionTextView = root.findViewById(R.id.name_question);
		this.mAnswerTextView = root.findViewById(R.id.name_answer);
		this.mEntryLayout = root.findViewById(R.id.name_entry);
		this.mNameEntry = root.findViewById(R.id.name_entry_edittext);

		this.getNameEntry().setOnEditorActionListener(this);
	}

	/**
	 * @see SsAddClimbSection#focus()
	 */
	@Override
	public void focus()
	{
		super.focus();

		TextInputEditText entry = this.getNameEntry();
		Context context = entry.getContext();

		SsSoftKeyboard.show(context, entry);
	}

	/**
	 * @return The name entry box.
	 */
	private TextInputEditText getNameEntry()
	{
		return this.mNameEntry;
	}

	/**
	 * @see SsAddClimbSection#getUserInput()
	 */
	public String getUserInput()
	{
		TextInputEditText entry = this.getNameEntry();
		return entry.getText().toString();
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
