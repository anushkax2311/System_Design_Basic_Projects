package models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static int nextRestaurantId = 0;
    private int restaurantId;
    private String name;
    private String Location;
    private List<MenuItem> menu = new ArrayList<>();

    public Restaurant(String name, String Location) {
        this.name = name;
        this.Location = Location;
        this.restaurantId = ++nextRestaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String loc) {
        Location = loc;
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

}
