package ttatli.com.foursquareappdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ttatli.com.foursquareappdemo.R;
import ttatli.com.foursquareappdemo.models.Venue;

public class PlaceListAdapter extends ArrayAdapter<Venue> {
    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private final List<Venue> venueList;

    public PlaceListAdapter(Context context, List<Venue> objects) {
        super(context, 0, objects);
        this.context = context;
        this.venueList = objects;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return venueList.size();
    }

    @Override
    public Venue getItem(int position) {
        return venueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return venueList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_place, null);

            holder = new ViewHolder();

            holder.place = convertView.findViewById(R.id.tv_place);
            holder.descripton = convertView.findViewById(R.id.tv_description);
            holder.city = convertView.findViewById(R.id.tv_city);
            holder.country = convertView.findViewById(R.id.tv_country);
            convertView.setTag(holder);

        } else {
            //Get viewholder we already created
            holder = (ViewHolder) convertView.getTag();
        }

        Venue venue = venueList.get(position);
        if (venue != null) {
            holder.place.setText(venue.getName());
            holder.descripton.setText(venue.location.getAddress());
            holder.city.setText(venue.location.getCity());
            holder.country.setText(venue.location.getCountry());

        }
        return convertView;
    }

    private static class ViewHolder {

        TextView place;
        TextView descripton;
        TextView city;
        TextView country;

    }
}
