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
	 * Q/A TextView.
	 */
	protected TextView mQaTextView;

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
		this.getQaTextView().setText(this.getQuestion());
		this.getQaTextView().setVisibility(View.VISIBLE);
		this.getEntryLayout().setVisibility(View.VISIBLE);
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
	 * @return The Q/A TextView.
	 */
	public TextView getQaTextView()
	{
		return this.mQaTextView;
	}

	/**
	 * @return The question to ask in the Q/A TextView.
	 */
	public abstract int getQuestion();

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
	 */
	public void unfocus()
	{
		this.getQaTextView().setVisibility(View.VISIBLE);
		this.getEntryLayout().setVisibility(View.GONE);

		String input = this.getUserInput();

		if (!input.isEmpty())
		{
			this.getQaTextView().setText(input);
		}
	}

}
