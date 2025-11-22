package strategies;

public class UpiPaymentStrategy implements PaymentStrategy{
    
    private String mobile;

    public UpiPaymentStrategy(String mob){
        this.mobile=mob;
    }
    public void pay(double amount){
        System.out.println("Paid Rs."+amount +"using UPI ("+mobile+")");
    }

}
