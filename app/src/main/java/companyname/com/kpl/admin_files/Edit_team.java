package companyname.com.kpl.admin_files;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import companyname.com.kpl.R;
import companyname.com.kpl.RoundImage;
import companyname.com.kpl.recycler_listviews_adapters.BackgroundTask_Feat_Player;
import companyname.com.kpl.recycler_listviews_adapters.BackgroundTask_PremiereLeague;
import companyname.com.kpl.recycler_listviews_adapters.BackgroundTask_Team;
import companyname.com.kpl.recycler_listviews_adapters.BackgroundTask_news;
import companyname.com.kpl.recycler_listviews_adapters.News_Details;

import static android.R.attr.bitmap;

public class Edit_team extends AppCompatActivity {
    RoundImage roundImage;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    Button edit,delete,update,viewplayers;
    EditText team_name;
    TextView team_code;
    ImageView iv;
    Spinner sel_trophy;
    private ProgressDialog loading;
    public static final String DATA_URL="http://devkpl.com/alldeletequeries/getDataID_teams_delete.php?id=";
    String defaultTextForSpinner = "Your deafult text here";
    String[] arrayForSpinner = {"One", "Two", "Three"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_edit_team);
        viewplayers= (Button) findViewById(R.id.view_all_players);
        edit=(Button)findViewById(R.id.btn_edit_team);
        delete=(Button)findViewById(R.id.btn_del_team);
        update=(Button)findViewById(R.id.btn_update_team);
        team_name = (EditText) findViewById(R.id.tv_teamname);
        team_code = (TextView) findViewById(R.id.tv_team_code);


        iv = (ImageView) findViewById(R.id.iv_teamlogo);
        iv.setEnabled(false);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kpl_app_icon);
        sel_trophy = (Spinner) findViewById(R.id.spinner_trophy);


        /*
        * GETTING THE INTENT VALUES
        * */

        team_code.setText(getIntent().getStringExtra("id"));
        team_name.setText(getIntent().getStringExtra("name"));
        final String teamcode=team_code.getText().toString().trim();
        //Toast.makeText(getApplicationContext(),""+teamcode,Toast.LENGTH_LONG).show();
        team_code.setEnabled(false);
        team_name.setEnabled(false);
        /*
* FOR GLIDE
* */
        String image_url = "";
        image_url = getIntent().getStringExtra("image");

/*
* USING GLIDE TO CONVERT URL INTO IMAGE
* */
        Glide.with(getApplicationContext()).load(image_url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.kpl_app_icon)
                .into(iv);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.trophies_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner

        sel_trophy.setAdapter(adapter);
        update.setEnabled(false);
/*
* VIEW ALL PLAYERS BUTTON IMPLEMENTATION
* */
        viewplayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BackgroundTask_Team backgroundTask_team =new BackgroundTask_Team(Edit_team.this,teamcode);
                backgroundTask_team.execute();
                viewplayers.setEnabled(false);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Edit_team.this);
                alertDialog.setTitle("Please Confirm");
                alertDialog.setMessage("Are You Sure?");
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        team_name.setEnabled(true);
                        edit.setEnabled(false);
                        update.setEnabled(true);
                      //  iv.setEnabled(true);
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
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Edit_team.this);
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

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

    }

    private void deletedata() {
        String id = team_code.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(this,"Please wait...","Deleting this Record...",false,false);

        String url = DATA_URL+team_code.getText().toString().trim();
        // String url = Config.DATA_URL;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(),"RECORD DELETED SUCCESSFULLY!",Toast.LENGTH_LONG).show();
                /*
                * STARTING INTENT AND FINISHING!
                *Intent i=new Intent(Edit_team.this, TwoFragment_admin.class);
                startActivity(i);
                finish();
                * */
                Toast.makeText(getApplicationContext(),"TEAM DELETED SUCCESSFULLY!",Toast.LENGTH_LONG).show();
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

    private void showFileChooser() {

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

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                iv.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
