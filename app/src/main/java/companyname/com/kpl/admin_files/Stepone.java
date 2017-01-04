package companyname.com.kpl.admin_files;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import companyname.com.kpl.R;
import companyname.com.kpl.recycler_listviews_adapters.BackgroundTask_Player;
import companyname.com.kpl.recycler_listviews_adapters.CalStepOneAdapter_Ref;
import companyname.com.kpl.recycler_listviews_adapters.HotListPojo;
import companyname.com.kpl.recycler_listviews_adapters.RecyclerAdapter_referee;
import companyname.com.kpl.recycler_listviews_adapters.Referee;

public class Stepone extends AppCompatActivity {
    RecyclerView rvEvent;
    private ArrayList<HotListPojo> hotListPojos;

    CalStepOneAdapter_Ref calStepOneAdapter;
    Button confirm;
    TextView csf_tvTime,csf_tvLname,csf_tvRname,csf_tvDiv,csf_tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_one);
        final BackgroundTask_Referee backgroundTask_referee =new BackgroundTask_Referee(Stepone.this);
        backgroundTask_referee.execute();
        csf_tvTime=(TextView)findViewById(R.id.csf_tvTime);
        csf_tvLname= (TextView) findViewById(R.id.csf_tvLname);
        csf_tvRname= (TextView) findViewById(R.id.csf_tvRname);
        csf_tvDiv= (TextView) findViewById(R.id.csf_tvDiv);
        csf_tvDate= (TextView) findViewById(R.id.csf_tvDate);

        //GETTING INTENT FROM ADAPTER
        csf_tvTime.setText(getIntent().getStringExtra("time"));
        csf_tvLname.setText(getIntent().getStringExtra("team1"));
        csf_tvRname.setText(getIntent().getStringExtra("team2"));
        csf_tvDiv.setText(getIntent().getStringExtra("division"));
        csf_tvDate.setText(getIntent().getStringExtra("date"));
        Toast.makeText(getApplicationContext(),"ID:"+getIntent().getStringExtra("ref_id"),Toast.LENGTH_LONG).show();

        confirm= (Button) findViewById(R.id.csf_btnConfirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Stepone.this, StepTwo.class);
                startActivity(i);
            }
        });

        /*
        * TO LOAD RC FROM MAIN PAGE
        *rvEvent= (RecyclerView)findViewById(R.id.csf_rvEvent);
        rvEvent.setLayoutManager(new LinearLayoutManager(this));
        prepareData();
        calStepOneAdapter = new CalStepOneAdapter_Ref(this,hotListPojos);
        rvEvent.setAdapter(calStepOneAdapter);
        * */

    }

    private void prepareData() {
        hotListPojos = new ArrayList<>();

        HotListPojo hotListPojo = new HotListPojo();
        hotListPojo.isSelected = false;
        hotListPojos.add(hotListPojo);

        hotListPojo = new HotListPojo();
        hotListPojo.isSelected = false;
        hotListPojos.add(hotListPojo);

        hotListPojo = new HotListPojo();
        hotListPojo.isSelected = false;
        hotListPojos.add(hotListPojo);

        hotListPojo = new HotListPojo();
        hotListPojo.isSelected = false;
        hotListPojos.add(hotListPojo);

        hotListPojo = new HotListPojo();
        hotListPojo.isSelected = false;
        hotListPojos.add(hotListPojo);

        hotListPojo = new HotListPojo();
        hotListPojo.isSelected = false;
        hotListPojos.add(hotListPojo);

        hotListPojo = new HotListPojo();
        hotListPojo.isSelected = false;
        hotListPojos.add(hotListPojo);
    }
}
