package companyname.com.kpl.recycler_listviews_adapters;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;

import companyname.com.kpl.R;

/**
 * Created by admin on 12/13/2016.
 */

public class DisplayList_news extends AppCompatActivity{
    RecyclerView.Adapter adapter;
    public SearchView searchView;


    Toolbar toolbar;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
     requestWindowFeature(Window.FEATURE_NO_TITLE);
     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
             WindowManager.LayoutParams.FLAG_FULLSCREEN
     );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_news);
     searchView=(SearchView)findViewById(R.id.sv);
     searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
         @Override
         public boolean onQueryTextSubmit(String query) {
             return false;
         }

         @Override
         public boolean onQueryTextChange(String newText) {
             //  adapter.getFilter().filter(newText);
             Toast.makeText(getApplicationContext(),"CLICKED",Toast.LENGTH_LONG).show();

            //adapter.getFilter(newText);
             return false;
         }
     });
     toolbar=(Toolbar)findViewById(R.id.toolbar);
     setSupportActionBar(toolbar);
        BackgroundTask_news backgroundTaskNews =new BackgroundTask_news(DisplayList_news.this);
        backgroundTaskNews.execute();
    }




}
