package me.gabeg.sicksends;

//import android.app.PendingIntent;
//import android.content.res.ColorStateList;
//import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

/**
 * The application's main activity.
 */
public class SsMainActivity
	extends AppCompatActivity
	implements NavigationView.OnNavigationItemSelectedListener
	//implements View.OnClickListener
{

	private static final String TAG = "SsMainActivity";

	public CollapsingToolbarLayout mCollapsingLayout;
	public Toolbar mToolbar;
	public DrawerLayout mDrawerLayout;
	public NavController mNavController;
	public NavigationView mNavigationView;

	/**
	 * Shared preferences.
	 */
	private SsSharedPreferences mSharedPreferences;

	/**
	 * Recycler view containing the alarm cards.
	 */
	//private RecyclerView mRecyclerView;

	/**
	 * Floating button to add new alarms.
	 */
	private FloatingActionButton mFloatingButton;

	/**
	 * Alarm card adapter.
	 */
	//private NacCardAdapter mAdapter;

	/**
	 * @return The drawer layout.
	 */
	private DrawerLayout getDrawerLayout()
	{
		return this.mDrawerLayout;
	}

	/**
	 * @return The floating action button.
	 */
	private FloatingActionButton getFloatingButton()
	{
		return this.mFloatingButton;
	}

	/**
	 * @return The recycler view.
	 */
	//private RecyclerView getRecyclerView()
	//{
	//	return this.mRecyclerView;
	//}

	/**
	 * @return The shared preferences.
	 */
	private SsSharedPreferences getSharedPreferences()
	{
		return this.mSharedPreferences;
	}

	/**
	 * Add a new alarm when the floating action button is clicked.
	 */
	//@Override
	//public void onClick(View view)
	//{
	//	NacCardAdapter adapter = this.getCardAdapter();

	//	view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
	//	adapter.add();
	//	adapter.setWasAddedWithFloatingButton(true);
	//}

	///**
	// */
	//@Override
	//public void onBackPressed()
	//{
	//	if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START))
	//	{
	//		this.mDrawerLayout.closeDrawer(GravityCompat.START);
	//	}
	//	else
	//	{
	//		super.onBackPressed();
	//	}
	//}

	/**
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		this.mSharedPreferences = new SsSharedPreferences(this);

		this.setupOnboardingActivity();
		setContentView(R.layout.act_main);
		this.setupAppBar();
	}

	/**
	 */
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
	{
		DrawerLayout drawer = this.getDrawerLayout();
		int id = menuItem.getItemId();

		menuItem.setChecked(true);
		drawer.closeDrawers();

		//switch (id)
		//{
		//	case R.id.first:
		//		this.mNavController.navigate(R.id.firstFragment);
		//		break;
		//	case R.id.second:
		//		this.mNavController.navigate(R.id.secondFragment);
		//		break;
		//	case R.id.third:
		//		this.mNavController.navigate(R.id.thirdFragment);
		//		break;
		//}

		return true;
	}

	/**
	 */
	@Override
	public boolean onSupportNavigateUp()
	{
		NavController controller = Navigation.findNavController(this,
			R.id.nav_host_fragment);
		DrawerLayout drawer = this.getDrawerLayout();
		return NavigationUI.navigateUp(controller, drawer);
	}

	/**
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_toolbar, menu);
		return true;
	}

	/**
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		NavController navController = Navigation.findNavController(this,
			R.id.nav_host_fragment);
		return NavigationUI.onNavDestinationSelected(item, navController)
			|| super.onOptionsItemSelected(item);
	}

	//@Override
	//public boolean onOptionsItemSelected(MenuItem item)
	//{
	//	int id = item.getItemId();

	//	switch (id)
	//	{
	//		case R.id.menu_settings:
	//			startActivity(new Intent(this, NacSettingsActivity.class));
	//			return true;
	//		case R.id.menu_show_next_alarm:
	//			NacCardAdapter adapter = this.getCardAdapter();
	//			adapter.showNextAlarm();
	//			return true;
	//		default:
	//			break;
	//	}

	//	return super.onOptionsItemSelected(item);
	//}

	/**
	 * Activity is resumed.
	 */
	@Override
	protected void onResume()
	{
		super.onResume();
		SsSharedPreferences shared = this.getSharedPreferences();
		//shared.debug();

		this.setupBottomNavigationBar();
		this.setupGoogleRatingDialog();
	}

	/**
	 * Setup the app bar for navigation.
	 */
	private void setupAppBar()
	{
		CollapsingToolbarLayout collapsingLayout = findViewById(
			R.id.collapsing_toolbar_layout);
		Toolbar toolbar = findViewById(R.id.toolbar);
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		NavigationView navView = findViewById(R.id.nav_view);
		NavController navController = Navigation.findNavController(this,
			R.id.nav_host_fragment);

		AppBarConfiguration appBarConfiguration =
			new AppBarConfiguration.Builder(navController.getGraph())
				.setDrawerLayout(drawer)
				.build();

		setSupportActionBar(toolbar);
		NavigationUI.setupWithNavController(collapsingLayout, toolbar, navController,
			appBarConfiguration);
		navView.setNavigationItemSelectedListener(this);

		this.mCollapsingLayout = collapsingLayout;
		this.mToolbar = toolbar;
		this.mDrawerLayout = drawer;
		this.mNavigationView = navView;
		this.mNavController = navController;
	}

	/**
	 * Setup the bottom navigation bar.
	 */
	private void setupBottomNavigationBar()
	{
		SsSharedPreferences shared = this.getSharedPreferences();
		BottomNavigationView bottomBar = findViewById(R.id.bottom_navigation_bar);
		Menu menu = bottomBar.getMenu();

		boolean willBoulder = shared.getWillClimbBoulder();
		boolean willLead = shared.getWillClimbLead();
		boolean willTopRope = shared.getWillClimbTopRope();
		boolean willTrad = shared.getWillClimbTrad();

		menu.findItem(R.id.go_to_boulder).setVisible(willBoulder);
		menu.findItem(R.id.go_to_lead).setVisible(willLead);
		menu.findItem(R.id.go_to_top_rope).setVisible(willTopRope);
		menu.findItem(R.id.go_to_trad).setVisible(willTrad);
	}

	/**
	 * Setup the Google rating dialog.
	 */
	private void setupGoogleRatingDialog()
	{
		SsSharedPreferences shared = this.getSharedPreferences();
		if (shared.isRateMyAppRated())
		{
			return;
		}

		if (shared.isRateMyAppLimit())
		{
			//NacRateMyAppDialog dialog = new NacRateMyAppDialog(this);
			//dialog.build();
			//dialog.show();
		}
		else
		{
			//shared.incrementRateMyApp();
		}
	}

	/**
	 * Setup the onboarding activity.
	 */
	private void setupOnboardingActivity()
	{
		SsSharedPreferences shared = this.getSharedPreferences();

		if (shared.getAppFirstRun())
		{
			startActivity(new Intent(this, SsOnboardingActivity.class));
		}
	}

}
