package companyname.com.kpl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Add_news extends AppCompatActivity {
    private ImageView iv;
    private ImageButton upload_image;
    private static final int PICK_IMAGE=100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        iv= (ImageView) findViewById(R.id.upload);
        upload_image= (ImageButton) findViewById(R.id.upload_button);

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void openGallery()
    {
        Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK && resultCode==PICK_IMAGE)
        {
            imageUri=data.getData();
            iv.setImageURI(imageUri);

        }

    }
}
