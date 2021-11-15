import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        final CallCenter callCenter = new CallCenter();
        final TelephoneExchange telephoneExchange = new TelephoneExchange(callCenter);

        final ExecutorService operatorPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        operatorPool.submit(callCenter::pickUpTheCall);
        operatorPool.submit(callCenter::pickUpTheCall);
        operatorPool.submit(callCenter::pickUpTheCall);

        new Thread(null, telephoneExchange::generateCall, "Телефонная станция").start();

        operatorPool.shutdown();
    }

}
