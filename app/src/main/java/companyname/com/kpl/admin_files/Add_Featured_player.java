package companyname.com.kpl.admin_files;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import companyname.com.kpl.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class Add_Featured_player extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Spinner spinner,spinner_pos;

    //An ArrayList for Spinner Items
    private ArrayList<String> players;
    private ArrayList<String> position;

    //JSON Array
    private JSONArray result;

    //DECLARING TVs AND ETs
    private TextView textViewTeamName,textViewCode,textViewPosition;
    private EditText playerName,goals,redcard,yellowcard,assists ;
    ImageView iv;
    private Button addnewfeaturedplayer;
    private Bitmap bitmap;
    private static final int PICK_IMAGE=100;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    private int PICK_IMAGE_REQUEST = 1;
    /*
    * KEY VARIABLES FOR PLAYER TABLE
    * */
    private String UPLOAD_URL ="http://devkpl.com/featuredplayer/upload.php";
    private String KEY_IMAGE = "image";
    private String KEY_USERNAME = "player_name";
    private String KEY_POS = "player_pos";
    private String KEY_TEAMNAME = "player_team";
    private String KEY_GOALS = "goals_scored";
    private String KEY_RED = "red_cards";
    private String KEY_YELLOW = "yellow_cards";
    private String KEY_ASSISTS = "assists";

    /*
    * SPINNER URL
    * */
    String DATA_URL = "http://devkpl.com/KPL-Admin/wsGetTeams";
    public static final String JSON_ARRAY = "server_response";
    public static final String TAG_TEAMNAME = "tm_name";
    public static final String TAG_CODE = "tm_code";
    public static final String TAG_COURSE = "course";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_add_featured_player);

        /*
        * SPINNER ITEMS
        * */
        //Initializing the ArrayList
        players = new ArrayList<String>();

        //Initializing Spinner
        spinner = (Spinner) findViewById(R.id.spinner_featured_teamlist);
        spinner_pos=(Spinner)findViewById(R.id.spinner_featured_pos);
        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener
        spinner.setOnItemSelectedListener(this);
        //spinner_pos.setOnItemClickListener(this);
        /*
        * SET SPINNER ADAPTER
        * */
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.player_position_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner

        spinner_pos.setAdapter(adapter);

        /*
        * ASSIGNING VALUES/IDS
        * */
        iv= (ImageView) findViewById(R.id.civ_add_featured_player);
        playerName=(EditText)findViewById(R.id.et_featured_player_name);
        textViewTeamName = (TextView) findViewById(R.id.tv_static_featured_teamname);
        textViewCode = (TextView) findViewById(R.id.tv_static_featured_teamcode);
        textViewPosition=(TextView)findViewById(R.id.tv_static_featured_pos);
        goals=(EditText)findViewById(R.id.et_featured_goals);
        redcard= (EditText) findViewById(R.id.et_featured_red);
        yellowcard= (EditText) findViewById(R.id.et_featured_yellow);
        assists= (EditText) findViewById(R.id.et_featured_assist);


        getdata();
        /*
        * ADDING TO THE SERVER(addnewplayer)
        * */
        addnewfeaturedplayer=(Button)findViewById(R.id.btn_addnewplayer);
        addnewfeaturedplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isValidate()){
                    uploadToServer();
                }
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
                //Toast.makeText(getApplicationContext(),"Clickable",Toast.LENGTH_LONG).show();
            }
        });


    }

    private boolean isValidate() {
        boolean isValid = true;
        if (TextUtils.isEmpty(playerName.getText().toString())){
            playerName.setError("Name Missing");
            isValid=false;
        }
        if (TextUtils.isEmpty(goals.getText().toString())){
            goals.setError("Goals Missing");
            isValid=false;
        }
        if (TextUtils.isEmpty(yellowcard.getText().toString())){
            yellowcard.setError("YC Missing");
            isValid=false;
        }
        if (TextUtils.isEmpty(redcard.getText().toString())){
            redcard.setError("RC Missing");
            isValid=false;
        }
        if (TextUtils.isEmpty(assists.getText().toString())){
            assists.setError("Assist Missing");
            isValid=false;
        }

        return isValid;

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
                        Toast.makeText(Add_Featured_player.this, s , Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        //  Toast.makeText(Add_news.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                        loading.dismiss();
                        Toast.makeText(Add_Featured_player.this,""+volleyError, Toast.LENGTH_LONG).show();
                    }
                })



        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);
                //Getting Image Name and other values
                String teamname = textViewTeamName.getText().toString().trim();
                String player_name=playerName.getText().toString().trim();
                String player_pos=textViewPosition.getText().toString().trim();
                String goal=goals.getText().toString().trim();
                String red=redcard.getText().toString().trim();
                String yellow=yellowcard.getText().toString().trim();
                String assist=assists.getText().toString().trim();
                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_USERNAME, player_name);
                params.put(KEY_TEAMNAME, teamname);
                params.put(KEY_POS, player_pos);
                params.put(KEY_GOALS, goal);
                params.put(KEY_YELLOW, yellow);
                params.put(KEY_RED, red);
                params.put(KEY_ASSISTS,assist);


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
        spinner.setAdapter(new ArrayAdapter<String>(Add_Featured_player.this, android.R.layout.simple_spinner_dropdown_item, players));
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
        String item = parent.getItemAtPosition(position).toString();
        textViewPosition.setText(item);
        /*
        * USING ID TAG FOR 2 SPINNERS
        * */

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        textViewTeamName.setText("");
        textViewCode.setText("");
    }


}
