package companyname.com.kpl.recycler_listviews_adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import companyname.com.kpl.R;

public class News_Details extends AppCompatActivity {
    Context ctx;
    ImageView imageView_news,iv_staticpath;
    TextView tv_id,tv_date,staticpath;
    Button edit,delete,update,uploadnews;
    EditText tv_name,tv_title,tv_desc,tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        staticpath=(TextView)findViewById(R.id.tv_static_path_for_trial);
        iv_staticpath=(ImageView)findViewById(R.id.iv_static_path_for_trial);
        uploadnews= (Button) findViewById(R.id.btn_upload_news_image);
        uploadnews.setEnabled(false);
        tv_date=(TextView)findViewById(R.id.tv_date_news);
        imageView_news= (ImageView) findViewById(R.id.first_image);
        tv_id= (TextView) findViewById(R.id.second_idnews);
        tv_name= (EditText) findViewById(R.id.third_name);
        tv_title= (EditText) findViewById(R.id.four_title);
        tv_desc= (EditText) findViewById(R.id.five_desc);
        tv_content= (EditText) findViewById(R.id.six_content);

//        imageView_news.setImageResource(getIntent().getIntExtra("image",00));
        tv_date.setText(""+getIntent().getStringExtra("date"));
        tv_id.setText(""+getIntent().getStringExtra("news_id"));
        tv_name.setText(""+getIntent().getStringExtra("name"));
        tv_title.setText(""+getIntent().getStringExtra("title"));
        tv_desc.setText(""+getIntent().getStringExtra("description"));
        tv_content.setText(""+getIntent().getStringExtra("content"));
        staticpath.setText(""+getIntent().getStringExtra("imagepath"));
        String path=staticpath.toString().trim();


        Glide.with(getApplicationContext()).load(path)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.mipmap.ic_launcher)
                .into(imageView_news);


     /*   Picasso.with(getApplicationContext())
                .load(path)
                .error(R.drawable.alert_info)
                .into(imageView_news);
       */



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
                        tv_name.setText("");
                        tv_title.setEnabled(true);
                        tv_content.setEnabled(true);
                        tv_desc.setEnabled(true);
                        tv_date.setEnabled(true);
                        uploadnews.setEnabled(true);
                        edit.setEnabled(false);
                        update.setEnabled(true);
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
