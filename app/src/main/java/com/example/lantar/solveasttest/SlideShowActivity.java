package com.example.lantar.solveasttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;

public class SlideShowActivity extends Activity {

    private ViewFlipper mViewFlipper;
    private static int interval = 4000;
    private String path;
    private ImageView firstImage;
    private CountDownTimer timer;
    private static int numberCurrentPhoto = 0;
    private TextView namePhoto;
    private boolean timerWork = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);

        firstImage = (ImageView) findViewById(R.id.first);
        namePhoto = (TextView) findViewById(R.id.namePhoto);

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, ifilter);
        PowerConnectionReceiver powerConnectionReceiver = new PowerConnectionReceiver();
        powerConnectionReceiver.onReceive(getApplicationContext(), batteryStatus);

        onActivityResult(OptionsDate.getShange());
        mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);


        findViewById(R.id.play).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (OptionsDate.getPath() == null) {
                    Toast.makeText(getApplicationContext(), SlideShowActivity.this.getString(R.string.fail_path), Toast.LENGTH_SHORT).show();
                    return;
                }
                //sets auto flipping
                mViewFlipper.setAutoStart(true);
                mViewFlipper.setFlipInterval(interval);
                mViewFlipper.startFlipping();
                timer.onTick(interval);
                timer.start();
                timerWork = true;

            }
        });

        findViewById(R.id.stop).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //stop auto flipping
                mViewFlipper.stopFlipping();
                timer.cancel();
                timer.onFinish();
                timerWork = false;
            }
        });

        findViewById(R.id.options).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent optionIntent = new Intent(getApplicationContext(), Options.class);
                startActivity(optionIntent);
            }
        });


        timer = new CountDownTimer(interval, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {


            }

            @Override
            public void onFinish() {
                if (timerWork && OptionsDate.getList().size() != 0) {

                    File file = new File(OptionsDate.getList().get(numberCurrentPhoto));
                    setImage(OptionsDate.getList().get(numberCurrentPhoto));
                    namePhoto.setText(file.getName());
                    numberCurrentPhoto++;
                    if (numberCurrentPhoto >= OptionsDate.getList().size() - 1)
                        numberCurrentPhoto = 0;

                    timer.start();
                }
            }
        };
    }



    protected void onActivityResult(int shange) {
        if (shange == 0) {
            return;
        }

        interval = OptionsDate.getInterval();
        path = OptionsDate.getPath();

        if (OptionsDate.getPath() == null) {
            Toast.makeText(getApplicationContext(), SlideShowActivity.this.getString(R.string.fail_path), Toast.LENGTH_SHORT).show();
            return;
        }

        FileFilter.getJpg();
    }

    private void setImage(String path) {

        File file = new File(path);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        firstImage.setImageBitmap(bitmap);
    }


}


