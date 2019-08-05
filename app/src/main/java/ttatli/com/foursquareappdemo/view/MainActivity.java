package ttatli.com.foursquareappdemo.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ttatli.com.foursquareappdemo.R;
import ttatli.com.foursquareappdemo.models.Location;
import ttatli.com.foursquareappdemo.models.Responce;
import ttatli.com.foursquareappdemo.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {
    EditText place, city;
    Button search;
    public Location location;

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;


    public MainPresenter presenter;

    GPSTracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        location = new Location();
        tracker = new GPSTracker(getApplicationContext());

        place = findViewById(R.id.edt_place);
        city = findViewById(R.id.edt_city);
        search = findViewById(R.id.btn_search);
        ClickEvent click = new ClickEvent();
        search.setOnClickListener(click);


        presenter = new MainPresenter(this);
        place.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.updatePlace(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.updateCity(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    @Override
    public void onNextIntent(Responce responce) {
        Intent intent = new Intent(this, PlacesListActivity.class);
        intent.putExtra("response", responce);
        startActivity(intent);

    }

    @Override
    public void updatePlace(String place) {

    }

    @Override
    public void updateCity(String city) {

    }

    public class ClickEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_search:

                    if (place.getText() != null || city.getText() != null) {
                        presenter.getNearPlaces(place.getText().toString(), tracker.getLatitude(), tracker.getLongitude(), "0DLR3D0KKH4HZM34UP0Z3LFYKKFUUEEYDJCVMY03FPQAMZO4", "XNNMOFM0BIVNA2O5URPNJQ5T5QODQPEHC5KSON30MRADVIPI", "20190804");
                    } else {
                        presenter.getPlaces(tracker.getLatitude(), tracker.getLongitude(), "0DLR3D0KKH4HZM34UP0Z3LFYKKFUUEEYDJCVMY03FPQAMZO4", "XNNMOFM0BIVNA2O5URPNJQ5T5QODQPEHC5KSON30MRADVIPI", "20190804");

                    }
            }
        }
    }
}

