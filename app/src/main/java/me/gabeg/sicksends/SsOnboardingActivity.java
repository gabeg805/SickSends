package me.gabeg.sicksends;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

/**
 */
public class SsOnboardingActivity
	extends FragmentActivity
	implements TabLayoutMediator.TabConfigurationStrategy,
		MaterialButtonToggleGroup.OnButtonCheckedListener,
		SsOnboardingPagerAdapter.OnAdapterDoneListener
{

	/**
	 * Onboarding page change callback.
	 */
	public class OnboardingPageChangeCallback
		extends ViewPager2.OnPageChangeCallback
	{

		/**
		 * Called when the scroll state is changed.
		 */
		@Override
		public void onPageScrollStateChanged(int state)
		{
			ViewPager2 viewPager = getViewPager();
			int position = viewPager.getCurrentItem();

			if ((state == ViewPager2.SCROLL_STATE_IDLE) && (position == 2))
			{
				SsSharedPreferences shared = getSharedPreferences();
				SsSharedConstants cons = getSharedPreferences().getConstants();

				if (!shared.getWillClimb())
				{
					Context context = getApplicationContext();
					String text = cons.getErrorMessageNoClimbingTypeSelected();

					viewPager.setCurrentItem(1, true);
					Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
				}
			}
		}

		/**
		 * Called when a page has been selected.
		 */
		@Override
		public void onPageSelected(int position)
		{
			setNavigationButtonText(position);
		}

	}

	/**
	 * Log tag.
	 */
	private static final String TAG = "SsOnboardingActivity";

	/**
	 * Shared preferences.
	 */
	private SsSharedPreferences mSharedPreferences;

	/**
	 * View pager.
	 */
	private ViewPager2 mViewPager;

	/**
	 * @return True if the page can be changed to the given position, and False
	 *     otherwise.
	 */
	private boolean canChangePage(int position)
	{
		SsOnboardingPagerAdapter adapter = this.getOnboardingPagerAdapter();
		int maxPages = adapter.getItemCount();

		return (position >= 0) && (position < maxPages);
	}

	/**
	 * @return ID for the example grade TextView. Chosen by checking against the
	 *     climbing type that was selected.
	 */
	private int getExampleGradeId(MaterialButtonToggleGroup group)
	{
		int id = group.getId();
		if (id == R.id.bouldering_grade_choice_group)
		{
			return R.id.bouldering_grade_example;
		}
		else if (id == R.id.sport_grade_choice_group)
		{
			return R.id.sport_grade_example;
		}
		else if (id == R.id.top_rope_grade_choice_group)
		{
			return R.id.top_rope_grade_example;
		}
		else if (id == R.id.trad_grade_choice_group)
		{
			return R.id.trad_grade_example;
		}
		else
		{
			return View.NO_ID;
		}
	}

	/**
	 * @return Text for the example grade TextView.
	 */
	private String getExampleGradeText(MaterialButtonToggleGroup group,
		int checkedId)
	{
		SsSharedPreferences shared = this.getSharedPreferences();
		MaterialButton button = group.findViewById(checkedId);
		String grade = button.getText().toString();

		return shared.getGradeExample(grade);
	}

	/**
	 * @return The onboarding adapter for the view pager.
	 */
	private SsOnboardingPagerAdapter getOnboardingPagerAdapter()
	{
		return (SsOnboardingPagerAdapter) this.getViewPager().getAdapter();
	}

	/**
	 * @return The shared preferences.
	 */
	private SsSharedPreferences getSharedPreferences()
	{
		return this.mSharedPreferences;
	}

	/**
	 * @return The onboarding view pager.
	 */
	private ViewPager2 getViewPager()
	{
		return this.mViewPager;
	}

	/**
	 * Called when the adapter is done creating views.
	 */
	@Override
	public void onAdapterDone(SsOnboardingPagerAdapter adapter)
	{
		View root = adapter.getRootView(2);
		MaterialButtonToggleGroup boulderingGroup = root.findViewById(
			R.id.bouldering_grade_choice_group);
		MaterialButtonToggleGroup sportGroup = root.findViewById(
			R.id.sport_grade_choice_group);
		MaterialButtonToggleGroup topRopeGroup = root.findViewById(
			R.id.top_rope_grade_choice_group);
		MaterialButtonToggleGroup tradGroup = root.findViewById(
			R.id.trad_grade_choice_group);

		boulderingGroup.addOnButtonCheckedListener(this);
		sportGroup.addOnButtonCheckedListener(this);
		topRopeGroup.addOnButtonCheckedListener(this);
		tradGroup.addOnButtonCheckedListener(this);
	}

	/**
	 * Called when a climbing grade is selected.
	 */
	@Override
	public void onButtonChecked(MaterialButtonToggleGroup group,
		int checkedId, boolean isChecked)
	{
		String example = this.getExampleGradeText(group, checkedId);
		int id = this.getExampleGradeId(group);

		SsOnboardingPagerAdapter adapter = this.getOnboardingPagerAdapter();
		View root = adapter.getRootView(2);
		TextView textview = root.findViewById(id);

		Animation animation = AnimationUtils.loadAnimation(this,
			R.anim.grade_example_fade);

		textview.setText(example);
		textview.startAnimation(animation);
	}

	/**
	 * Called when a climbing type is selected.
	 */
	public void onClimbingTypeSelected(View view)
	{
		SsSharedPreferences shared = this.getSharedPreferences();
		boolean state = ((CompoundButton)view).isChecked();
		int visibility = state ? View.VISIBLE : View.GONE;
		int id = view.getId();

		if (id == R.id.climbing_bouldering)
		{
			shared.editWillClimbBouldering(state);
			this.setBoulderingGradeVisibility(visibility);
		}
		else if (id == R.id.climbing_sport)
		{
			shared.editWillClimbSport(state);
			this.setSportGradeVisibility(visibility);
		}
		else if (id == R.id.climbing_top_rope)
		{
			shared.editWillClimbTopRope(state);
			this.setTopRopeGradeVisibility(visibility);
		}
		else if (id == R.id.climbing_trad)
		{
			shared.editWillClimbTrad(state);
			this.setTradGradeVisibility(visibility);
		}
	}

	/**
	 * TabLayoutMediator configuration strategy.
	 */
	@Override
	public void onConfigureTab(TabLayout.Tab tab, int position)
	{
	}

	/**
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_onboarding);

		this.mSharedPreferences = new SsSharedPreferences(this);
		this.mViewPager = findViewById(R.id.onboarding_pager);

		this.setupViewPager();
		this.setupTabLayoutMediator();
	}

	/**
	 * Move to the next onboarding page.
	 */
	public void onNextPageClicked(View v)
	{
		ViewPager2 viewPager = this.getViewPager();
		int next = viewPager.getCurrentItem() + 1;

		if (this.canChangePage(next))
		{
			viewPager.setCurrentItem(next, true);
		}
		else
		{
			finish();
		}
	}

	/**
	 * Move to the previous onboarding page.
	 */
	public void onPreviousPageClicked(View v)
	{
		ViewPager2 viewPager = this.getViewPager();
		int previous = viewPager.getCurrentItem() - 1;

		if (this.canChangePage(previous))
		{
			viewPager.setCurrentItem(previous, true);
		}
	}

	/**
	 * Set the text of the navigation buttons.
	 */
	private void setNavigationButtonText(int position)
	{
		Button previousButton = findViewById(R.id.onboarding_previous);
		Button nextButton = findViewById(R.id.onboarding_next);
		SsSharedConstants cons = this.getSharedPreferences().getConstants();

		String previousText = (position > 0) ? cons.getNavigatePrevious() : "";
		String nextText = (position < 2) ? cons.getNavigateNext()
			: cons.getNavigateFinish();

		previousButton.setText(previousText);
		nextButton.setText(nextText);
	}

	/**
	 * Set visibility of bouldering grades.
	 */
	private void setBoulderingGradeVisibility(int visibility)
	{
		SsOnboardingPagerAdapter adapter = this.getOnboardingPagerAdapter();
		View root = adapter.getRootView(2);
		View title = root.findViewById(R.id.bouldering_grade_title);
		View choice = root.findViewById(R.id.bouldering_grade_choice);

		title.setVisibility(visibility);
		choice.setVisibility(visibility);
	}

	/**
	 * Set visibility of sport grades.
	 */
	private void setSportGradeVisibility(int visibility)
	{
		SsOnboardingPagerAdapter adapter = this.getOnboardingPagerAdapter();
		View root = adapter.getRootView(2);
		View title = root.findViewById(R.id.sport_grade_title);
		View choice = root.findViewById(R.id.sport_grade_choice);

		title.setVisibility(visibility);
		choice.setVisibility(visibility);
	}

	/**
	 * Set visibility of top rope grades.
	 */
	private void setTopRopeGradeVisibility(int visibility)
	{
		SsOnboardingPagerAdapter adapter = this.getOnboardingPagerAdapter();
		View root = adapter.getRootView(2);
		View title = root.findViewById(R.id.top_rope_grade_title);
		View choice = root.findViewById(R.id.top_rope_grade_choice);

		title.setVisibility(visibility);
		choice.setVisibility(visibility);
	}

	/**
	 * Set visibility of trade grades.
	 */
	private void setTradGradeVisibility(int visibility)
	{
		SsOnboardingPagerAdapter adapter = this.getOnboardingPagerAdapter();
		View root = adapter.getRootView(2);
		View title = root.findViewById(R.id.trad_grade_title);
		View choice = root.findViewById(R.id.trad_grade_choice);

		title.setVisibility(visibility);
		choice.setVisibility(visibility);
	}

	/**
	 * Setup the TabLayoutMediator associated with the Onboarding ViewPager.
	 */
	private void setupTabLayoutMediator()
	{
		ViewPager2 viewPager = this.getViewPager();
		TabLayout tabs = findViewById(R.id.onboarding_location);

		new TabLayoutMediator(tabs, viewPager, this).attach();
	}

	/**
	 * Setup the ViewPager, which allows a user to navigate between different
	 * pages of content.
	 */
	private void setupViewPager()
	{
		ViewPager2 viewPager = this.getViewPager();
		SsOnboardingPagerAdapter adapter = new SsOnboardingPagerAdapter(this);
		OnboardingPageChangeCallback pageChangeCallback =
			new OnboardingPageChangeCallback();

		adapter.setOnAdapterDoneListener(this);
		viewPager.setAdapter(adapter);
		viewPager.registerOnPageChangeCallback(pageChangeCallback);
		viewPager.setOffscreenPageLimit(adapter.getItemCount());
	}

}
