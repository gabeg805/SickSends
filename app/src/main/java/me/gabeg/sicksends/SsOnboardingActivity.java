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
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

/**
 */
public class SsOnboardingActivity
	extends FragmentActivity
	implements TabLayoutMediator.TabConfigurationStrategy,
		CompoundButton.OnCheckedChangeListener,
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
		this.setupClimbingChoices(adapter);
		this.setupGradeChoices(adapter);
	}

	/**
	 * Called when a climbing grade is selected.
	 */
	@Override
	public void onButtonChecked(MaterialButtonToggleGroup group,
		int checkedId, boolean isChecked)
	{
		SsSharedPreferences shared = this.getSharedPreferences();
		MaterialButton button = group.findViewById(checkedId);
		String grade = button.getText().toString();
		String example = shared.getGradeExample(grade);
		int id = group.getId();

		if (id == R.id.grade_choices_boulder)
		{
			shared.editUseGradeBoulder(grade, isChecked);
			this.showExampleGradeBoulder(example);
		}
		else if (id == R.id.grade_choices_lead)
		{
			shared.editUseGradeLead(grade, isChecked);
			this.showExampleGradeLead(example);
		}
		//else if (id == R.id.grade_choices_sport)
		//{
		//	shared.editUseGradeSport(grade, isChecked);
		//	this.showExampleGradeSport(example);
		//}
		else if (id == R.id.grade_choices_top_rope)
		{
			shared.editUseGradeTopRope(grade, isChecked);
			this.showExampleGradeTopRope(example);
		}
		else if (id == R.id.grade_choices_trad)
		{
			shared.editUseGradeTrad(grade, isChecked);
			this.showExampleGradeTrad(example);
		}
	}

	/**
	 * Called when a climbing type is selected.
	 */
	@Override
	public void onCheckedChanged(CompoundButton button, boolean isChecked)
	{
		SsSharedPreferences shared = this.getSharedPreferences();
		int visibility = isChecked ? View.VISIBLE : View.GONE;
		int id = button.getId();

		if (id == R.id.climbing_choice_boulder)
		{
			shared.editWillClimbBoulder(isChecked);
			this.setGradeVisibilityBoulder(visibility);
		}
		else if (id == R.id.climbing_choice_lead)
		{
			shared.editWillClimbLead(isChecked);
			this.setGradeVisibilityLead(visibility);
		}
		//else if (id == R.id.climbing_choice_sport)
		//{
		//	shared.editWillClimbSport(isChecked);
		//	this.setGradeVisibilitySport(visibility);
		//}
		else if (id == R.id.climbing_choice_top_rope)
		{
			shared.editWillClimbTopRope(isChecked);
			this.setGradeVisibilityTopRope(visibility);
		}
		else if (id == R.id.climbing_choice_trad)
		{
			shared.editWillClimbTrad(isChecked);
			this.setGradeVisibilityTrad(visibility);
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
	 * Set the visibility of a set of climbing grades for a climbing type.
	 */
	private void setGradeVisibility(int id, int visibility)
	{
		SsOnboardingPagerAdapter adapter = this.getOnboardingPagerAdapter();
		View root = adapter.getRootView(2);
		View view = root.findViewById(id);

		view.setVisibility(visibility);
	}

	/**
	 * Set visibility of boulder grades.
	 */
	private void setGradeVisibilityBoulder(int visibility)
	{
		this.setGradeVisibility(R.id.boulder_grade_root, visibility);
	}

	/**
	 * Set visibility of lead grades.
	 */
	private void setGradeVisibilityLead(int visibility)
	{
		this.setGradeVisibility(R.id.lead_grade_root, visibility);
	}

	///**
	// * Set visibility of sport grades.
	// */
	//private void setGradeVisibilitySport(int visibility)
	//{
	//	this.setGradeVisibility(R.id.sport_grade_root, visibility);
	//}

	/**
	 * Set visibility of top rope grades.
	 */
	private void setGradeVisibilityTopRope(int visibility)
	{
		this.setGradeVisibility(R.id.top_rope_grade_root, visibility);
	}

	/**
	 * Set visibility of trade grades.
	 */
	private void setGradeVisibilityTrad(int visibility)
	{
		this.setGradeVisibility(R.id.trad_grade_root, visibility);
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
	 * Setup the climbing choices.
	 */
	private void setupClimbingChoices(SsOnboardingPagerAdapter adapter)
	{
		SsSharedPreferences shared = this.getSharedPreferences();
		View root = adapter.getRootView(1);
		SwitchMaterial boulder = root.findViewById(R.id.climbing_choice_boulder);
		SwitchMaterial lead = root.findViewById(R.id.climbing_choice_lead);
		//SwitchMaterial sport = root.findViewById(R.id.climbing_choice_sport);
		SwitchMaterial topRope = root.findViewById(R.id.climbing_choice_top_rope);
		SwitchMaterial trad = root.findViewById(R.id.climbing_choice_trad);

		boolean willBoulder = shared.getWillClimbBoulder();
		boolean willLead = shared.getWillClimbLead();
		//boolean willSport = shared.getWillClimbSport();
		boolean willTopRope = shared.getWillClimbTopRope();
		boolean willTrad = shared.getWillClimbTrad();

		boulder.setOnCheckedChangeListener(this);
		lead.setOnCheckedChangeListener(this);
		//sport.setOnCheckedChangeListener(this);
		topRope.setOnCheckedChangeListener(this);
		trad.setOnCheckedChangeListener(this);

		boulder.setChecked(willBoulder);
		lead.setChecked(willLead);
		//sport.setChecked(willSport);
		topRope.setChecked(willTopRope);
		trad.setChecked(willTrad);
	}

	/**
	 * Setup the grade choices.
	 */
	private void setupGradeChoices(SsOnboardingPagerAdapter adapter)
	{
		View root = adapter.getRootView(2);
		MaterialButtonToggleGroup boulder = root.findViewById(R.id.grade_choices_boulder);
		MaterialButtonToggleGroup lead = root.findViewById(R.id.grade_choices_lead);
		//MaterialButtonToggleGroup sport = root.findViewById(R.id.grade_choices_sport);
		MaterialButtonToggleGroup topRope = root.findViewById(R.id.grade_choices_top_rope);
		MaterialButtonToggleGroup trad = root.findViewById(R.id.grade_choices_trad);

		boulder.addOnButtonCheckedListener(this);
		lead.addOnButtonCheckedListener(this);
		//sport.addOnButtonCheckedListener(this);
		topRope.addOnButtonCheckedListener(this);
		trad.addOnButtonCheckedListener(this);
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

	/**
	 * Show the example grade for a climbing type.
	 */
	private void showExampleGrade(int id, String example)
	{
		SsOnboardingPagerAdapter adapter = this.getOnboardingPagerAdapter();
		View root = adapter.getRootView(2);
		TextView textview = root.findViewById(id);

		Animation animation = AnimationUtils.loadAnimation(this,
			R.anim.grade_example_fade);

		textview.setText(example);
		textview.startAnimation(animation);
	}

	/**
	 * Show the example grade for boulder.
	 */
	private void showExampleGradeBoulder(String example)
	{
		this.showExampleGrade(R.id.grade_example_boulder, example);
	}

	/**
	 * Show the example grade for lead climbing.
	 */
	private void showExampleGradeLead(String example)
	{
		this.showExampleGrade(R.id.grade_example_lead, example);
	}

	///**
	// * Show the example grade for sport climbing.
	// */
	//private void showExampleGradeSport(String example)
	//{
	//	this.showExampleGrade(R.id.grade_example_sport, example);
	//}

	/**
	 * Show the example grade for top rope climbing.
	 */
	private void showExampleGradeTopRope(String example)
	{
		this.showExampleGrade(R.id.grade_example_top_rope, example);
	}

	/**
	 * Show the example grade for trad climbing.
	 */
	private void showExampleGradeTrad(String example)
	{
		this.showExampleGrade(R.id.grade_example_trad, example);
	}

}
