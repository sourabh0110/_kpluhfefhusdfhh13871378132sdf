package companyname.com.kpl.recycler_listviews_adapters;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import companyname.com.kpl.Image_Caching.ImageLoader;
import companyname.com.kpl.R;
import companyname.com.kpl.admin_files.Config;
import companyname.com.kpl.admin_files.FourFragment_admin;
import companyname.com.kpl.admin_files.Search_news;

import static android.R.attr.bitmap;

public class News_Details extends Activity {
    /*
    * DATA URL FOR DELETE
    * */
    //public static final String DATA_URL = "http://devkpl.com/news/getDataID.php?id=";
    public static final String DATA_URL="http://devkpl.com/alldeletequeries/getDataID_news_delete.php?id=";
    private ProgressDialog loading;
    private Bitmap bitmap;
    private static final int PICK_IMAGE=100;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    private int PICK_IMAGE_REQUEST = 1;
    final Calendar cal=Calendar.getInstance();
    static final int DIALOG_ID=0;
    int year_x,month_x,day_x;
    Context ctx;
    ImageView imageView_news,iv_staticpath;
    TextView tv_id,tv_date,staticpath;
    Button edit,delete,update,uploadnews,sdate;
    EditText tv_name,tv_title,tv_desc,tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        /*
        * CALENDAR CONTROLS
        * */
        final Calendar cal=Calendar.getInstance();
        year_x=cal.get(Calendar.YEAR);
        month_x=cal.get(Calendar.MONTH);
        day_x=cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();
        staticpath=(TextView)findViewById(R.id.tv_static_path_for_trial);
        iv_staticpath=(ImageView)findViewById(R.id.iv_static_path_for_trial);
        tv_date=(TextView)findViewById(R.id.tv_date_news);
        imageView_news= (ImageView) findViewById(R.id.first_image);
        tv_id= (TextView) findViewById(R.id.second_idnews);
        tv_name= (EditText) findViewById(R.id.third_name);
        tv_title= (EditText) findViewById(R.id.four_title);
        tv_desc= (EditText) findViewById(R.id.five_desc);
        tv_content= (EditText) findViewById(R.id.six_content);
        sdate.setEnabled(false);
        imageView_news.setEnabled(false);
/*
* INTENT EXTRAS
* */
        tv_date.setText(""+getIntent().getStringExtra("date"));
        tv_id.setText(""+getIntent().getStringExtra("news_id"));
        tv_name.setText(""+getIntent().getStringExtra("name"));
        tv_title.setText(""+getIntent().getStringExtra("title"));
        tv_desc.setText(""+getIntent().getStringExtra("description"));
        tv_content.setText(""+getIntent().getStringExtra("content"));
        staticpath.setText(""+getIntent().getStringExtra("imagepath"));
/*
* FOR GLIDE
* */
        String image_url = "";
        image_url=getIntent().getStringExtra("imagepath");


/*
* USING GLIDE TO CONVERT URL INTO IMAGE
* */
  Glide.with(getApplicationContext()).load(image_url)
 .diskCacheStrategy(DiskCacheStrategy.NONE)
 .error(R.mipmap.ic_launcher)
 .into(imageView_news);





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
                        tv_date.setEnabled(true);
                        uploadnews.setEnabled(true);
                        edit.setEnabled(false);
                        update.setEnabled(true);
                        imageView_news.setEnabled(true);
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

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(News_Details.this);
                alertDialog.setTitle("Confirm Delete");
                alertDialog.setMessage("The changes you make cannot be reverted!!");
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    //CALLING METHOD TO IMPLEMENT DELETE!
                    deletedata();

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

       imageView_news.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showFileChooser();
           }
       });

    }

    private void deletedata() {
        String id = tv_id.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(this,"Please wait...","Deleting this Record...",false,false);

        String url = DATA_URL+tv_id.getText().toString().trim();
        // String url = Config.DATA_URL;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                //showJSON(response);
                Toast.makeText(getApplicationContext(),"NEWS DELETED SUCCESSFULLY!",Toast.LENGTH_LONG).show();
                getCallingPackage();
                finish();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
         /*
        * SETTING THE TIMEOUT TO 50SECONDS
        * */

        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

    }

    public void showDialogOnButtonClick()
    {
        sdate= (Button) findViewById(R.id.select_date_update);
        sdate.setOnClickListener(new View.OnClickListener() {
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
            tv_date.setText(year_x+"/"+month_x+"/"+day_x);

            if(tv_date==null)
            {
                tv_date.setVisibility(View.INVISIBLE);
            }
            else
            {
                tv_date.setVisibility(View.VISIBLE);
            }
        }

    };

    /*
    * FOR IMAGE UPLOADING FROM GALLERY TO INTENT
    * */

    private void showFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Context ctx = null;
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                try
                {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                }
                catch (OutOfMemoryError e)
                {
                    Toast.makeText(getApplicationContext(),"FILE SIZE IS LARGE,CHOOSE ANOTHER FILE",Toast.LENGTH_LONG).show();
                    imageView_news.setImageResource(R.mipmap.ic_launcher);
                }


                //Setting the Bitmap to ImageView
                try{
                    imageView_news.setImageBitmap(bitmap);
                }
                catch (OutOfMemoryError e)
                {
                    Toast.makeText(getApplicationContext(),"FILE SIZE IS LARGE,CHOOSE ANOTHER FILE",Toast.LENGTH_LONG).show();
                }

                //iv.setImageBitmap(ImageDecoder.decodeSampledBitmapFromResource(this.getResources(),R.id.upload,100,100));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    }

