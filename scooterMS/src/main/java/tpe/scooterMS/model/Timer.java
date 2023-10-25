package tpe.scooterMS.model;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@EnableScheduling
public class Timer {

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    @PostConstruct
    public void startTimer() {
        int durationInMinutes = 15;

        executor.schedule(() -> {
            System.out.println("15 minutes have elapsed. Stopping the timer."); //Acá habría que dar aviso al viaje.
            executor.shutdown();
        }, durationInMinutes, TimeUnit.MINUTES);
    }

    @PreDestroy
    public void stopTimer() {
        if (!executor.isShutdown()) {
            executor.shutdownNow();
        }
    }
}

