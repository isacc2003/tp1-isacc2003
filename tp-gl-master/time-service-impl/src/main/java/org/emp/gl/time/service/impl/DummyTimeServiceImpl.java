package org.emp.gl.time.service.impl;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;


public class DummyTimeServiceImpl implements TimerService {

    private int dixiemeDeSeconde;
    private int secondes;
    private int minutes;
    private int heures;

    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public DummyTimeServiceImpl() {
        setTimeValues();

       
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timeChanged();
            }
        };
        timer.scheduleAtFixedRate(task, 100, 100);
    }

   
    private void setTimeValues() {
        LocalTime localTime = LocalTime.now();

        setSecondes(localTime.getSecond());
        setMinutes(localTime.getMinute());
        setHeures(localTime.getHour());
        setDixiemeDeSeconde(localTime.getNano() / 100_000_000);
    }

   
    private void timeChanged() {
        setTimeValues();
    }

    
    @Override
    public void addTimeChangeListener(PropertyChangeListener pl) {
        pcs.addPropertyChangeListener(pl);
    }

    @Override
    public void removeTimeChangeListener(PropertyChangeListener pl) {
        pcs.removePropertyChangeListener(pl);
    }

    
    public void setDixiemeDeSeconde(int newDixiemeDeSeconde) {
        if (dixiemeDeSeconde == newDixiemeDeSeconde)
            return;

        int oldValue = dixiemeDeSeconde;
        dixiemeDeSeconde = newDixiemeDeSeconde;
        pcs.firePropertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP, oldValue, newDixiemeDeSeconde);
    }

    public void setSecondes(int newSecondes) {
        if (secondes == newSecondes)
            return;

        int oldValue = secondes;
        secondes = newSecondes;
        pcs.firePropertyChange(TimerChangeListener.SECONDE_PROP, oldValue, newSecondes);
    }

    public void setMinutes(int newMinutes) {
        if (minutes == newMinutes)
            return;

        int oldValue = minutes;
        minutes = newMinutes;
        pcs.firePropertyChange(TimerChangeListener.MINUTE_PROP, oldValue, newMinutes);
    }

    public void setHeures(int newHeures) {
        if (heures == newHeures)
            return;

        int oldValue = heures;
        heures = newHeures;
        pcs.firePropertyChange(TimerChangeListener.HEURE_PROP, oldValue, newHeures);
    }

    
    @Override
    public int getDixiemeDeSeconde() {
        return dixiemeDeSeconde;
    }

    @Override
    public int getHeures() {
        return heures;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }
}
