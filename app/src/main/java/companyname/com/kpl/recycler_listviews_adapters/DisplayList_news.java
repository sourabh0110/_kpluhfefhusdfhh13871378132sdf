package companyname.com.kpl.recycler_listviews_adapters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import companyname.com.kpl.R;

/**
 * Created by admin on 12/13/2016.
 */

public class DisplayList_news extends AppCompatActivity {
 @Override
    protected void onCreate(Bundle savedInstanceState) {
     requestWindowFeature(Window.FEATURE_NO_TITLE);
     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
             WindowManager.LayoutParams.FLAG_FULLSCREEN
     );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_news);
        BackgroundTask_news backgroundTaskNews =new BackgroundTask_news(DisplayList_news.this);
        backgroundTaskNews.execute();
    }



}
