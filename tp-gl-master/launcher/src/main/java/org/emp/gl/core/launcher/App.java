package org.emp.gl.core.launcher;

import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.HorlogeGUI;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;
import java.util.Random;

public class App {
    public static void main(String[] args) throws InterruptedException {
        TimerService timer = new DummyTimeServiceImpl();

        

       
        Horloge h1 = new Horloge("Horloge-1");
        h1.setTimerService(timer);

        Horloge h2 = new Horloge("Horloge-2");
        h2.setTimerService(timer);

        Horloge h3 = new Horloge("Horloge-3");
        h3.setTimerService(timer);

       
       
        CompteARebours compte1 = new CompteARebours(5, timer);

       
        Thread.sleep(7000);

        
        
        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            int valeur = random.nextInt(11) + 10; // 10 à 20
            new CompteARebours(valeur, timer);
        }

       
        new HorlogeGUI(timer);

       
        Thread.sleep(30000);

       
        System.exit(0);
    }
}