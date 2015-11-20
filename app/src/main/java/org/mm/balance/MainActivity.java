package org.mm.balance;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import org.mm.balance.gui.event.EventFragment;
import org.mm.balance.gui.SettingFragment;

public class MainActivity extends BaseActivity {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private MenuItem mMenuItemSelected;

    private EventFragment eventFragment;
    private SettingFragment settingFragment;

    public static Intent getIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        eventFragment = EventFragment.newInstance();
        settingFragment = SettingFragment.newInstance();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name) {

            public void onDrawerClosed(View view) {

                if (mMenuItemSelected != null) {
                    switch (mMenuItemSelected.getItemId()) {
                        case R.id.menu_drawer_event:
                            onBackPressed();
                            break;

                        case R.id.menu_drawer_setting:
                            replaceFragment(settingFragment, true, true);
                            break;
                        default:
                            break;
                    }
                }

                supportInvalidateOptionsMenu();

                // Menu item consume
                mMenuItemSelected = null;
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Select first item
        mNavigationView.setCheckedItem(R.id.menu_drawer_event);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mNavigationView.setCheckedItem(item.getItemId());

                mMenuItemSelected = item;
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        replaceFragment(eventFragment, false, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        mNavigationView.setCheckedItem(R.id.menu_drawer_event);

        super.onBackPressed();
    }
}
