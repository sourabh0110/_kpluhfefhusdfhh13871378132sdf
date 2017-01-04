package companyname.com.kpl.admin_files;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import companyname.com.kpl.R;
import companyname.com.kpl.recycler_listviews_adapters.BackgroundTask_Player;
import companyname.com.kpl.recycler_listviews_adapters.Player;
import companyname.com.kpl.recycler_listviews_adapters.RecyclerAdapter_player;
import companyname.com.kpl.recycler_listviews_adapters.RecyclerAdapter_referee;

public class Select_Player_list extends AppCompatActivity {
    SearchView sv;
    RecyclerAdapter_player adapter_player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player_list);
        sv= (SearchView) findViewById(R.id.search_player);
        final BackgroundTask_Player backgroundTask_player =new BackgroundTask_Player(Select_Player_list.this);
        backgroundTask_player.execute();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter_player.getFilter().filter(newText);
                return false;
            }
        });

    }

}
