package companyname.com.kpl.admin_files;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import companyname.com.kpl.OnClickListener;
import companyname.com.kpl.R;
import companyname.com.kpl.recycler_listviews_adapters.BackgroundTask_Player;
import companyname.com.kpl.recycler_listviews_adapters.CalEventAdapter;

public class StepZero extends AppCompatActivity implements OnClickListener {
    TextView tvback,tvDate;
    RecyclerView rvEvent;
    String date,time,team1,team2,division;
    CalEventAdapter calEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        super.onCreate(savedInstanceState);
        //final BackgroundTask_Referee backgroundTask_referee =new BackgroundTask_Referee(StepZero.this);
        //backgroundTask_referee.execute();
        setContentView(R.layout.activity_step_zero);
        tvDate= (TextView)findViewById(R.id.cef_tvDate);
        tvDate.setText(getIntent().getStringExtra("date"));
        //GETTING INTENT VALUES FROM ADAPTER CLASS
        date=tvDate.getText().toString();
        time=getIntent().getStringExtra("time");
        team1=getIntent().getStringExtra("team1");
        team2=getIntent().getStringExtra("team2");
        division=getIntent().getStringExtra("division");
        Toast.makeText(getApplicationContext(),"Div"+division,Toast.LENGTH_LONG).show();
        rvEvent= (RecyclerView)findViewById(R.id.cef_rvEvent);

        rvEvent.setLayoutManager(new LinearLayoutManager(StepZero.this));
        calEventAdapter = new CalEventAdapter(StepZero.this,this);
        rvEvent.setAdapter(calEventAdapter);

    }
    /*
    *   @Override
    public void onClick(View v) {
        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fcal_flContainer, CalStepFirstFragment.newInstance()).addToBackStack(null).commit();


    }
    * */



    @Override
    public void onClick(View view, int position) {

        /*
        *Intent i=new Intent(StepZero.this,Stepone.class);
        i.putExtra("date",date );
        i.putExtra("time",time );
        i.putExtra("team1",team1 );
        i.putExtra("team2",team2 );
        i.putExtra("division",division );
        Log.e("Current",""+tvDate);
        startActivity(i);
        * */



    }
}
