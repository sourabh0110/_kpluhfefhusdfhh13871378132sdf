package companyname.com.kpl;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class Add_news extends AppCompatActivity {
    private Button sdate,btnupdate;
    private ImageView iv;
    private TextView tv_date;
    private ImageButton upload_image;
    private static final int PICK_IMAGE=100;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    Uri imageUri;
    int year_x,month_x,day_x;
    static final int DIALOG_ID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        tv_date= (TextView) findViewById(R.id.tv_date);
        tv_date.setVisibility(View.INVISIBLE);
        btnupdate= (Button) findViewById(R.id.btn_update);
        final Calendar cal=Calendar.getInstance();
        year_x=cal.get(Calendar.YEAR);
        month_x=cal.get(Calendar.MONTH);
        day_x=cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();
        updateInfo();
        iv= (ImageView) findViewById(R.id.upload);
        upload_image= (ImageButton) findViewById(R.id.upload_button);

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImageGalleryClicked(v);
            }
        });
    }

    private void updateInfo() {

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // if we are here, everything processed successfully.
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                // if we are here, we are hearing back from the image gallery.

                // the address of the image on the SD Card.
                Uri imageUri = data.getData();

                // declare a stream to read the image data from the SD Card.
                InputStream inputStream;

                // we are getting an input stream, based on the URI of the image.
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);

                    // get a bitmap from the stream.
                    Bitmap image = BitmapFactory.decodeStream(inputStream);


                    // show the image to the user
                    iv.setImageBitmap(image);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    // show a message to the user indictating that the image is unavailable.
                    //Toast.makeText(, "Unable to open image", Toast.LENGTH_LONG).show();
                }

            }
        }

    }
}
