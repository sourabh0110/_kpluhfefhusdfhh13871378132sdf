package companyname.com.kpl.recycler_listviews_adapters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import companyname.com.kpl.R;

public class Team_details extends AppCompatActivity {
    ImageView imageView_team;
    TextView tv_code;
    EditText et_team_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        imageView_team= (ImageView) findViewById(R.id.first_image_team);
        tv_code= (TextView) findViewById(R.id.second_idteam);
        et_team_name= (EditText) findViewById(R.id.third_name_team);
        imageView_team.setImageResource(getIntent().getIntExtra("image",00));
        tv_code.setText("Code: "+getIntent().getStringExtra("id"));
        et_team_name.setText("Team Name: "+getIntent().getStringExtra("name"));



    }
}
