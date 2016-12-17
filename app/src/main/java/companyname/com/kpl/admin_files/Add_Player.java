package companyname.com.kpl.admin_files;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import companyname.com.kpl.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class Add_Player extends AppCompatActivity {
    static final int DIALOG_ID=0;
    int year_x,month_x,day_x;
    CircleImageView iv;
    Button add_date;
    TextView dispdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_add_player);
        dispdate= (TextView) findViewById(R.id.tv_show_date);
        dispdate.setVisibility(View.INVISIBLE);
        iv= (CircleImageView) findViewById(R.id.civ_add_player);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Clickable",Toast.LENGTH_LONG).show();
            }
        });
        final Calendar cal=Calendar.getInstance();
        year_x=cal.get(Calendar.YEAR);
        month_x=cal.get(Calendar.MONTH);
        day_x=cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();

    }

    private void showDialogOnButtonClick() {
        add_date= (Button) findViewById(R.id.btn_select_date);
        add_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id)
    {
        if(id==DIALOG_ID)
            return new DatePickerDialog(this,dpickerListener,year_x,month_x,day_x);
        return null;
    }
    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            year_x=year;
            month_x=month+1;
            day_x=dayOfMonth;

            //Toast.makeText(Add_news.this,year_x+"/"+month_x+"/"+day_x,Toast.LENGTH_LONG).show();
            dispdate.setText(year_x+"/"+month_x+"/"+day_x);

            if(dispdate==null)
            {
                dispdate.setVisibility(View.INVISIBLE);
            }
            else
            {
                dispdate.setVisibility(View.VISIBLE);
            }
        }

    };

}
