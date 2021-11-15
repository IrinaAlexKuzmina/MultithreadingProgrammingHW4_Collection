import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.Thread.sleep;
import static utils.Constants.*;
import static utils.Logger.log;

public class CallCenter {
    private Queue<String> waitingCustomers;

    public CallCenter() {
        // нужна была неблокирующая простая очередь,ConcurrentLinkedQueue показалось самым лучшим вариантов,
        // учитывая тот факт, что колл-центр все-таки должен более-менее справляться с нагрузкой и
        // очередь не должна иметь огромные размеры
        waitingCustomers = new ConcurrentLinkedQueue<>();
    }

    public void addCustomer(String name) {
        waitingCustomers.add(name);
        log("В очередь ожидания добавился " + name);
    }

    public void pickUpTheCall() {
        int count = 0;
        String str;
        while (!waitingCustomers.isEmpty() || count <= WAITING_NUMBER)
            try {
                if ((str = waitingCustomers.poll()) != null) {
                    log("Взял звонок от " + str + "!");
                    sleep(PROCESSING_TIME_MILLISECONDS);
                } else {
                    log("Ура! Нет ожидающих звонков");
                    sleep(WAITING_TIME_MILLISECONDS);
                    count++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
