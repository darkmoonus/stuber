package uet.vav.stuber.activities;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;

import uet.vav.stuber.R;
import uet.vav.stuber.cores.CoreActivity;
import uet.vav.stuber.fragments.BroadcastFragment;
import uet.vav.stuber.fragments.DirectFragment;
import uet.vav.stuber.fragments.NotificationsFragment;
import uet.vav.stuber.fragments.SettingsFragment;

public class MainActivity extends CoreActivity {
    private Fragment currentFragment;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private android.app.FragmentManager fragmentManager = getFragmentManager();
    private AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
//        initPager();
        initModels();
        initListeners();
        initAnimations();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initViews() {
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_broadcast, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_direct, R.color.color_tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_notification, R.color.color_tab_3);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4, R.drawable.ic_settings, R.color.color_tab_4);

        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);

        bottomNavigation.addItems(bottomNavigationItems);
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

        bottomNavigation.setColored(true);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                if (!wasSelected) {
                    switch (position) {
                        case 0:
                            currentFragment = BroadcastFragment.getInstance(MainActivity.this);
                            break;
                        case 1:
                            currentFragment = DirectFragment.getInstance(MainActivity.this);
                            break;
                        case 2:
                            currentFragment = NotificationsFragment.getInstance(MainActivity.this);
                            break;
                        case 3:
                            currentFragment = SettingsFragment.getInstance(MainActivity.this);
                            break;
                    }

                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
                            .replace(R.id.fragment_container, currentFragment)
                            .commit();
                }
            }
        });

        currentFragment = new BroadcastFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, currentFragment)
                .commit();

    }

    @Override
    public void initModels() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initAnimations() {

    }

    /**
     * Initialize pager
     */
//    public static final int NUM_PAGES = 4;
//    public static ViewPager mPager;
//    public PagerAdapter mPagerAdapter;
//    public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
//        public ScreenSlidePagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//        @Override
//        public CoreFragment getItem(int position) {
//            switch (position) {
//                case 0:
//                    return BroadcastFragment.getInstance(MainActivity.this);
//                case 1:
//                    return DirectFragment.getInstance(MainActivity.this);
//                case 2:
//                    return NotificationsFragment.getInstance(MainActivity.this);
//                case 3:
//                    return SettingsFragment.getInstance(MainActivity.this);
//                default:
//                    break;
//            }
//            return null;
//        }
//        @Override
//        public int getCount() {
//            return NUM_PAGES;
//        }
//    }
//    public void initPager() {
//        mPager = (ViewPager) findViewById(R.id.viewpager);
//        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
//        mPager.setOffscreenPageLimit(NUM_PAGES);
//        mPager.setAdapter(mPagerAdapter);
//        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageSelected(int arg0) {
//                if (mPager.getCurrentItem() == 1) {
//
//                } else
//                if (mPager.getCurrentItem() == 0) {
//
//                }
//            }
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//            }
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//            }
//        });
//        mPager.setCurrentItem(0);
//    }
}
