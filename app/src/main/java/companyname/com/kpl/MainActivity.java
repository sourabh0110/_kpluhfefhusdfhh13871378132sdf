package companyname.com.kpl;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import companyname.com.kpl.admin_files.LoginActivity;

public class MainActivity extends AppCompatActivity
         {
             String app_server_url = "http://devkpl.com/fcm_insert.php";
             private TabLayout tabLayout;
             private long back_pressed;
            private ViewPager viewPager;
             private int[] tabIcons = {
            R.drawable.menu_home,
            R.drawable.menu_mykpl,
            R.drawable.menu_stats,
            R.drawable.menu_more
    };
             Button b;




             @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_main);
        TabLayout tl;
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        sendfcmtoken();

    }

             private void sendfcmtoken() {

                 SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
                 final String token=sharedPreferences.getString(getString(R.string.FCM_TOKEN),"");
                 StringRequest stringRequest=new StringRequest(Request.Method.POST, app_server_url,
                         new Response.Listener<String>() {
                             @Override
                             public void onResponse(String response) {
                                 //          pb.dismiss();
//                                loading.dismiss();
                                 Toast.makeText(getApplicationContext(),response+"",Toast.LENGTH_LONG).show();
                             }
                         }, new Response.ErrorListener()
                 {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         //loading.dismiss();
                         System.out.println(error) ;
                         Toast.makeText(getApplicationContext(),error+"",Toast.LENGTH_LONG).show();
                     }
                 })
                 {
                     @Override
                     protected Map<String, String> getParams() throws AuthFailureError {

                         Map<String,String > params=new HashMap<String, String>();
                         params.put("fcm_token",token);

                         return params;
                     }
                 };
                 MySingleton.getmInstance(MainActivity.this).addToRequestque(stringRequest);
             }

             @Override
             public void onBackPressed() {
                 if (back_pressed + 1000 > System.currentTimeMillis()){
                     super.onBackPressed();
                 }
                 else{
                     Toast.makeText(getBaseContext(),
                             "Press once again to exit!", Toast.LENGTH_SHORT)
                             .show();
                 }
                 back_pressed = System.currentTimeMillis();
             }

             private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "Home");
        adapter.addFragment(new TwoFragment(), "My KPL");
        adapter.addFragment(new ThreeFragment(), "Stats");
        adapter.addFragment(new FourFragment(), "More");
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
