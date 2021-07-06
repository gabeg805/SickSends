package me.gabeg.sicksends.main;

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
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
//import androidx.customview.widget.Openable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import me.gabeg.sicksends.R;
import me.gabeg.sicksends.system.SsBundle;
import me.gabeg.sicksends.onboarding.SsOnboardingActivity;
import me.gabeg.sicksends.shared.SsSharedConstants;
import me.gabeg.sicksends.shared.SsSharedPreferences;

/**
 * The application's main activity.
 */
public class SsMainActivity
	extends AppCompatActivity
	implements View.OnClickListener,
		NavigationView.OnNavigationItemSelectedListener,
		BottomNavigationView.OnNavigationItemSelectedListener,
		NavController.OnDestinationChangedListener
{

	private static final String TAG = "SsMainActivity";

	/**
	 * Shared preferences.
	 */
	private SsSharedPreferences mSharedPreferences;

	/**
	 * Main drawer layout.
	 *
	 * Mainly used to control whether the drawer is open or closed.
	 */
	public DrawerLayout mDrawerLayout;

	/**
	 * View that is actually contained within the drawer.
	 *
	 * Mainly used for configuring the menu items.
	 */
	public NavigationView mNavigationView;

	/**
	 * Navigation controller between Home and each of the climbing types.
	 */
	public NavController mNavController;

	/**
	 * Navigation bar at the bottom of the screen.
	 */
	public BottomNavigationView mBottomBar;

	/**
	 * Floating button to add new alarms.
	 */
	private FloatingActionButton mFloatingActionButton;

	/**
	 * Top level destinations in the app bar.
	 *
	 * This dictates whether the hamburger icon is visible, or a back button.
	 */
	private static final Integer[] TOP_LEVEL_DST_IDS = {R.id.frg_home,
		R.id.frg_boulder, R.id.frg_sport, R.id.frg_top_rope, R.id.frg_trad};

	/**
	 * Convert a menu item ID from the bottom bar to the corresponding ID in the
	 * drawer's navigation view.
	 *
	 * @param  id  Menu item ID.
	 */
	private int bottomBarToDrawerId(int id)
	{
		switch (id)
		{
			case R.id.home_from_bar:
				return R.id.home_from_drawer;
			case R.id.boulder_from_bar:
				return R.id.boulder_from_drawer;
			case R.id.sport_from_bar:
				return R.id.sport_from_drawer;
			case R.id.top_rope_from_bar:
				return R.id.top_rope_from_drawer;
			case R.id.trad_from_bar:
				return R.id.trad_from_drawer;
			default:
				return View.NO_ID;
		}
	}

	/**
	 * Close the drawer.
	 */
	private void closeDrawer()
	{
		DrawerLayout drawer = this.getDrawerLayout();
		drawer.closeDrawer(GravityCompat.START);
	}

	/**
	 * Convert a menu item ID from the drawer's navigation view to the
	 * corresponding ID in the bottom bar.
	 *
	 * @param  id  Menu item ID.
	 */
	private int drawerToBottomBarId(int id)
	{
		switch (id)
		{
			case R.id.home_from_drawer:
				return R.id.home_from_bar;
			case R.id.boulder_from_drawer:
				return R.id.boulder_from_bar;
			case R.id.sport_from_drawer:
				return R.id.sport_from_bar;
			case R.id.top_rope_from_drawer:
				return R.id.top_rope_from_bar;
			case R.id.trad_from_drawer:
				return R.id.trad_from_bar;
			default:
				return View.NO_ID;
		}
	}

	/**
	 * @return The navigation bar at the bottom of the screen.
	 */
	private BottomNavigationView getBottomBar()
	{
		return this.mBottomBar;
	}

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
	private FloatingActionButton getFloatingActionButton()
	{
		return this.mFloatingActionButton;
	}

	/**
	 * @return The navigation controller.
	 */
	private NavController getNavController()
	{
		return this.mNavController;
	}

	/**
	 * @return The navigation view.
	 */
	private NavigationView getNavigationView()
	{
		return this.mNavigationView;
	}

	/**
	 * @return The shared preferences.
	 */
	private SsSharedPreferences getSharedPreferences()
	{
		return this.mSharedPreferences;
	}

	/**
	 * @return The toolbar subtitle based on the destination.
	 *
	 * @param  dst  The destination.
	 */
	private CharSequence getToolbarSubtitleByDestination(NavDestination dst)
	{
		SsSharedPreferences shared = this.getSharedPreferences();
		SsSharedConstants cons = shared.getConstants();
		int id = dst.getId();

		if ((id == R.id.frg_home) || (id == R.id.frg_add_climb))
		{
			return "";
		}
		else
		{
			return cons.getIndoor();
		}
	}

	/**
	 * @return The toolbar title based on the destination.
	 *
	 * @param  dst  The destination.
	 */
	private CharSequence getToolbarTitleByDestination(NavDestination dst)
	{
		SsSharedPreferences shared = this.getSharedPreferences();
		SsSharedConstants cons = shared.getConstants();
		int id = dst.getId();

		return (id == R.id.frg_home) ? cons.getAppName() : dst.getLabel();
	}

	/**
	 * Check if the ID belongs to the bottom navigation bar.
	 *
	 * @param  id  A menu item ID.
	 */
	private boolean isBottomBarId(int id)
	{
		BottomNavigationView bottomBar = this.getBottomBar();
		Menu menu = bottomBar.getMenu();
		MenuItem item = menu.findItem(id);

		return (item != null);
	}

	/**
	 * Check if the ID belongs to the drawer's navigation view.
	 *
	 * @param  id  A menu item ID.
	 */
	private boolean isDrawerId(int id)
	{
		NavigationView nav = this.getNavigationView();
		Menu menu = nav.getMenu();
		MenuItem item = menu.findItem(id);

		return (item != null);
	}

	/**
	 * Check if the drawer is open.
	 */
	private boolean isDrawerOpen()
	{
		DrawerLayout drawer = this.getDrawerLayout();
		return drawer.isDrawerOpen(GravityCompat.START);
	}

	/**
	 * Navigate to a fragment.
	 *
	 * @param  id  The ID of the fragment to navigate to.
	 */
	private void navigateTo(int id)
	{
		NavController controller = this.getNavController();

		if ((id == R.id.home_from_bar) || (id == R.id.home_from_drawer))
		{
			controller.navigate(R.id.frg_home);
		}
		else if ((id == R.id.boulder_from_bar) || (id == R.id.boulder_from_drawer))
		{
			controller.navigate(R.id.frg_boulder);
		}
		else if ((id == R.id.sport_from_bar) || (id == R.id.sport_from_drawer))
		{
			controller.navigate(R.id.frg_sport);
		}
		else if ((id == R.id.top_rope_from_bar) || (id == R.id.top_rope_from_drawer))
		{
			controller.navigate(R.id.frg_top_rope);
		}
		else if ((id == R.id.trad_from_bar) || (id == R.id.trad_from_drawer))
		{
			controller.navigate(R.id.frg_trad);
		}
	}

	/**
	 * Close the drawer if it is open when the back button is pressed, otherwise
	 * do the default behavior.
	 * 
	 * TODO Should I not do the default back pressed action? Fragments are added
	 * to the backstack and this will just cycle through the backstack.
	 */
	@Override
	public void onBackPressed()
	{
		DrawerLayout drawer = this.getDrawerLayout();
		int gravity = GravityCompat.START;

		if (drawer.isDrawerOpen(gravity))
		{
			drawer.closeDrawer(gravity);
		}
		else
		{
			Log.i(TAG, "onBackPressed!");
			super.onBackPressed();
		}
	}

	/**
	 * Called when the floating action button has been clicked.
	 */
	@Override
	public void onClick(View view)
	{
		NavController controller = this.getNavController();
		int srcId = controller.getCurrentDestination().getId();
		Bundle args = SsBundle.toAddClimbBundle(srcId);

		Log.i(TAG, "onFabClicked! " + srcId);

		controller.navigate(R.id.frg_add_climb, args);
	}

	/**
	 * Create the main activity.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		this.mSharedPreferences = new SsSharedPreferences(this);

		this.setupOnboardingActivity();
		setContentView(R.layout.act_main);

		this.mDrawerLayout = findViewById(R.id.drawer_layout);
		this.mNavigationView = findViewById(R.id.nav_view);
		this.mFloatingActionButton = findViewById(R.id.fab_add_climb);
		this.mBottomBar = findViewById(R.id.bottom_navigation_bar);
		this.mNavController = Navigation.findNavController(this, R.id.nav_host);

		this.setupAppBar();
	}

	/**
	 * Create the toolbar menu items.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_toolbar, menu);
		return true;
	}

	/**
	 * Navigation destination has changed.
	 */
	@Override
	public void onDestinationChanged(NavController controller, NavDestination dst,
		Bundle args)
	{
		Log.i(TAG, "onDestinationChanged! " + dst.getLabel());
		this.setToolbarTitle(dst);
		this.setToolbarSubtitle(dst);
		this.setFloatingActionButtonVisibility(dst);
		this.setBottomBarVisibility(dst);
	}

	/**
	 * Called when an item in the navigation drawer is selected.
	 */
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
	{
		int id = menuItem.getItemId();

		this.navigateTo(id);
		this.selectNavigationItem(id);
		this.closeDrawer();
		return true;
	}

	/**
	 */
	@Override
	public boolean onSupportNavigateUp()
	{
		Log.i(TAG, "onSupportNavigateUp!");
		NavController controller = this.getNavController();
		DrawerLayout drawer = this.getDrawerLayout();

		return NavigationUI.navigateUp(controller, drawer);
	}

	/**
	 * Either the NacController was able to navigate up, or an option in the
	 * toolbar menu was selected.
	 *
	 * TODO Not sure if I should be even using NavigationUI. Everything is a
	 * toplevel destination so there's really no UP button.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Log.i(TAG, "onOptionsItemSelected!");
		NavController controller = this.getNavController();

		return NavigationUI.onNavDestinationSelected(item, controller)
			|| super.onOptionsItemSelected(item);
	}

	/**
	 * Activity is resumed.
	 */
	@Override
	protected void onResume()
	{
		super.onResume();
		SsSharedPreferences shared = this.getSharedPreferences();
		//shared.debug();

		this.setupBottomBar();
		this.setupNavigationView();
		this.setupFloatingActionButton();
		this.setupGoogleRatingDialog();
	}

	/**
	 * Select the navigation item in the bottom bar as well as the drawer.
	 */
	private void selectNavigationItem(int id)
	{
		if (this.isBottomBarId(id))
		{
			int newId = this.bottomBarToDrawerId(id);
			this.getNavigationView().setCheckedItem(newId);
		}
		else if (this.isDrawerId(id))
		{
			int newId = this.drawerToBottomBarId(id);
			this.getBottomBar().setSelectedItemId(newId);
		}
	}

	/**
	 * Set bottom navigation bar visibility based on the current destination.
	 *
	 * @param  dst  The current destination.
	 */
	private void setBottomBarVisibility(NavDestination dst)
	{
		BottomNavigationView bottomBar = this.getBottomBar();
		int id = dst.getId();

		if (id == R.id.frg_add_climb)
		{
			bottomBar.setVisibility(View.GONE);
		}
		else
		{
			bottomBar.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * Set floating action button visibility based on the current destination.
	 *
	 * @param  dst  The current destination.
	 */
	private void setFloatingActionButtonVisibility(NavDestination dst)
	{
		FloatingActionButton fab = this.getFloatingActionButton();
		int id = dst.getId();

		if ((id == R.id.frg_home) || (id == R.id.frg_add_climb))
		{
			fab.hide();
		}
		else
		{
			fab.show();
		}
	}

	/**
	 * Set the toolbar subtitle based on the current destination.
	 *
	 * @param  dst  The current destination.
	 */
	private void setToolbarSubtitle(NavDestination dst)
	{
		ActionBar actionBar = getSupportActionBar();
		if (actionBar == null)
		{
			return;
		}

		CharSequence subtitle = this.getToolbarSubtitleByDestination(dst);
		actionBar.setSubtitle(subtitle);
	}

	/**
	 * Set the toolbar title based on the current destination.
	 *
	 * @param  dst  The current destination.
	 */
	private void setToolbarTitle(NavDestination dst)
	{
		ActionBar actionBar = getSupportActionBar();
		if (actionBar == null)
		{
			return;
		}

		CharSequence title = this.getToolbarTitleByDestination(dst);
		actionBar.setTitle(title);
	}

	/**
	 * Setup the app bar for navigation.
	 */
	private void setupAppBar()
	{
		CollapsingToolbarLayout collapsingLayout = findViewById(
			R.id.collapsing_toolbar_layout);
		MaterialToolbar toolbar = findViewById(R.id.toolbar);
		DrawerLayout drawer = this.getDrawerLayout();
		NavController controller = this.getNavController();
		Set<Integer> topLevel = new HashSet<>(Arrays.asList(TOP_LEVEL_DST_IDS));

		AppBarConfiguration appBarConfig = new AppBarConfiguration.Builder(topLevel)
				.setDrawerLayout(drawer)
				.build();
				//.setOpenableLayout(drawer)

		setSupportActionBar(toolbar);
		NavigationUI.setupWithNavController(collapsingLayout, toolbar, controller,
			appBarConfig);
		//NavigationUI.setupWithNavController(toolbar, controller, appBarConfig);
		collapsingLayout.setTitleEnabled(false);
		controller.addOnDestinationChangedListener(this);
	}

	/**
	 * Setup the floating action button.
	 */
	private void setupFloatingActionButton()
	{
		FloatingActionButton fab = this.getFloatingActionButton();
		fab.setOnClickListener(this);
	}

	/**
	 * Setup the toolbar.
	 */
	private void setupToolbar()
	{
		ActionBar actionBar = getSupportActionBar();
		if (actionBar == null)
		{
			return;
		}

		SsSharedPreferences shared = this.getSharedPreferences();
		BottomNavigationView bottomBar = this.getBottomBar();
		Menu menu = bottomBar.getMenu();

		boolean willBoulder = shared.getWillClimbBoulder();
		boolean willSport = shared.getWillClimbSport();
		boolean willTopRope = shared.getWillClimbTopRope();
		boolean willTrad = shared.getWillClimbTrad();

		menu.findItem(R.id.boulder_from_bar).setVisible(willBoulder);
		menu.findItem(R.id.sport_from_bar).setVisible(willSport);
		menu.findItem(R.id.top_rope_from_bar).setVisible(willTopRope);
		menu.findItem(R.id.trad_from_bar).setVisible(willTrad);
		bottomBar.setOnNavigationItemSelectedListener(this);
	}

	/**
	 * Setup the bottom navigation bar.
	 */
	private void setupBottomBar()
	{
		SsSharedPreferences shared = this.getSharedPreferences();
		BottomNavigationView bottomBar = this.getBottomBar();
		Menu menu = bottomBar.getMenu();

		boolean willBoulder = shared.getWillClimbBoulder();
		boolean willSport = shared.getWillClimbSport();
		boolean willTopRope = shared.getWillClimbTopRope();
		boolean willTrad = shared.getWillClimbTrad();

		menu.findItem(R.id.boulder_from_bar).setVisible(willBoulder);
		menu.findItem(R.id.sport_from_bar).setVisible(willSport);
		menu.findItem(R.id.top_rope_from_bar).setVisible(willTopRope);
		menu.findItem(R.id.trad_from_bar).setVisible(willTrad);
		bottomBar.setOnNavigationItemSelectedListener(this);
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
	 * Setup the drawer's navigation view.
	 */
	private void setupNavigationView()
	{
		SsSharedPreferences shared = this.getSharedPreferences();
		NavigationView nav = this.getNavigationView();
		Menu menu = nav.getMenu();

		BottomNavigationView bottomBar = this.getBottomBar();
		int barId = bottomBar.getSelectedItemId();
		int navId = this.bottomBarToDrawerId(barId);

		boolean willBoulder = shared.getWillClimbBoulder();
		boolean willSport = shared.getWillClimbSport();
		boolean willTopRope = shared.getWillClimbTopRope();
		boolean willTrad = shared.getWillClimbTrad();

		menu.findItem(R.id.boulder_from_drawer).setVisible(willBoulder);
		menu.findItem(R.id.sport_from_drawer).setVisible(willSport);
		menu.findItem(R.id.top_rope_from_drawer).setVisible(willTopRope);
		menu.findItem(R.id.trad_from_drawer).setVisible(willTrad);
		nav.setCheckedItem(navId);
		nav.setNavigationItemSelectedListener(this);
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
