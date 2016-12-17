package companyname.com.kpl.admin_files;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import companyname.com.kpl.R;
import companyname.com.kpl.RoundImage;

import static android.R.attr.bitmap;

public class Edit_team extends AppCompatActivity {
    RoundImage roundImage;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    FloatingActionButton uploadImage;
    EditText team_name;
    TextView team_code;
    ImageView iv;
    Spinner sel_trophy;
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
        team_name=(EditText)findViewById(R.id.tv_teamname);
        team_code=(TextView)findViewById(R.id.tv_team_code);
        iv= (ImageView) findViewById(R.id.iv_teamlogo);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.kpl_app_icon);

        uploadImage= (FloatingActionButton) findViewById(R.id.floatingActionButton);
        sel_trophy= (Spinner) findViewById(R.id.spinner_trophy);
        iv.setImageResource(getIntent().getIntExtra("image",00));
        team_code.setText("Code: "+getIntent().getStringExtra("id"));
        team_name.setText("Team Name: "+getIntent().getStringExtra("name"));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.trophies_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner

        sel_trophy.setAdapter(adapter);


        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }

            private void showFileChooser() {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
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
