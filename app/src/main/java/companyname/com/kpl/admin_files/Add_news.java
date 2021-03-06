package companyname.com.kpl.admin_files;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import companyname.com.kpl.ImageDecoder;
import companyname.com.kpl.R;

public class Add_news extends AppCompatActivity {
    private Button sdate,btnupdate;
    private ImageView iv;
    private EditText author,news_title,news_desc,news_content;
    private TextView tv_date;
    private Button upload_image;
    private static final int PICK_IMAGE=100;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    Uri imageUri;
    int year_x,month_x,day_x;
    private Bitmap bitmap;
    private String UPLOAD_URL ="http://devkpl.com/news/upload.php";
    private int PICK_IMAGE_REQUEST = 1;
    private String KEY_IMAGE = "image";
    private String KEY_NAME = "author_name";
    private String KEY_DATE = "date";
    private String KEY_TITLE = "title";
    private String KEY_DESCRIPTION = "description";
    private String KEY_CONTENT = "content";
    static final int DIALOG_ID=0;
/*
* ORIGINAL MOBILE WS
* // private String UPLOAD_URL ="http://devkpl.com/KPL-Admin/saveNews";
* //private String KEY_IMAGE = "image";
* //private String KEY_NAME = "name";
* //private String KEY_DESCRIPTION = "preview";
* */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        tv_date= (TextView) findViewById(R.id.tv_date);
        tv_date.setVisibility(View.INVISIBLE);
        btnupdate= (Button) findViewById(R.id.btn_update);
        author=(EditText)findViewById(R.id.et_author);
        news_title= (EditText) findViewById(R.id.et_title);
        news_desc= (EditText) findViewById(R.id.et_description);
        news_content= (EditText) findViewById(R.id.et_content);

        final Calendar cal=Calendar.getInstance();
        year_x=cal.get(Calendar.YEAR);
        month_x=cal.get(Calendar.MONTH);
        day_x=cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();
        iv= (ImageView) findViewById(R.id.upload);
        upload_image= (Button) findViewById(R.id.upload_button);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updateInfo();
                if(isValidate()){
                    uploadToServer();
                }

            }
        });

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
    }

    private boolean isValidate() {
        boolean isValid = true;
        if (TextUtils.isEmpty(tv_date.getText().toString())){
            tv_date.setError("Date Missing");
            isValid=false;
        }
        if (TextUtils.isEmpty(author.getText().toString())){
            author.setError("Name Missing");
            isValid=false;
        }
        if (TextUtils.isEmpty(news_title.getText().toString())){
            news_title.setError("Title Missing");
            isValid=false;
        }
        if (TextUtils.isEmpty(news_desc.getText().toString())){
            news_desc.setError("Description Missing");
            isValid=false;
        }
        if (TextUtils.isEmpty(news_content.getText().toString())){
            news_content.setError("Content Missing");
            isValid=false;
        }

        return isValid;

    }

    private void updateInfo() {
        /*

        AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(this).setIcon(R.drawable.alert_info).setTitle("Please Confirm").setMessage("Are you sure you want to update?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                }
        );
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();
*/
        //Toast.makeText(Add_news.this,year_x+"/"+month_x+"/"+day_x,Toast.LENGTH_LONG).show();
    }


    public void showDialogOnButtonClick()
    {
        sdate= (Button) findViewById(R.id.select_date);
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

    private void onImageGalleryClicked(View v)
    {
        /*
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        // where do we want to find the data?
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        // finally, get a URI representation
        Uri data = Uri.parse(pictureDirectoryPath);

        // set the data and type.  Get all image types.
        photoPickerIntent.setDataAndType(data, "image/*");

        // we will invoke this activity, and get something back from it.
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
*/

    }

    private void showFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    private void uploadToServer(){


            //Showing the progress dialog
            final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            //Disimissing the progress dialog
                            loading.dismiss();
                            //Showing toast message of the response
                            Toast.makeText(Add_news.this, s , Toast.LENGTH_LONG).show();

                        }
                    },
                    new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            //Dismissing the progress dialog
                          //  Toast.makeText(Add_news.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                            loading.dismiss();
                            Toast.makeText(Add_news.this, "ERRROR", Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    //Converting Bitmap to String
                    String image = getStringImage(bitmap);
                    //Getting Image Name and other values
                    String name = author.getText().toString().trim();
                    String curr_date=tv_date.getText().toString().trim();
                    String title=news_title.getText().toString().trim();
                    String desc=news_desc.getText().toString().trim();
                    String content=news_content.getText().toString().trim();
                    //Creating parameters
                    Map<String,String> params = new Hashtable<String, String>();

                    //Adding parameters
                    params.put(KEY_IMAGE, image);
                    params.put(KEY_NAME, name);
                    params.put(KEY_DATE, curr_date);
                    params.put(KEY_TITLE, title);
                    params.put(KEY_DESCRIPTION, desc);
                    params.put(KEY_CONTENT, content);


                    //returning parameters
                    return params;

                }
            };

            //Creating a Request Queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            //Adding request to the queue
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
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                    iv.setImageResource(R.mipmap.ic_launcher);
                }


                //Setting the Bitmap to ImageView
               try{
                   iv.setImageBitmap(bitmap);
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
