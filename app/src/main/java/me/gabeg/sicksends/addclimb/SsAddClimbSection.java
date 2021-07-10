package me.gabeg.sicksends.addclimb;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * A section in the add climb fragment.
 */
public abstract class SsAddClimbSection
{

	/**
	 * Listener for when the section has been completed by the user.
	 */
	public interface OnSectionDoneListener
	{
		public void onSectionDone(SsAddClimbSection section);
	}

	/**
	 * Entire section view.
	 */
	protected ConstraintLayout mSection;

	/**
	 * Title TextView.
	 */
	protected TextView mTitleTextView;

	/**
	 * Question TextView.
	 */
	protected TextView mQuestionTextView;

	/**
	 * Answer TextView.
	 */
	protected TextView mAnswerTextView;

	/**
	 * Subtitle TextView.
	 */
	protected TextView mSubtitleTextView;

	/**
	 * User entry layout.
	 */
	protected ViewGroup mEntryLayout;

	/**
	 * Listener for when the OnSectionDoneListener is called.
	 */
	private OnSectionDoneListener mOnSectionDoneListener;

	/**
	 */
	public SsAddClimbSection(View root)
	{
	}

	/**
	 * Call the OnSectionDoneListener if it has been set.
	 */
	public void callOnSectionDoneListener()
	{
		OnSectionDoneListener listener = this.getOnSectionDoneListener();

		if (listener != null)
		{
			listener.onSectionDone(this);
		}
	}

	/**
	 * Section has been completed.
	 *
	 * Unfocus the section and call the OnSectionDoneListener if it has been set.
	 */
	public void done()
	{
		this.unfocus();
		this.callOnSectionDoneListener();
	}

	/**
	 * Focus on the current section.
	 */
	public void focus()
	{
		TextView questionTv = this.getQuestionTextView();
		TextView answerTv = this.getAnswerTextView();
		ViewGroup entryLayout = this.getEntryLayout();

		questionTv.setVisibility(View.VISIBLE);
		answerTv.setVisibility(View.GONE);
		entryLayout.setVisibility(View.VISIBLE);
	}

	/**
	 * Check if the section is focused or not.
	 *
	 * @return True if the section is focused, and False otherwise.
	 */
	public boolean isFocused()
	{
		ViewGroup entryLayout = this.getEntryLayout();
		int vis = entryLayout.getVisibility();

		return vis == View.VISIBLE;
	}

	/**
	 * Get the answer TextView.
	 *
	 * @return The answer TextView.
	 */
	public TextView getAnswerTextView()
	{
		return this.mAnswerTextView;
	}

	/**
	 * @return The user entry section.
	 */
	public ViewGroup getEntryLayout()
	{
		return this.mEntryLayout;
	}

	/**
	 * @return The OnSectionDoneListener.
	 */
	public OnSectionDoneListener getOnSectionDoneListener()
	{
		return this.mOnSectionDoneListener;
	}

	/**
	 * Get the question TextView.
	 *
	 * @return The question TextView.
	 */
	public TextView getQuestionTextView()
	{
		return this.mQuestionTextView;
	}

	/**
	 * @return The entire section view.
	 */
	public ConstraintLayout getSection()
	{
		return this.mSection;
	}

	/**
	 * @return The subtitle TextView.
	 */
	public TextView getSubtitleTextView()
	{
		return this.mSubtitleTextView;
	}

	/**
	 * @return The title TextView.
	 */
	public TextView getTitleTextView()
	{
		return this.mTitleTextView;
	}

	/**
	 * @return The data that the user has input.
	 */
	public abstract String getUserInput();

	/**
	 * Set the OnClickListener on the section view.
	 */
	public void setOnClickListener(View.OnClickListener listener)
	{
		this.getSection().setOnClickListener(listener);
	}

	/**
	 * Set the OnSectionDoneListener.
	 */
	public void setOnSectionDoneListener(OnSectionDoneListener listener)
	{
		this.mOnSectionDoneListener = listener;
	}

	/**
	 * Unfocus on the current section.
	 *
	 * If the section is not currently focused, this will do nothing.
	 */
	public void unfocus()
	{
		if (!this.isFocused())
		{
			return;
		}

		TextView questionTv = this.getQuestionTextView();
		TextView answerTv = this.getAnswerTextView();
		ViewGroup entryLayout = this.getEntryLayout();
		String input = this.getUserInput();

		questionTv.setVisibility(View.GONE);
		entryLayout.setVisibility(View.GONE);

		if (input.isEmpty())
		{
			answerTv.setVisibility(View.GONE);
		}
		else
		{
			answerTv.setVisibility(View.VISIBLE);
			answerTv.setText(input);
		}
	}

}
