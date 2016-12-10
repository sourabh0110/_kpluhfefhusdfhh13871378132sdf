package companyname.com.kpl;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    int a=240, b= 19, c= 20;
    private int progressStatus = 0;
    TextView tv;
    ProgressBar pBar,pb;
    int pStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       // tv = (TextView) findViewById(R.id.textView1);
        //pBar = (ProgressBar) findViewById(R.id.progressBar1);
        pb=(ProgressBar)findViewById(R.id.progressBar3);
        pb.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < 100){
                    // Update the progress status
                    progressStatus +=1;

                    // Try to sleep the thread for 20 milliseconds
                    try{
                        Thread.sleep(20);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(progressStatus);
                            // Show the progress on TextView
                           // tv.setText(progressStatus+"");
                        }
                    });
                }

                finish();
                //Intent i=new Intent(Splash.this,ReaderActivity.class);
                Intent i=new Intent(Splash.this,MainActivity.class);
                startActivity(i);
            }


        }).start(); // Start the operation




    }
/*
*
* IF PROGRESS BAR IS SET TO CIRCULAR
*
* */
/*
    final ImageView iv=(ImageView)findViewById(R.id.WeplayLogo);
    final Animation an= AnimationUtils.loadAnimation(getBaseContext(),R.anim.splash_start);


    iv.startAnimation(an);
    an.setAnimationListener(new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

            pb.setVisibility(View.VISIBLE);

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            finish();
            //Intent i=new Intent(Splash.this,ReaderActivity.class);
            Intent i=new Intent(Splash.this,MainActivity.class);
            startActivity(i);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        while (pStatus <= 100) {

                            handler.post(new Runnable() {

                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                    pBar.setProgress(pStatus);
                                    pBar.setSecondaryProgress(pStatus + 5);
                                    tv.setText(pStatus + "/" + pBar.getMax());
                                }
                            });
                            try {
                                // Sleep for 200 milliseconds.
                                // Just to display the progress slowly
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            pStatus++;
                        }
                    }
                }).start();

        }
    });

*/

    }

