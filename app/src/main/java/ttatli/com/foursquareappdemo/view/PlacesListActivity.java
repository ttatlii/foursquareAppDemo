package ttatli.com.foursquareappdemo.view;

import android.app.Dialog;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import ttatli.com.foursquareappdemo.R;
import ttatli.com.foursquareappdemo.adapter.PlaceListAdapter;
import ttatli.com.foursquareappdemo.models.Responce;
import ttatli.com.foursquareappdemo.models.Venue;

public class PlacesListActivity extends AppCompatActivity {
    ListView listView;
    List<Venue> venueList;
    Responce responce;
    GPSTracker tracker;
    public Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);
        dialog = new Dialog(PlacesListActivity.this);
        // map= ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.popupmapview)).getMap();
        listView = findViewById(R.id.listview_places);
        tracker = new GPSTracker(getApplicationContext());

        Responce responce = (Responce) getIntent().getSerializableExtra("response");
        venueList = new ArrayList<>();
        venueList = responce.venues;
        PlaceListAdapter adapter = new PlaceListAdapter(getApplicationContext(), venueList);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog = new Dialog(PlacesListActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                /////make map clear
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

                dialog.setContentView(R.layout.activity_maps);////your custom content

                MapView mMapView = (MapView) dialog.findViewById(R.id.mapView);
                MapsInitializer.initialize(PlacesListActivity.this);

                mMapView.onCreate(dialog.onSaveInstanceState());
                mMapView.onResume();


                mMapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(final GoogleMap googleMap) {
                        LatLng posisiabsen = new LatLng(tracker.getLatitude(), tracker.getLongitude()); ////your lat lng
                        googleMap.addMarker(new MarkerOptions().position(posisiabsen).title("Yout title"));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(posisiabsen));
                        googleMap.getUiSettings().setZoomControlsEnabled(true);
                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                    }
                });


                dialog.show();

                return false;
            }
        });

    }


}
