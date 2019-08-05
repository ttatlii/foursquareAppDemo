package ttatli.com.foursquareappdemo.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;


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

    //PopupWindow pw;
    //GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);
        // map= ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.popupmapview)).getMap();
        listView = findViewById(R.id.listview_places);


       Responce responce= (Responce)getIntent().getSerializableExtra("response");
        venueList = new ArrayList<>();
        venueList=responce.venues;
        PlaceListAdapter adapter = new PlaceListAdapter(getApplicationContext(), venueList);
        listView.setAdapter(adapter);
       listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
              // initiatePopupWindow();

               return false;
           }
       });

    }
    /*private void initiatePopupWindow()
    {
        try
        {

            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) PlacesListActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout = inflater.inflate(R.layout.activity_places_list,
                    (ViewGroup) findViewById(R.id.lnr_google_maps));

            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, 300, 470, true);

            // display the popup in the center
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);


            map= ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.popupmapview)).getMap();


            pw.dismiss();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }*/
}
