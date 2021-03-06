package companyname.com.kpl.admin_files;

import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import companyname.com.kpl.R;

public class Admin_Activity extends FragmentActivity {

    Bundle arguments;
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
        // String username=name.toString().trim();
        /*
        * TAKING THE VALUE OF USER FROM lOGINACTIVITY.JAVA(Username)
        * */
        String username = getIntent().getStringExtra("name");
        Log.e("name", "" + username);
        String a = "superadmin";
        Log.e("a==", "" + a);
        viewPager = (ViewPager) findViewById(R.id.viewpager_admin);
        if (a.equalsIgnoreCase(username)) {
            setupViewPagerSuperAdmin(viewPager);
        } else {
            setupViewPagerAdmin(viewPager);
        }

        tabLayout = (TabLayout) findViewById(R.id.tabs_admin);
        tabLayout.setupWithViewPager(viewPager);
        if (a.equalsIgnoreCase(username)) {

            setupTabIcons_SuperAdmin();
        } else {
            setupTabIcons_Admin();
        }

    }

    @Override
    public void onBackPressed() {
        logout();
    }

    private void logout() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this).setIcon(R.drawable.alert_info).setTitle("Please Confirm").setMessage("Are you sure you want to Logout?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharedPreferences preferences = getSharedPreferences(LoginActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();

                        editor.putBoolean(LoginActivity.LOGGEDIN_SHARED_PREF, false);
                        editor.commit();

                        //Intent i=new Intent(Admin_Activity.this,MainActivity.class);
                        //startActivity(i);
                        Toast.makeText(Admin_Activity.this, "Logged Out successfully", Toast.LENGTH_LONG).show();
                        finish();


                    }
                }
        );
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void setupTabIcons_SuperAdmin() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }

    private void setupTabIcons_Admin() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        //tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        //tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }

    private void setupViewPagerSuperAdmin(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment_admin(), "");
        adapter.addFragment(new TwoFragment_admin(), "");
        adapter.addFragment(new ThreeFragment_admin(), "");
        adapter.addFragment(new FourFragment_admin(), "");
        viewPager.setAdapter(adapter);

    }

    private void setupViewPagerAdmin(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment_admin(), "");
        adapter.addFragment(new FourFragment_admin(), "");
        viewPager.setAdapter(adapter);

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


}
