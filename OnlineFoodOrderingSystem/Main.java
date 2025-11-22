import models.Order;
import models.Restaurant;
import models.User;
import strategies.UpiPaymentStrategy;

public class Main {
    public static void main(String[] args){
        TomatoApp tomatoApp = new TomatoApp();

        User user = new User(101, "Aditya", "Delhi");
        System.out.println("User: " +user.getName() +" is active.");

        java.util.List<Restaurant> restaurantList = tomatoApp.searchRestaurants("Delhi");

        if (restaurantList.isEmpty()) {
            System.out.println("No Restaurants Found!");
            return;
        }

        System.out.println("Found Restaurants: ");
        for(Restaurant restaurant : restaurantList){
            System.out.println(" - "+restaurant.getName());
        }
        tomatoApp.selectRestaurant(user, restaurantList.get(0));
        System.out.println("Selected Restaurant: "+restaurantList.get(0).getName());

        tomatoApp.addToCart(user,"P1");
        tomatoApp.addToCart(user, "P2");

        tomatoApp.printUserCart(user);

        Order order = tomatoApp.checkOutNow(user, "Delivery", new UpiPaymentStrategy("12345678"));
        tomatoApp.payForOrder(user, order);
    }
}
