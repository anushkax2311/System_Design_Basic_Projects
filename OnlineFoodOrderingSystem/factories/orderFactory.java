package factories;

import java.util.List;

import models.Cart;
import models.MenuItem;
import models.Order;
import models.Restaurant;
import models.User;
import strategies.PaymentStrategy;

public interface orderFactory {
    Order createOrder(User user, Cart cart, Restaurant restaurant, List<MenuItem> menuItems,
            PaymentStrategy paymentStrategy, double totalCost, String orderType);
}
