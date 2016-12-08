package companyname.com.kpl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Admin_Activity extends AppCompatActivity implements Demo.OnFragmentInteractionListener{

    private AlertDialog ad;
    private TabLayout tabLayout;
    private long back_pressed;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.menu_admin_home,
            R.drawable.menu_edit,
            R.drawable.menu_add_user,
            R.drawable.menu_news
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_admin);

        TabLayout tl;
        //private Toolbar toolbar;

        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        viewPager = (ViewPager) findViewById(R.id.viewpager_admin);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs_admin);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    @Override
    public void onBackPressed() {
        /*
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Please Confirm")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();

                    }
                }).setNegativeButton("No", null).show();
                */



        logout();
    }

    /*
                 @Override
                 public void onBackPressed() {
                     new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                             .setMessage("Are you sure you want to exit?")
                             .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {
                                     finish();
                                 }
                             }).setNegativeButton("No", null).show();
                 }
    */
    private void logout()
    {
        AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(this).setIcon(R.drawable.alert_info).setTitle("Please Confirm").setMessage("Are you sure you want to Logout?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharedPreferences preferences=getSharedPreferences(LoginActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();

                        editor.putBoolean(LoginActivity.LOGGEDIN_SHARED_PREF,false);
                        editor.commit();

                        //Intent i=new Intent(Admin_Activity.this,MainActivity.class);
                        //startActivity(i);
                        Toast.makeText(Admin_Activity.this,"Logged Out successfully",Toast.LENGTH_LONG).show();
                        finish();



                    }
                }
        );
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment_admin(), "");
        adapter.addFragment(new TwoFragment_admin(), "");
        adapter.addFragment(new ThreeFragment_admin(), "");
        adapter.addFragment(new FourFragment_admin(), "");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {

            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mFragmentTitleList.get(position);
        }


    }



/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/


    /*
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_favourites) {

        }
        else if (id == R.id.nav_login) {

        }else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_aboutkpl) {

        } else if (id == R.id.nav_aboutdev) {

        } else if (id == R.id.nav_rateus) {

        }
        else if (id == R.id.nav_help) {

        }
        else if (id == R.id.nav_terms_conditions) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    */
}
