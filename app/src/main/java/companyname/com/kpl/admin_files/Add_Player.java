package companyname.com.kpl.admin_files;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import companyname.com.kpl.MainActivity;
import companyname.com.kpl.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class Add_Player extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    /**
     *ALL SPINNER ITEMS
     *
     */
    //Declaring an Spinner
    private Spinner spinner;

    //An ArrayList for Spinner Items
    private ArrayList<String> players;

    //JSON Array
    private JSONArray result;

    //DECLARING TVs AND ETs
    private TextView textViewTeamName,textViewCode;
    private EditText playerName,contactNo ;
    CircleImageView iv;
    Button add_date;
    TextView textViewdispdate;
    private Button addnewplayer;
    private Bitmap bitmap;
    private static final int PICK_IMAGE=100;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    private int PICK_IMAGE_REQUEST = 1;
    static final int DIALOG_ID=0;
    /*
    * KEY VARIABLES FOR PLAYER TABLE
    * */
    private String UPLOAD_URL ="http://devkpl.com/player/upload.php";
    private String KEY_IMAGE = "usr_profile_pic";
    private String KEY_USERNAME = "usr_name";
    private String KEY_DOB = "usr_dob";
    private String KEY_TEAMCODE = "usr_team_code";
    private String KEY_TEAMNAME = "team_name";
    private String KEY_MOBNO = "usr_mobile_number";

    /*
    * DEMO URLS
    * */
    String DATA_URL = "http://devkpl.com/KPL-Admin/wsGetTeams";
    public static final String JSON_ARRAY = "server_response";
    public static final String TAG_TEAMNAME = "tm_name";
    public static final String TAG_CODE = "tm_code";
    public static final String TAG_COURSE = "course";
    int year_x,month_x,day_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_add_player);
        /*
        * SPINNER ITEMS
        * */
        //Initializing the ArrayList
        players = new ArrayList<String>();

        //Initializing Spinner
        spinner = (Spinner) findViewById(R.id.spinner_teamlist);

        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener
        spinner.setOnItemSelectedListener(this);
        /*
        * ASSIGNING VALUES
        * */
        textViewdispdate= (TextView) findViewById(R.id.tv_show_date);
        textViewdispdate.setVisibility(View.INVISIBLE);
        iv= (CircleImageView) findViewById(R.id.civ_add_player);
        playerName=(EditText)findViewById(R.id.et_player_name);
        contactNo=(EditText)findViewById(R.id.et_contactno);
        textViewTeamName = (TextView) findViewById(R.id.tv_static_teamname);
        textViewCode = (TextView) findViewById(R.id.tv_static_teamcode);
        getdata();
        /*
        * ADDING TO THE SERVER(addnewplayer)
        * */
        addnewplayer=(Button)findViewById(R.id.btn_addnewplayer);
        addnewplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadToServer();
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
                //Toast.makeText(getApplicationContext(),"Clickable",Toast.LENGTH_LONG).show();
            }
        });
        final Calendar cal=Calendar.getInstance();
        year_x=cal.get(Calendar.YEAR);
        month_x=cal.get(Calendar.MONTH);
        day_x=cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();

    }

    private void uploadToServer() {

        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(Add_Player.this, s , Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        //  Toast.makeText(Add_news.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                        loading.dismiss();
                        Toast.makeText(Add_Player.this, "ERRROR", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);
                //Getting Image Name and other values
                String teamname = textViewTeamName.getText().toString().trim();
                String teamcode=textViewCode.getText().toString().trim();
                String dispdate=textViewdispdate.getText().toString().trim();
                String player_name=playerName.getText().toString().trim();
                String contactno=contactNo.getText().toString().trim();

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_USERNAME, player_name);
                params.put(KEY_DOB, dispdate);
                params.put(KEY_TEAMCODE, teamcode);
                params.put(KEY_TEAMNAME, teamname);
                params.put(KEY_MOBNO, contactno);


                //returning parameters
                return params;

            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);




    }

    private void getdata() {

        StringRequest stringRequest = new StringRequest(DATA_URL,
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
    }


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
    spinner.setAdapter(new ArrayAdapter<String>(Add_Player.this, android.R.layout.simple_spinner_dropdown_item, players));
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
            textViewdispdate.setText(year_x+"/"+month_x+"/"+day_x);

            if(textViewdispdate==null)
            {
                textViewdispdate.setVisibility(View.INVISIBLE);
            }
            else
            {
                textViewdispdate.setVisibility(View.VISIBLE);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        textViewTeamName.setText(getName(position));
        textViewCode.setText(getCode(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        textViewTeamName.setText("");
        textViewCode.setText("");
    }
}
