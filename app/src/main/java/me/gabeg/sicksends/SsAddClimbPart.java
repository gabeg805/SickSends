package me.gabeg.sicksends;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * A part of a section in the add climb fragment.
 */
public abstract class SsAddClimbPart
{

	/**
	 * Listener for when the section has been completed by the user.
	 */
	public interface OnPartDoneListener
	{
		public void onPartDone(SsAddClimbPart part);
	}

	/**
	 * User entry part.
	 */
	protected ViewGroup mPartLayout;

	/**
	 * Listener for when the OnPartDoneListener is called.
	 */
	private OnPartDoneListener mOnPartDoneListener;

	/**
	 */
	//public SsAddClimbPart(View root)
	//{
	//}

	/**
	 */
	public SsAddClimbPart(View root, LayoutInflater inflater)
	{
	}

	/**
	 * Call the OnPartDoneListener if it has been set.
	 */
	public void callOnPartDoneListener()
	{
		OnPartDoneListener listener = this.getOnPartDoneListener();

		if (listener != null)
		{
			listener.onPartDone(this);
		}
	}

	/**
	 * Section has been completed.
	 *
	 * Unfocus the section and call the OnPartDoneListener if it has been set.
	 */
	public void done()
	{
		this.callOnPartDoneListener();
	}

	/**
	 * Focus on the current part.
	 */
	public abstract void focus();

	/**
	 * @return The user entry section.
	 */
	public ViewGroup getPartLayout()
	{
		return this.mPartLayout;
	}

	/**
	 * @return The OnPartDoneListener.
	 */
	public OnPartDoneListener getOnPartDoneListener()
	{
		return this.mOnPartDoneListener;
	}

	/**
	 * Set the OnPartDoneListener.
	 */
	public void setOnPartDoneListener(OnPartDoneListener listener)
	{
		this.mOnPartDoneListener = listener;
	}

}
