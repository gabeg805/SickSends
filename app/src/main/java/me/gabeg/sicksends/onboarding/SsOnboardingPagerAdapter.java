package me.gabeg.sicksends.onboarding;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import me.gabeg.sicksends.R;

/**
 * Onboarding pager adapter.
 */
public class SsOnboardingPagerAdapter
	extends FragmentStateAdapter
	implements SsOnboardingFragment.OnViewCreatedListener
{

	/**
	 * Listener for when the adapter is done creating fragments.
	 */
	public interface OnAdapterDoneListener
	{
		public void onAdapterDone(SsOnboardingPagerAdapter adapter);
	}

	/**
	 * Log tag.
	 */
	private static final String TAG = "SsOnboardingPagerAdapter";

	/**
	 * Fragments for each page that is created.
	 */
	private SsOnboardingFragment[] mPageFragments;

	/**
	 * Adapter done listener.
	 */
	private OnAdapterDoneListener mOnAdapterDoneListener;

	/**
	 */
	public SsOnboardingPagerAdapter(FragmentActivity fragmentActivity)
	{
		super(fragmentActivity);

		this.mPageFragments = new SsOnboardingFragment[this.getItemCount()];
		this.mOnAdapterDoneListener = null;
	}

	/**
	 * @return Adapter done listener.
	 */
	public void callOnAdapterDoneListener()
	{
		OnAdapterDoneListener listener = this.getOnAdapterDoneListener();
		if (listener != null)
		{
			listener.onAdapterDone(this);
		}
	}

	/**
	 * @return True if the item is contained in the adapter, and False otherwise.
	 */
	@Override
	public boolean containsItem(long itemId)
	{
		int size = this.getItemCount();

		for (int i=0; i < size; i++)
		{
			long id = this.getItemId(i);
			if (id == itemId)
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Create a fragment.
	 */
	@NonNull
	@Override
	public Fragment createFragment(int position)
	{
		SsOnboardingFragment[] pages = this.getPageFragments();
		int layout = (int) this.getItemId(position);
		SsOnboardingFragment fragment = new SsOnboardingFragment(layout);
		Bundle bundle = new Bundle();

		bundle.putInt(SsOnboardingFragment.BUNDLE_KEY, position);
		fragment.setArguments(bundle);
		fragment.setOnViewCreatedListener(this);

		pages[position] = fragment;
		return fragment;
	}

	/**
	 * @return The onboarding page fragments.
	 */
	public SsOnboardingFragment getFragment(int position)
	{
		return this.getPageFragments()[position];
	}

	/**
	 * @return Number of pages handled by the adapter.
	 */
	@Override
	public int getItemCount()
	{
		return 3;
	}

	/**
	 * @return The layout ID from the given page.
	 */
	@Override
	public long getItemId(int position)
	{
		switch (position)
		{
			case 2:
				return R.layout.frg_onboarding_grade_choice;
			case 1:
				return R.layout.frg_onboarding_climbing_choice;
			case 0:
				return R.layout.frg_onboarding_welcome;
			default:
				return RecyclerView.NO_ID;
		}
	}

	/**
	 * @return OnAdapterDoneListener.
	 */
	public OnAdapterDoneListener getOnAdapterDoneListener()
	{
		return this.mOnAdapterDoneListener;
	}

	/**
	 * @return The onboarding page fragments.
	 */
	public SsOnboardingFragment[] getPageFragments()
	{
		return this.mPageFragments;
	}

	/**
	 * @return The root view of the fragment on the given page.
	 */
	public View getRootView(int position)
	{
		Fragment fragment = this.getFragment(position);
		return (fragment != null) ? fragment.getView() : null;
	}

	/**
	 * @return True if done creating fragment pages, and False otherwise.
	 */
	public boolean isDone()
	{
		SsOnboardingFragment[] pages = this.getPageFragments();
		for (SsOnboardingFragment fragment : pages)
		{
			if (fragment == null)
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Called when a fragment's view has been created.
	 */
	@Override
	public void onFragmentViewCreated(SsOnboardingFragment fragment)
	{
		if (this.isDone())
		{
			this.callOnAdapterDoneListener();
		}
	}

	/**
	 * Set the OnAdapterDoneListener.
	 */
	public void setOnAdapterDoneListener(OnAdapterDoneListener listener)
	{
		this.mOnAdapterDoneListener = listener;
	}

}
