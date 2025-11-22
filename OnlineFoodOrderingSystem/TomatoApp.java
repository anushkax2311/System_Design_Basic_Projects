import java.util.List;

import factories.NowOrderFactory;
import factories.ScheduledOrderFactory;
import factories.orderFactory;
import managers.OrderManager;
import managers.RestaurantManager;
import models.Cart;
import models.MenuItem;
import models.Order;
import models.Restaurant;
import models.User;
import services.NotificationService;
import strategies.PaymentStrategy;

public class TomatoApp {
    public TomatoApp(){
        initializeRestaurants();
    }
    public void initializeRestaurants(){
        Restaurant restaurant1= new Restaurant("Bikaner", "Delhi");
        restaurant1.addMenuItem(new MenuItem("P1", "Chole Bhature" , 120));
        restaurant1.addMenuItem(new MenuItem("P2", "Samosa" , 15));


        Restaurant restaurant2= new Restaurant("Haldiram", "Kolkata");
        restaurant2.addMenuItem(new MenuItem("P1", "Raj Kachori" , 80));
        restaurant2.addMenuItem(new MenuItem("P2", "Pav Bhaji" , 100));
        restaurant2.addMenuItem(new MenuItem("P3", "Dhokla" , 50));

        Restaurant restaurant3= new Restaurant("Saravana", "Chennai");
        restaurant3.addMenuItem(new MenuItem("P1", "Masala Dosa" , 120));
        restaurant3.addMenuItem(new MenuItem("P2", "Idli Vada" , 60));
        restaurant3.addMenuItem(new MenuItem("P2", "Filter Coffee" , 30));
  
        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        restaurantManager.addRestaurant(restaurant1);
        restaurantManager.addRestaurant(restaurant2);
        restaurantManager.addRestaurant(restaurant1);

    }

    public List<Restaurant> searchRestaurants(String location){
        return RestaurantManager.getInstance().searchByLocation(location);
    }

    public void selectRestaurant(User user , Restaurant restaurant){
        Cart cart = user.getCart();
        cart.setRestaurant(restaurant);
    }

    public void addToCart(User user , String itemCode){
        Restaurant restaurant = user.getCart().getRestaurant();
        if (restaurant==null) {
                System.out.println("Please select a restaurant first.");
                return;            
        }
        for(MenuItem item : restaurant.getMenu()){
            if (item.getCode().equals(itemCode)) {
                user.getCart().addItem(item);
                break;
                
            }
        }
    } 

    public Order checkOutNow(User user , String orderType , PaymentStrategy paymentStrategy){

        return checkOut(user, orderType,  paymentStrategy , new NowOrderFactory());
    }

    public Order checkOutNow(User user , String orderType , PaymentStrategy paymentStrategy,String scheduleTime){

        return checkOut(user,orderType,paymentStrategy,new ScheduledOrderFactory(scheduleTime));
    }


    public Order checkOut(User user , String orderType , PaymentStrategy paymentStrategy,orderFactory orderFactory){
if (user.getCart().isEmpty()) return null; 

        Cart usercart = user.getCart();
        Restaurant orderedRestaurant = usercart.getRestaurant();
        List<MenuItem> itemsordered = usercart.getItems();
        double totalCost = usercart.getTotalCost();

        Order order = orderFactory.createOrder(user, usercart, orderedRestaurant, itemsordered, paymentStrategy, totalCost, orderType);
        OrderManager.getInstance().addOrder(order);
        return order;



    }
    public void payForOrder(User user , Order order){
        boolean isPaymentSuccess = order.processPayment();

        if (isPaymentSuccess) {
            NotificationService.notify(order);
            user.getCart().clear();
        }
    }

    public void printUserCart(User user){
        System.out.println("Items in Cart: ");
        System.out.println("--------------------------------");
        for(MenuItem item:user.getCart().getItems()){
            System.out.println(item.getCode()+" : "+item.getName());
        }
        System.out.println("----------------------------");
        System.out.println("Grand Total : RS." + user.getCart().getTotalCost());
    }

}
