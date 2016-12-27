package companyname.com.kpl.admin_files;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import companyname.com.kpl.R;
import companyname.com.kpl.recycler_listviews_adapters.BackgroundTask_Feat_Player;
import companyname.com.kpl.recycler_listviews_adapters.BackgroundTask_Player;

public class Select_Feat_Player_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__feat_player_list);

        //DECLARING FEATUREDPLAYER BACKGROUND CLASS
        final BackgroundTask_Feat_Player backgroundTask_feat_player =new BackgroundTask_Feat_Player(Select_Feat_Player_list.this);
        backgroundTask_feat_player.execute();

    }
}
