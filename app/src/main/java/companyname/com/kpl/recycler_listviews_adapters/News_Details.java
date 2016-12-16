package companyname.com.kpl.recycler_listviews_adapters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import companyname.com.kpl.R;

public class News_Details extends AppCompatActivity {
    ImageView imageView_news;
    TextView tv_id,tv_name,tv_title,tv_desc,tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__details);

       imageView_news= (ImageView) findViewById(R.id.first_image);
        tv_id= (TextView) findViewById(R.id.second_idnews);
        tv_name= (TextView) findViewById(R.id.third_name);
        tv_title= (TextView) findViewById(R.id.four_title);
        tv_desc= (TextView) findViewById(R.id.five_desc);
        tv_content= (TextView) findViewById(R.id.six_content);

        imageView_news.setImageResource(getIntent().getIntExtra("image",00));

        tv_id.setText("ID: "+getIntent().getStringExtra("id"));
        tv_name.setText("Author Name: "+getIntent().getStringExtra("name"));
        tv_title.setText("Title: "+getIntent().getStringExtra("title"));
        tv_desc.setText("Description: "+getIntent().getStringExtra("description"));
        tv_content.setText("Content: "+getIntent().getStringExtra("content"));


    }
}
