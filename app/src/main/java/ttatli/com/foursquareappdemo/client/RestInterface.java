package ttatli.com.foursquareappdemo.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ttatli.com.foursquareappdemo.models.Result;

public interface RestInterface {
   @GET("venues/search")
   Call<Result> getPlaces(@Query("ll")String location,@Query("client_id") String clientId,@Query("client_secret") String secretId,@Query("v") String v);

   @GET("venues/search")
   Call<Result> getNearPlaces(@Query("query") String query,@Query("ll")String location,@Query("client_id") String clientId,@Query("client_secret") String secretId,@Query("v") String v);
}
