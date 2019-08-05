package ttatli.com.foursquareappdemo.presenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ttatli.com.foursquareappdemo.client.ApiClient;
import ttatli.com.foursquareappdemo.client.RestInterface;
import ttatli.com.foursquareappdemo.models.Location;
import ttatli.com.foursquareappdemo.models.Responce;
import ttatli.com.foursquareappdemo.models.Result;

public class MainPresenter {
    public View view;
    private Location location;
    public static RestInterface restInterface;

    public MainPresenter(View view) {
        this.view = view;
        this.location=new Location();

    }

    public void updatePlace(String place) {

        this.view.updatePlace(place);
        this.location.setAddress(place);
    }

    public void updateCity(String city) {
        this.view.updateCity(city);
        this.location.setCity(city);

    }

    public void getPlaces(double lat, double lng, String clientId, String secretId, String versionId) {
        restInterface = ApiClient.getRetrofit().create(RestInterface.class);
        String location=lat+","+lng;
        Call<Result> call = restInterface.getPlaces(location,clientId,secretId,versionId);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //action.onNotified(Enums.GetPlaces,response.body().getResults());


                view.onNextIntent(response.body().response);

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

    }

public void getNearPlaces(String near,double lat, double lng, String clientId, String secretId, String versionId){
    restInterface = ApiClient.getRetrofit().create(RestInterface.class);
    String location=lat+","+lng;
    Call<Result> call = restInterface.getNearPlaces(near,location,clientId,secretId,versionId);
    call.enqueue(new Callback<Result>() {
        @Override
        public void onResponse(Call<Result> call, Response<Result> response) {
            if(response != null && response.body()!= null){
                view.onNextIntent(response.body().response);
            }
        }

        @Override
        public void onFailure(Call<Result> call, Throwable t) {

        }
    });
}
    public interface View {
        void onNextIntent(Responce responce);

        void updatePlace(String place);

        void updateCity(String city);

    }
}
