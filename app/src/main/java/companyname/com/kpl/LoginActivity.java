package companyname.com.kpl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String LOGIN_URL = "http://www.devkpl.com/login_new.php";

    public static final String KEY_USERNAME="adm_username";
    public static final String KEY_PASSWORD="adm_password";
    public static final String KEY_NAME="name";

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private ProgressBar pb;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pb=(ProgressBar)findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(this);
        final Animation an= AnimationUtils.loadAnimation(getBaseContext(),R.anim.splash_start);



    }


    private void userLogin() {
        username = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            pb.setVisibility(View.INVISIBLE);
                            editTextPassword.setText("");
                            editTextUsername.setText("");
                            openProfile();
                        }else{
                            pb.setVisibility(View.INVISIBLE);
                            editTextPassword.setText("");
                            editTextUsername.setText("");
                            Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        editTextPassword.setText("");
                        editTextUsername.setText("");
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
        Intent intent = new Intent(this, Admin_Activity.class);
        intent.putExtra(KEY_NAME, username);
        editTextPassword.setText("");
        editTextUsername.setText("");
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        pb.setVisibility(View.VISIBLE);
        userLogin();
    }
}
