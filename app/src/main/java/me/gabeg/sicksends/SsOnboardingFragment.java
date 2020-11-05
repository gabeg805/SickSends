package me.gabeg.sicksends;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Onboarding fragment.
 */
public class SsOnboardingFragment
	extends Fragment
{

	/**
	 * Listener for when the adapter is done creating fragments.
	 */
	public interface OnViewCreatedListener
	{
		public void onFragmentViewCreated(SsOnboardingFragment fragment);
	}

	/**
	 * Log tag.
	 */
	private static final String TAG = "SsOnboardingFragment";

	/**
	 * Bundle key.
	 */
	public static final String BUNDLE_KEY = "OnboardingFragmentBundleKey";

	/**
	 * Listener for when the fragment's view has been created.
	 */
	private OnViewCreatedListener mOnViewCreatedListener;

	/**
	 */
	public SsOnboardingFragment(int layout)
	{
		super(layout);
	}

	/**
	 * Call the listener for OnFragmentViewCreatedListener.
	 */
	public void callOnViewCreatedListener()
	{
		OnViewCreatedListener listener = this.getOnViewCreatedListener();
		if (listener != null)
		{
			listener.onFragmentViewCreated(this);
		}
	}

	/**
	 * @return OnViewCreatedListener.
	 */
	public OnViewCreatedListener getOnViewCreatedListener()
	{
		return this.mOnViewCreatedListener;
	}

	/**
	 * Called when the fragment's view is created.
	 */
	@Override
	public void onViewCreated(@NonNull View view,
		@Nullable Bundle savedInstanceState)
	{
		this.callOnViewCreatedListener();
	}

	/**
	 * Set the OnViewCreatedListener.
	 */
	public void setOnViewCreatedListener(OnViewCreatedListener listener)
	{
		this.mOnViewCreatedListener = listener;
	}

}
