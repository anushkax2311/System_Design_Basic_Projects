package services;

import java.util.List;

import models.MenuItem;
import models.*;

public class NotificationService {
    public static void  notify(Order order){
        System.out.println("\nNotification : New "+order.getType()+"order placed!");
        System.out.println("---------------------------");
        System.out.println("Order ID: "+order.getOrderId());
        System.out.println("Customer: "+order.getUser().getName());
        System.out.println("Restaurant "+order.getRestaurant().getName());
        System.out.println("Items Ordered: ");

        List<MenuItem> items = order.getItems();
        for(MenuItem item : items){
            System.out.println("  -"+item.getName()+"(Rs."+item.getPrice()+")");

        }

        System.out.println("Total:Rs. "+order.getTotal());
        System.out.println("Scheduled For: "+order.getscheduled());
        System.out.println("Payment: Done");
        System.out.println("----------------------------------");



    }
}
