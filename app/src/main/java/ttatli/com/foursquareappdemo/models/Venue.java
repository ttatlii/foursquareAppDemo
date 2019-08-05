package ttatli.com.foursquareappdemo.models;

import java.io.Serializable;

public class Venue  implements Serializable {
    private String name;
   public Location location;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "name='" + name + '\'' +
                '}';

    }

}
