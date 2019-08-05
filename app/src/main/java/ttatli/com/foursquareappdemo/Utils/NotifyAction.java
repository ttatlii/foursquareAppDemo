package ttatli.com.foursquareappdemo.Utils;

public interface NotifyAction<T> {
    void onNotified(T key, T object);
}
