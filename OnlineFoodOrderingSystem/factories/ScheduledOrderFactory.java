package factories;

import java.util.List;

import models.Cart;
import models.DeliveryOrder;
import models.MenuItem;
import models.Order;
import models.PickupOrder;
import models.Restaurant;
import models.User;
import strategies.PaymentStrategy;

public class ScheduledOrderFactory implements orderFactory {
 private String scheduleTime;
 
 
 public ScheduledOrderFactory(String scheduleTime){
 this.scheduleTime = scheduleTime;
 }


 @Override
 public Order createOrder(User user, Cart cart,Restaurant  restaurant, List<MenuItem> menuItems,PaymentStrategy paymentStrategy, double totalCost , String orderType){
    Order order = null;
    if (orderType.equals("Delivery")) {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setUserAddress(user.getAddress());
        order = deliveryOrder;
    }else{
        PickupOrder pickupOrder = new PickupOrder();
        pickupOrder.setRestaurantAddress(restaurant.getLocation());
    }
order.setUser(user);
        order.setRestaurant(restaurant);
        order.setItems(menuItems);
        order.setPaymentStrategy(paymentStrategy);
        order.setscheduled(scheduleTime);
        order.setTotal(totalCost);
        return order;
}


}
