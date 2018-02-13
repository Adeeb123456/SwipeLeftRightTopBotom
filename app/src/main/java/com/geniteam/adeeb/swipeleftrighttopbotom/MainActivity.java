package com.geniteam.adeeb.swipeleftrighttopbotom;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
ImageView imageView;
int[] images=new int[]{R.drawable.s0,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5};
int state=0;
RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=(ImageView)findViewById(R.id.imageView);
     //   imageView.setOnTouchListener(new RelativeLayoutTouchListener(MainActivity.this));
        relativeLayout=(RelativeLayout)findViewById(R.id.relative);
relativeLayout.setOnTouchListener(new RelativeLayoutTouchListener(MainActivity.this));

    }


    public class RelativeLayoutTouchListener implements View.OnTouchListener {

        static final String logTag = "ActivitySwipeDetector";
        private Context activity;
        static final int MIN_DISTANCE = 1;// TODO change this runtime based on screen resolution. for 1920x1080 is to small the 100 distance
        private float downX, downY, upX, upY;
int swiptLtoRCount=0;
        int swiptRtoLCount=4;

        // private MainActivity mMainActivity;

        public RelativeLayoutTouchListener(MainActivity mainActivity) {
            activity = mainActivity;
        }

        public void onRightToLeftSwipe() {
           // swiptRtoLCount++;
//imageView.setBackgroundResource(images[swiptRtoLCount]);
            if(swiptRtoLCount>=0) {
                imageView.setImageResource(images[swiptRtoLCount]);
                swiptLtoRCount--;
            }

                // startActivity(intent1);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
             //   finishAffinity();
            }else {
              //  finish();
            }

        }


        public void onLeftToRightSwipe() {

           // imageView.setBackgroundResource(images[swiptLtoRCount]);
            if(swiptLtoRCount<=4){
                imageView.setImageResource(images[swiptLtoRCount]);
                swiptLtoRCount++;
                swiptRtoLCount=swiptLtoRCount;
            }

            // handler.removeCallbacks(null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
               // finishAffinity();
            }
            //  System.exit(0);
        }

        public void onTopToBottomSwipe() {
Log.i("debug","swipe top to bottom");
        }

        public void onBottomToTopSwipe() {
            Log.i("debug","swipe bottom to top");

        }
        int k=0;






        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    downX = event.getX();
                    downY = event.getY();
                    Log.i("debug","downX "+downX);
                    Log.i("debug","downY "+downY);

                    return true;
                }
                case MotionEvent.ACTION_UP: {
                    upX = event.getX();
                    upY = event.getY();
                    Log.i("debug","upX "+downX);
                    Log.i("debug","upY "+downY);
                    float deltaX = downX - upX;
                    float deltaY = downY - upY;
                    Log.i("debug","deltaX "+deltaX);
                    Log.i("debug","deltaY "+deltaY);

                    Log.i("debug","min distance "+MIN_DISTANCE);


                    // swipe horizontal?
                    if (Math.abs(deltaX) > MIN_DISTANCE) {
                        // left or right
                        if (deltaX < 0) {
                            this.onLeftToRightSwipe();
                            Log.i("debug","swipe left to right");
                            return true;
                        }
                        if (deltaX > 0) {
                            this.onRightToLeftSwipe();
                            Log.i("debug","swipe righ to left");
                            return true;
                        }
                    } else {
                        Log.i("debug", "Swipe was only " + Math.abs(deltaX) + " long horizontally, need at least " + MIN_DISTANCE);
                        // return false; // We don't consume the event
                    }

                    // swipe vertical?
                    if (Math.abs(deltaY) > MIN_DISTANCE) {
                        // top or down
                        if (deltaY < 0) {
                            this.onTopToBottomSwipe();
                            return true;
                        }
                        if (deltaY > 0) {
                            this.onBottomToTopSwipe();
                            return true;
                        }
                    } else {
                        Log.i(logTag, "Swipe was only " + Math.abs(deltaX) + " long vertically, need at least " + MIN_DISTANCE);
                        // return false; // We don't consume the event
                    }

                    return false; // no swipe horizontally and no swipe vertically
                }// case MotionEvent.ACTION_UP:
            }
            return false;
        }

    }
}
