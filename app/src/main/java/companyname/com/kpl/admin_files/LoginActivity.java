package companyname.com.kpl.admin_files;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import companyname.com.kpl.MainActivity;
import companyname.com.kpl.MySingleton;
import companyname.com.kpl.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String LOGIN_URL = "http://www.devkpl.com/login_new.php";
    String app_server_url = "http://devkpl.com/fcm_insert.php";
    public static final String KEY_USERNAME="adm_username";
    public static final String KEY_PASSWORD="adm_password";
    public static final String KEY_NAME="name";
    public static final String SHARED_PREF_NAME="myloginapp";
    public static final String LOGGEDIN_SHARED_PREF="loggedin";
    private ProgressDialog loading;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private String username,name;
    private String password;
    private boolean loggedIn=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(this);
        final Animation an= AnimationUtils.loadAnimation(getBaseContext(),R.anim.splash_start);



    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        loggedIn=sharedPreferences.getBoolean(LOGGEDIN_SHARED_PREF,false);

        if(loggedIn)
        {
            openProfile();
        }
    }

    private void userLogin() {
        username = editTextUsername.getText().toString().trim();
        name=username;
        password = editTextPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                           /*
                            pb.setVisibility(View.INVISIBLE);
                            editTextPassword.setText("");
                            editTextUsername.setText("");
                            openProfile();
                            */
                            /*
                            * USING SHARED PREFERENECES
                            * SharedPreferences sharedPreferences=LoginActivity.this.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putBoolean(LOGGEDIN_SHARED_PREF,true);
                            editor.commit();
                            * */
                            openProfile();

                        }else{
                            //pb.setVisibility(View.INVISIBLE);
                            loading.dismiss();
                            editTextUsername.setText("");
                            editTextPassword.setText("");
                            editTextUsername.setFocusable(true);
                            Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        editTextUsername.setText("");
                        editTextPassword.setText("");
                        editTextUsername.setFocusable(true);
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_USERNAME,username);
                map.put(KEY_PASSWORD,password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void openProfile(){
        Intent i = new Intent(getBaseContext(), Admin_Activity.class);
        //username = "HELO SOURABH";
        String abc=name;
        i.putExtra("name", abc);
       // intent.putExtra("name",username);
        startActivity(i);
        finish();
    }


    @Override
    public void onClick(View v) {

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);
        //pb.setVisibility(View.VISIBLE);
        userLogin();

    }


}
