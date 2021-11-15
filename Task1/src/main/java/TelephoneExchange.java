import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;
import static utils.Constants.*;

public class TelephoneExchange {

    private CallCenter callCenter;

    private final List<String> names = Arrays.asList("Петя", "Коля", "Паша", "Наташа", "Вера", "Миша", "Дима",
            "Вася", "Даша", "Варвара", "Елизавета Петровна", "муж Елизаветы Петровны", "Дуся", "Витя", "Сережа");

    public TelephoneExchange(CallCenter callCenter) {
        this.callCenter = callCenter;
    }

    public void generateCall() {
        LocalDateTime cycleTime = java.time.LocalDateTime.now().plusSeconds(TE_CYCLE_PERIOD_SECS);
        while (java.time.LocalDateTime.now().isBefore(cycleTime)) {
            try {
                callCenter.addCustomer(names.get(new Random().nextInt(names.size())) + " # " + new Random().nextInt());
                sleep(TE_WAITING_TIME_MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
