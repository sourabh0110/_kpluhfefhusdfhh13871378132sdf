package companyname.com.kpl.admin_files;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import companyname.com.kpl.R;
import companyname.com.kpl.recycler_listviews_adapters.BackgroundTask_Championship;
import companyname.com.kpl.recycler_listviews_adapters.BackgroundTask_PremiereLeague;

public class Select_team extends AppCompatActivity {
RecyclerView premeireleague;
    Button btn_premiereLeague,btn_championship;
    Boolean pl_pressed=false;
            Boolean championship_pressed = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_team);
        final BackgroundTask_PremiereLeague backgroundTask_premiereLeague =new BackgroundTask_PremiereLeague(Select_team.this);
        final BackgroundTask_Championship backgroundTask_championship =new BackgroundTask_Championship(Select_team.this);

        //backgroundTask_premiereLeague.execute();
        btn_championship=(Button)findViewById(R.id.btn_championships);
        btn_premiereLeague=(Button)findViewById(R.id.btn_premiere_league);
        btn_premiereLeague.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pl_pressed==false)
                {
                    backgroundTask_premiereLeague.execute();
                    pl_pressed=true;
                }
                else
                {
                    btn_premiereLeague.setEnabled(false);
                }


            }
        });
        btn_championship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(championship_pressed==false)
                {
                    backgroundTask_championship.execute();
                    championship_pressed=true;
                }
                else
                {
                    btn_premiereLeague.setEnabled(false);
                }
            }
        });
    }

}
