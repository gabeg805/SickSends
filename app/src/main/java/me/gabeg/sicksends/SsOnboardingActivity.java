package me.gabeg.sicksends;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


/**
 */
public class SsOnboardingActivity
	extends FragmentActivity
	implements TabLayoutMediator.TabConfigurationStrategy
{

	/**
	 * Onboarding page adapter.
	 */
	public class OnboardingAdapter
		extends FragmentStateAdapter
	{

		/**
		 */
		public OnboardingAdapter(FragmentActivity fragmentActivity)
		{
			super(fragmentActivity);
		}

		/**
		 */
		@NonNull
		@Override
		public Fragment createFragment(int position)
		{
			int layout = this.pageToLayout(position);
			OnboardingFragment fragment = new OnboardingFragment(layout);
			Bundle args = new Bundle();

			args.putInt(OnboardingFragment.BUNDLE_KEY, position);
			fragment.setArguments(args);
			mPageFragments.add(fragment);
			return fragment;
		}

		/**
		 */
		@Override
		public int getItemCount()
		{
			return 3;
		}

		/**
		 * @return The layout ID from the given page.
		 */
		private int pageToLayout(int page)
		{
			switch (page)
			{
				case 2:
					return R.layout.frg_onboarding_grade_choice;
				case 1:
					return R.layout.frg_onboarding_climbing_choice;
				case 0:
				default:
					return R.layout.frg_onboarding_welcome;
			}
		}

	}

	/**
	 * Onboarding fragment.
	 */
	public static class OnboardingFragment
		extends Fragment
		implements MaterialButtonToggleGroup.OnButtonCheckedListener
	{

		/**
		 * Bundle key.
		 */
		private static final String BUNDLE_KEY = "OnboardingFragmentBundleKey";

		/**
		 * Shared preferences.
		 */
		private SsSharedPreferences mSharedPreferences;

		/**
		 */
		public OnboardingFragment(int layout)
		{
			super(layout);
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
		public void onButtonChecked(MaterialButtonToggleGroup group,
			int checkedId, boolean isChecked)
		{
			Context context = getContext();
			SsSharedPreferences shared = this.getSharedPreferences();
			MaterialButton button = group.findViewById(checkedId);
			String grade = button.getText().toString();
			String example = shared.getGradeExample(context, grade);
			int groupId = group.getId();
			int exampleId;

			switch (groupId)
			{
				case R.id.bouldering_grade_choice_group:
					exampleId = R.id.bouldering_grade_example;
					break;
				case R.id.sport_grade_choice_group:
					exampleId = R.id.sport_grade_example;
					break;
				case R.id.top_rope_grade_choice_group:
					exampleId = R.id.top_rope_grade_example;
					break;
				case R.id.trad_grade_choice_group:
					exampleId = R.id.trad_grade_example;
					break;
				default:
					return;
			}

			Animation animation = AnimationUtils.loadAnimation(context,
				R.anim.grade_example_fade);
			TextView textview = (TextView) getView().findViewById(exampleId);
			textview.setText(example);
			textview.startAnimation(animation);
		}

		/**
		 */
		@Override
		public void onViewCreated(@NonNull View view,
			@Nullable Bundle savedInstanceState)
		{
			Bundle args = getArguments();
			int position = args.getInt(BUNDLE_KEY);
			Log.i(TAG, "Fragment position : " + position);
			this.mSharedPreferences = new SsSharedPreferences(getContext());

			if (position == 2)
			{
				MaterialButtonToggleGroup boulderingGroup =
					(MaterialButtonToggleGroup) view.findViewById(
						R.id.bouldering_grade_choice_group);
				MaterialButtonToggleGroup sportGroup =
					(MaterialButtonToggleGroup) view.findViewById(
						R.id.sport_grade_choice_group);
				MaterialButtonToggleGroup topRopeGroup =
					(MaterialButtonToggleGroup) view.findViewById(
						R.id.top_rope_grade_choice_group);
				MaterialButtonToggleGroup tradGroup =
					(MaterialButtonToggleGroup) view.findViewById(
						R.id.trad_grade_choice_group);
				boulderingGroup.addOnButtonCheckedListener(this);
				sportGroup.addOnButtonCheckedListener(this);
				topRopeGroup.addOnButtonCheckedListener(this);
				tradGroup.addOnButtonCheckedListener(this);
			}
		}

	}

	/**
	 * Onboarding page change callback.
	 */
	public class OnboardingPageChange
		extends ViewPager2.OnPageChangeCallback
	{

		/**
		 */
		@Override
		public void onPageSelected(int position)
		{
			Log.i(TAG, "onPageSelected! " + position);
			setNavigationButtonText(position);
			Log.i(TAG, "Offscreen ! " + getPager().getOffscreenPageLimit());

			//saveClimbingTypes();
			//createClimbingGrades();
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
	 * Adapter for the onboarding pages.
	 */
	private OnboardingAdapter mAdapter;

	/**
	 * Page fragments.
	 */
	private List<OnboardingFragment> mPageFragments;

	/**
	 * Previous button.
	 */
	private TextView mPreviousView;

	/**
	 * Next button.
	 */
	private TextView mNextView;

	/**
	 * Next strings.
	 */
	private String[] mNextString;

	/**
	 * @return The onboarding adapter for the view pager.
	 */
	private OnboardingAdapter getAdapter()
	{
		return this.mAdapter;
	}

	/**
	 * @return The onboarding page fragments.
	 */
	private List<OnboardingFragment> getPageFragments()
	{
		return this.mPageFragments;
	}

	/**
	 * @return The onboarding view pager.
	 */
	private ViewPager2 getPager()
	{
		return this.mViewPager;
	}

	/**
	 * @return The shared preferences.
	 */
	private SsSharedPreferences getSharedPreferences()
	{
		return this.mSharedPreferences;
	}

	/**
	 * Move to the next onboarding page.
	 */
	public void nextPage(View v)
	{
		OnboardingAdapter adapter = this.getAdapter();
		ViewPager2 viewPager = this.getPager();
		int nextPage = viewPager.getCurrentItem() + 1;
		int numberOfPages = adapter.getItemCount();

		if (nextPage < numberOfPages)
		{
			viewPager.setCurrentItem(nextPage, true);
		}
		else
		{
			finish();
		}
	}

	/**
	 * Called when a climbing checkbox is selected.
	 */
	public void onClimbingChoiceSelected(View view)
	{
		CompoundButton button = (CompoundButton) view;
		boolean isChecked = button.isChecked();
		this.saveClimbingChoice(button, isChecked);
		this.setupClimbingGrade(button, isChecked);
	}

	/**
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Starting onboarding activity!");
		setContentView(R.layout.act_onboarding);

		ViewPager2 viewPager = findViewById(R.id.onboarding_pager);
		TabLayout tabs = findViewById(R.id.onboarding_location);
		OnboardingAdapter adapter = new OnboardingAdapter(this);
		this.mSharedPreferences = new SsSharedPreferences(this);
		this.mPageFragments = new ArrayList<>();
		this.mPreviousView = findViewById(R.id.onboarding_previous);
		this.mNextView = findViewById(R.id.onboarding_next);
		this.mNextString = getResources().getStringArray(R.array.onboarding_next);
		this.mViewPager = viewPager;
		this.mAdapter = adapter;

		viewPager.setAdapter(adapter);
		viewPager.registerOnPageChangeCallback(new OnboardingPageChange());
		viewPager.setOffscreenPageLimit(adapter.getItemCount());
		new TabLayoutMediator(tabs, viewPager, this).attach();
	}

	/**
	 * TabLayoutMediator configuration strategy.
	 */
	@Override
	public void onConfigureTab(TabLayout.Tab tab, int position)
	{
	}

	/**
	 * Move to the previous onboarding page.
	 */
	public void previousPage(View v)
	{
		ViewPager2 viewPager = this.getPager();
		int previousPage = viewPager.getCurrentItem() - 1;

		if (previousPage >= 0)
		{
			viewPager.setCurrentItem(previousPage, true);
		}
	}

	/**
	 * Save the selected climbing choice.
	 */
	//private void saveClimbingChoice(View view, boolean checked)
	private void saveClimbingChoice(CompoundButton button, boolean isChecked)
	{
		SsSharedPreferences shared = this.getSharedPreferences();
		int id = button.getId();
		//int id = view.getId();

		switch (id)
		{
			case R.id.climbing_bouldering:
				shared.editClimbingTypeBouldering(isChecked);
				break;
			case R.id.climbing_sport:
				shared.editClimbingTypeSport(isChecked);
				break;
			case R.id.climbing_top_rope:
				shared.editClimbingTypeTopRope(isChecked);
				break;
			case R.id.climbing_trad:
				shared.editClimbingTypeTrad(isChecked);
				break;
			default:
				break;
		}

		Log.i(TAG, "Bouldering Checked! " + shared.getClimbingTypeBouldering());
		Log.i(TAG, "Sport Checked! " + shared.getClimbingTypeSport());
		Log.i(TAG, "Top Rope Checked! " + shared.getClimbingTypeTopRope());
		Log.i(TAG, "Trad Checked! " + shared.getClimbingTypeTrad());
	}

	/**
	 * Set the text of the navigation buttons.
	 */
	private void setNavigationButtonText(int position)
	{
		String[] nextString = mNextString;
		mPreviousView.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
		mNextView.setText(position == 2 ? nextString[1] : nextString[0]);
	}

	/**
	 * Setup the climbing grade screen.
	 */
	//private void setupClimbingGrade(View view, boolean checked)
	private void setupClimbingGrade(CompoundButton button, boolean isChecked)
	{
		SsSharedPreferences shared = getSharedPreferences();
		List<OnboardingFragment> pages = getPageFragments();

		if (pages.size() < 3)
		{
			return;
		}

		OnboardingFragment fragment = pages.get(2);
		View root = fragment.getView();
		int visibility = isChecked ? View.VISIBLE : View.GONE;
		int id = button.getId();

		switch (id)
		{
			case R.id.climbing_bouldering:
				root.findViewById(R.id.bouldering_grade_title).setVisibility(visibility);
				root.findViewById(R.id.bouldering_grade_choice).setVisibility(visibility);
				break;
			case R.id.climbing_sport:
				root.findViewById(R.id.sport_grade_title).setVisibility(visibility);
				root.findViewById(R.id.sport_grade_choice).setVisibility(visibility);
				break;
			case R.id.climbing_top_rope:
				root.findViewById(R.id.top_rope_grade_title).setVisibility(visibility);
				root.findViewById(R.id.top_rope_grade_choice).setVisibility(visibility);
				break;
			case R.id.climbing_trad:
				root.findViewById(R.id.trad_grade_title).setVisibility(visibility);
				root.findViewById(R.id.trad_grade_choice).setVisibility(visibility);
				break;
			default:
				return;
		}
	}

}
