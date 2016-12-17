package companyname.com.kpl.recycler_listviews_adapters;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import companyname.com.kpl.R;

public class News_Details extends AppCompatActivity {
    ImageView imageView_news;
    TextView tv_id;
    Button edit,delete,update;
    EditText tv_name,tv_title,tv_desc,tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);


        imageView_news= (ImageView) findViewById(R.id.first_image);
        tv_id= (TextView) findViewById(R.id.second_idnews);
        tv_name= (EditText) findViewById(R.id.third_name);
        tv_title= (EditText) findViewById(R.id.four_title);
        tv_desc= (EditText) findViewById(R.id.five_desc);
        tv_content= (EditText) findViewById(R.id.six_content);

//        imageView_news.setImageResource(getIntent().getIntExtra("image",00));

        tv_id.setText("ID: "+getIntent().getStringExtra("id"));
        tv_name.setText("Author Name: "+getIntent().getStringExtra("name"));
        tv_title.setText("Title: "+getIntent().getStringExtra("title"));
        tv_desc.setText("Description: "+getIntent().getStringExtra("description"));
        tv_content.setText("Content: "+getIntent().getStringExtra("content"));

        update=(Button)findViewById(R.id.btn_update_news);
        update.setEnabled(false);


        edit= (Button) findViewById(R.id.btn_edit_news);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(News_Details.this);
                alertDialog.setTitle("Please Confirm");
                alertDialog.setMessage("Are You Sure?");
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_name.setEnabled(true);
                        tv_title.setEnabled(true);
                        tv_content.setEnabled(true);
                        tv_desc.setEnabled(true);
                        //edit.setText("Update");
                        edit.setEnabled(false);
                        update.setEnabled(true);
                        //edit.setVisibility(View.INVISIBLE);
                    }
                });

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });

        delete= (Button) findViewById(R.id.btn_del_news);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
