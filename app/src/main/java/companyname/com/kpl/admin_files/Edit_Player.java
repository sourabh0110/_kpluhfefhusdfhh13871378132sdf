package companyname.com.kpl.admin_files;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Window;
import android.view.WindowManager;

import companyname.com.kpl.R;
import companyname.com.kpl.recycler_listviews_adapters.BackgroundTask_Championship;
import companyname.com.kpl.recycler_listviews_adapters.BackgroundTask_Player;
import companyname.com.kpl.recycler_listviews_adapters.BackgroundTask_PremiereLeague;

public class Edit_Player extends AppCompatActivity implements SearchView.OnQueryTextListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);

        final BackgroundTask_Player backgroundTask_player =new BackgroundTask_Player(Edit_Player.this);
        backgroundTask_player.execute();

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
