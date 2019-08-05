package ttatli.com.foursquareappdemo.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ttatli.com.foursquareappdemo.R;


public class SplashScreenActivity extends AppCompatActivity {
    Intent intent;
    public static String TAG = "MainActivity";
    private static int gosterim_suresi = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i =  new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);

                finish();
            }
        }, gosterim_suresi);
    }

}
