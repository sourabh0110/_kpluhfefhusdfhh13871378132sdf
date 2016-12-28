package companyname.com.kpl.recycler_listviews_adapters;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import companyname.com.kpl.R;
import companyname.com.kpl.admin_files.Add_Featured_player;
import companyname.com.kpl.admin_files.Admin_Activity;
import companyname.com.kpl.admin_files.FourFragment_admin;

public class FeaturedPlayer_Details extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String DATA_URL = "http://devkpl.com/alldeletequeries/getDataID_featuredplayer_delete.php?id=";
    private Spinner spinner;
    //An ArrayList for Spinner Items
    private ArrayList<String> players;
    /*
   * KEY VARIABLES FOR PLAYER TABLE
   * */
    private String UPLOAD_URL ="http://devkpl.com/featuredplayer/upload.php";
    private String UPDATE_URL="http://devkpl.com/allupdatequeries/update_featuredplayer.php";
    private String KEY_ID="id";
    private String KEY_IMAGE = "image";
    private String KEY_MATCHES = "matches_played";
    private String KEY_USERNAME = "player_name";
    private String KEY_OLD_USERNAME = "old_player_name";
    private String KEY_POS = "player_pos";
    private String KEY_TEAMNAME = "player_team";
    private String KEY_GOALS = "goals_scored";
    private String KEY_RED = "red_cards";
    private String KEY_YELLOW = "yellow_cards";
    private String KEY_ASSISTS = "assists";
    //JSON Array
    private JSONArray result;
    String DATA_URL_TEAMS = "http://devkpl.com/KPL-Admin/wsGetTeams";
    public static final String JSON_ARRAY = "server_response";
    public static final String TAG_TEAMNAME = "tm_name";
    public static final String TAG_CODE = "tm_code";
    private ProgressDialog loading;
    int year_x, month_x, day_x;
    private Bitmap bitmap;
    private static final int PICK_IMAGE = 100;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    private int PICK_IMAGE_REQUEST = 1;
    static final int DIALOG_ID = 0;
    Context ctx;
    ImageView iv;
    TextView pid, prev_pos, team_name, player_staticpath,textViewTeamName,textViewCode,curr_pos;
    Button edit, delete, update, sdate;
    EditText name, matches,goals,yellow,red,assists;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured_player_details);
        players = new ArrayList<String>();



        //Initializing Spinner
        spinner = (Spinner) findViewById(R.id.spinner_fplayer_pos);

        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener
        spinner.setOnItemSelectedListener(this);
                /*
        * CALENDAR CONTROLS
        * */
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        /*
        * ASSIGNING VALUES
        * */
        pid = (TextView) findViewById(R.id.tv_edit_fplayer_id);
        pid.setEnabled(false);
        prev_pos = (TextView) findViewById(R.id.tv_fplayer_prev_pos);
        team_name = (TextView) findViewById(R.id.tv_fplayer_original_team);
        name = (EditText) findViewById(R.id.et_edit_fplayer_name);
        name.setEnabled(false);
        matches = (EditText) findViewById(R.id.et_fplayer_matches);
        goals = (EditText) findViewById(R.id.et_fplayer_goals);
        yellow = (EditText) findViewById(R.id.et_fplayer_yellow);
        red = (EditText) findViewById(R.id.et_fplayer_red);
        assists  = (EditText) findViewById(R.id.et_fplayer_assists);
        //player_staticpath = (TextView) findViewById(R.id.tv_player_imagepath);


        /*
        * FOR OLD AND NEW IMAGE
        * */
        iv = (ImageView) findViewById(R.id.iv_edit_image_fplayer);
        iv.setEnabled(false);
        final int[] flag = {0};
        textViewTeamName=(TextView)findViewById(R.id.tv_team_name);
        textViewCode=(TextView)findViewById(R.id.tv_team_code);
        delete = (Button) findViewById(R.id.btn_del_player);
        getdataspinner();



        /*
* INTENT EXTRAS
* */
        pid.setText("" + getIntent().getStringExtra("id"));
        name.setText("" + getIntent().getStringExtra("name"));
        prev_pos.setText("" + getIntent().getStringExtra("pos"));
        team_name.setText("" + getIntent().getStringExtra("teamname"));
        matches.setText("" + getIntent().getStringExtra("matches"));
        goals.setText("" + getIntent().getStringExtra("goals"));
        yellow.setText("" + getIntent().getStringExtra("yellow"));
        red.setText("" + getIntent().getStringExtra("red"));
        assists.setText("" + getIntent().getStringExtra("assists"));

        /*
* FOR GLIDE
* */
        String image_url = "";
        image_url = getIntent().getStringExtra("image");


/*
* USING GLIDE TO CONVERT URL INTO IMAGE
* */

        Glide.with(getApplication()).load(image_url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .error(R.mipmap.ic_launcher)
                .into(iv);
        Toast.makeText(getApplicationContext(),"IMAGE SET",Toast.LENGTH_LONG).show();

        update = (Button) findViewById(R.id.btn_update_player);
        update.setEnabled(false);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(FeaturedPlayer_Details.this);
                alertDialog.setTitle("Confirm Update");
                alertDialog.setMessage("The changes you make cannot be reverted!!");
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //CALLING METHOD TO IMPLEMENT UPDATE!
                        update();

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

        edit = (Button) findViewById(R.id.btn_edit_player);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(FeaturedPlayer_Details.this);
                alertDialog.setTitle("Please Confirm");
                alertDialog.setMessage("Are You Sure?");
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edit.setEnabled(false);
                        update.setEnabled(true);
                        delete.setEnabled(false);
                        name.setEnabled(true);
                        goals.setEnabled(true);
                        matches.setEnabled(true);
                        yellow.setEnabled(true);
                        red.setEnabled(true);
                        //iv.setImageBitmap(null);
                        //iv.setEnabled(true);
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
        showDialogOnButtonClick();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(FeaturedPlayer_Details.this);
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
                Toast.makeText(getApplicationContext(),"Clicked!",Toast.LENGTH_LONG).show();
                showFileChooser();
            }
        });


    }

    private void getdataspinner() {
        StringRequest stringRequest = new StringRequest(DATA_URL_TEAMS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getTeams(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
                    }
                });

        //Creating a request queue
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

    private void showFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void deletedata() {

        String id = pid.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(this, "Please wait...", "Deleting this Record...", false, false);

        String url = DATA_URL + pid.getText().toString().trim();
        // String url = Config.DATA_URL;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                Bundle b=new Bundle();
                //showJSON(response);
                Toast.makeText(getApplicationContext(), "NEWS DELETED SUCCESSFULLY!", Toast.LENGTH_LONG).show();
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


    private void showDialogOnButtonClick() {

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            year_x = year;
            month_x = month + 1;
            day_x = dayOfMonth;

            //Toast.makeText(Add_news.this,year_x+"/"+month_x+"/"+day_x,Toast.LENGTH_LONG).show();
            //player_dob.setText(year_x + "/" + month_x + "/" + day_x);

            //if (player_dob == null) {
             //   player_dob.setVisibility(View.INVISIBLE);
            //} else {
             //   player_dob.setVisibility(View.VISIBLE);
            //}
        }

    };

    private void getTeams(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                players.add(json.getString(TAG_TEAMNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(FeaturedPlayer_Details.this, android.R.layout.simple_spinner_dropdown_item, players));
    }
    private String getName(int position){
        String name="",code="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            name = json.getString(TAG_TEAMNAME);
            //code = json.getString(TAG_CODE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }
    private String getCode(int position){
        String code="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            //name = json.getString(TAG_TEAMNAME);
            code = json.getString(TAG_CODE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return code;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

          Context ctx = null;
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                } catch (OutOfMemoryError e) {
                    Toast.makeText(getApplicationContext(), "FILE SIZE IS LARGE,CHOOSE ANOTHER FILE", Toast.LENGTH_LONG).show();
                    iv.setImageBitmap(null);
                }


                //Setting the Bitmap to ImageView
                try {
                    iv.setImageBitmap(bitmap);
                } catch (OutOfMemoryError e) {
                    Toast.makeText(getApplicationContext(), "FILE SIZE IS LARGE,CHOOSE ANOTHER FILE", Toast.LENGTH_LONG).show();
                    iv.setImageBitmap(null);
                }

                //iv.setImageBitmap(ImageDecoder.decodeSampledBitmapFromResource(this.getResources(),R.id.upload,100,100));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//        textViewTeamName.setText(getName(position));
  //      textViewCode.setText(getCode(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    //    textViewTeamName.setText("");
      //  textViewCode.setText("");
    }

    private void update() {

        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Updating Records...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPDATE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        Toast.makeText(FeaturedPlayer_Details.this, "Record Updated Successfully!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        //  Toast.makeText(Add_news.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                        loading.dismiss();
                        Toast.makeText(FeaturedPlayer_Details.this,"ERROR!", Toast.LENGTH_LONG).show();
                    }
                })



        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String

                String image = getIntent().getStringExtra("image");
                //String player_name=getIntent().getStringExtra("name");
                //String image = getStringImage(bitmap);
                String id1= pid.getText().toString();
                String teamname = team_name.getText().toString().trim();
                String new_player_name=name.getText().toString().trim();
                String player_pos=prev_pos.getText().toString().trim();
                String goal=goals.getText().toString().trim();
                String red1=red.getText().toString().trim();
                String yellow1=yellow.getText().toString().trim();
                String assist=assists.getText().toString().trim();
                String matches1=matches.getText().toString().trim();
                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_USERNAME, new_player_name);
                //params.put(KEY_OLD_USERNAME, player_name);
                params.put(KEY_TEAMNAME, teamname);
                params.put(KEY_POS, player_pos);
                params.put(KEY_GOALS, goal);
                params.put(KEY_YELLOW, yellow1);
                params.put(KEY_RED, red1);
                params.put(KEY_ASSISTS,assist);
                params.put(KEY_MATCHES,matches1);
                params.put(KEY_ID,id1);



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
}
