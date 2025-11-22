import java.util.*;

class PaymentRequest {
    public String sender;
    public String reciever;
    public double amount;
    public String currency;

    public PaymentRequest(String sender, String reciever, double amt, String curr) {
        this.sender = sender;
        this.reciever = reciever;
        this.amount = amt;
        this.currency = curr;
    }
}

interface BankingSystem {
    boolean processPayment(double amount);
}

class PaytmBankingSystem implements BankingSystem {
    private Random rand = new Random();

    public PaytmBankingSystem() {

    }

    @Override
    public boolean processPayment(double amount) {
        int r = rand.nextInt(100);
        return r < 80;
    }
}

class RazorPayBankingSystem implements BankingSystem {

    private Random rand = new Random();

    public RazorPayBankingSystem() {

    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("[BankingSystem-RazorPay] Processing payment of " + amount + "...");

        int r = rand.nextInt(100);
        return r < 90;
    }
}

abstract class PaymentGateway {
    protected BankingSystem bankingSystem;

    protected abstract boolean validatePayment(PaymentRequest request);

    protected abstract boolean initiatePayment(PaymentRequest request);

    protected abstract boolean confirmPayment(PaymentRequest request);

    public PaymentGateway() {
        this.bankingSystem = null;
    }

    public boolean processPayment(PaymentRequest request) {
        if (!validatePayment(request)) {
            System.out.println("[PaymentGateway] validation failed for " + request.sender + ".");
            return false;
        }
        if (!initiatePayment(request)) {
            System.out.println("[PaymentGateway] Initiation failed for " + request.sender + ".");
            return false;
        }
        if (!confirmPayment(request)) {
            System.out.println("[PaymentGateway] Confirmation failed for " + request.sender + ".");
            return false;
        }
        return true;
    }

}

class PaytmGateway extends PaymentGateway {
    public PaytmGateway() {
        this.bankingSystem = new PaytmBankingSystem();
    }

    @Override
    protected boolean validatePayment(PaymentRequest request) {
        System.out.println("[Paytm] validating payment for " + request.sender + ".");
        if (request.amount <= 0 || !"INR".equals(request.currency)) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean initiatePayment(PaymentRequest request) {
        System.out.println("[Paytm] Initiating payment of " + request.amount
                + " " + request.currency + " for " + request.sender + ".");
        return bankingSystem.processPayment(request.amount);
    }

    @Override
    protected boolean confirmPayment(PaymentRequest request) {
        System.out.println("[Paytm] confirming payment for " + request.sender + ".");
        return true;
    }
}

class RazorPayGateway extends PaymentGateway {
    public RazorPayGateway() {
        this.bankingSystem = new RazorPayBankingSystem();
    }

    @Override
    protected boolean validatePayment(PaymentRequest request) {
        System.out.println("[Razorpay] Validating payment for " + request.sender + ".");
        if (request.amount <= 0) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean initiatePayment(PaymentRequest request) {
        System.out.println("[Razorpay] Initiating payment of " + request.amount
                + " " + request.currency + " for " + request.sender + ".");
        return bankingSystem.processPayment(request.amount);
    }

    @Override
    protected boolean confirmPayment(PaymentRequest request) {
        System.out.println("[Razorpay] Confirming payment for " + request.sender + ".");
        return true;
    }
}

class PaymentGatewayProxy extends PaymentGateway {
    private PaymentGateway realGateway;
    private int retries;

    public PaymentGatewayProxy(PaymentGateway gateway, int maxRetries) {
        this.realGateway = gateway;
        this.retries = maxRetries;
    }

    @Override
    public boolean processPayment(PaymentRequest request) {
        boolean result = false;
        for (int attempt = 0; attempt < retries; ++attempt) {
            if (attempt > 0) {
                System.out.println("[Proxy] Retrying payment (attempt " + (attempt + 1)
                        + ") for " + request.sender + ".");
            }
            result = realGateway.processPayment(request);
            if (result)
                break;}
            if (!result) {
                System.out.println("[Proxy] Payment failed after " + retries
                        + " attempts for " + request.sender + ".");
            }
                return result;
        }
        
    

    @Override
    protected boolean validatePayment(PaymentRequest request) {
        return realGateway.validatePayment(request);
    }

    @Override
    protected boolean initiatePayment(PaymentRequest request) {
        return realGateway.initiatePayment(request);
    }

    @Override
    protected boolean confirmPayment(PaymentRequest request) {
        return realGateway.confirmPayment(request);
    }
}

enum GatewayType{
    PAYTM,
    RAZORPAY
}

class GatewayFactory{
    private static final GatewayFactory instance = new GatewayFactory();

    private GatewayFactory(){

    }

    public static GatewayFactory getInstance(){
        return instance;
    }
    public PaymentGateway getGateway(GatewayType type){
        if (type==GatewayType.PAYTM) {
            PaymentGateway gateway = new PaytmGateway();
            return new PaymentGatewayProxy(gateway, 3);
        }else{
            PaymentGateway gateway = new RazorPayGateway();
            return new PaymentGatewayProxy(gateway,1);
        }
    }
}

class PaymentService{
    private static final PaymentService instance = new PaymentService();
    private PaymentGateway gateway;

    private PaymentService(){
        this.gateway = null;
    }

    public static PaymentService getInstance(){
        return instance;
    }

    public void setGateway(PaymentGateway g){
        this.gateway=g;
    }

    public boolean processPayment(PaymentRequest request){
        if (gateway==null) {
            System.out.println("[PaymentService] No payment gateway selected.");
            return false;
        }
        return gateway.processPayment(request);
    }
}

class PaymentController{
    private static final PaymentController instance = new PaymentController();
    private PaymentController(){}

    public static PaymentController getInstance(){
        return instance;
    }

    public boolean handlePayment(GatewayType type ,PaymentRequest request){
        PaymentGateway gateway = GatewayFactory.getInstance().getGateway(type);
        PaymentService.getInstance().setGateway(gateway);
        return PaymentService.getInstance().processPayment(request);
    }
}

public class PaymentGatewayApplication {

    public static void main(String[] args) {
        
        PaymentRequest req1 = new PaymentRequest("Aditya", "Anushka", 1000000.0, "INR");
        System.out.println("Processing via Paytm");
        boolean res1 =PaymentController.getInstance().handlePayment(GatewayType.PAYTM, req1);
        System.out.println("Result: "+( res1?"SUCCESS":"FAIL"));
        System.out.println("-----------------------");
        
   
        PaymentRequest req2 = new PaymentRequest("Shubham", "Aditya", 500.0, "USD");

        System.out.println("Processing via Razorpay");
        System.out.println("------------------------------");
        boolean res2 = PaymentController.getInstance().handlePayment(GatewayType.RAZORPAY, req2);
        System.out.println("Result: " + (res2 ? "SUCCESS" : "FAIL"));
        System.out.println("------------------------------");
    }
}