package maszyna;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;
import java.util.UUID;

public class Farba {
    
    private final PropertyChangeSupport propertyChangeSupport = 
            new PropertyChangeSupport(this);
 
    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
    private String nazwa;
    private Double jakosc;
    private Double toksycznosc;
    
    public Farba () {
        nazwa = UUID.randomUUID().toString().substring(0, 13);
        Random rand = new Random();
        jakosc = 100 * Math.round(rand.nextDouble() * 100000d) / 100000d;
        toksycznosc = 100 * Math.round(rand.nextDouble() * 100000d) / 100000d;
    }
    
    public Farba (String nazwa, String jakosc, String toksycznosc) {
        this.nazwa = nazwa;
        if (jakosc.charAt(0) != ' ')
            this.jakosc = Double.parseDouble(jakosc);
        else
            this.jakosc = 0d;
        if (toksycznosc.charAt(0) != ' ')
            this.toksycznosc = Double.parseDouble(toksycznosc);
        else
            this.toksycznosc = 0d;
    }
    
    public String toString () {
        return nazwa;
    }
    
    public String getNazwa () {
        return nazwa;
    }
    
    public Double getJakosc () {
        return jakosc;
    }
    
    public Double getToksycznosc () {
        return toksycznosc;
    }
    
    public void setNazwa (String nazwa) {
        String old = this.nazwa;
        this.nazwa = nazwa;
        propertyChangeSupport.firePropertyChange("nazwa", old, nazwa);
    }
    
    public void setJakosc (Double jakosc) {
        Double old = this.jakosc;
        this.jakosc = jakosc;
        propertyChangeSupport.firePropertyChange("jakosc", old, jakosc);
    }
    
    public void setToksycznosc (Double toksycznosc) {
        Double old = this.toksycznosc;
        this.toksycznosc = toksycznosc;
        propertyChangeSupport.firePropertyChange("toksycznosc", old,
                toksycznosc);
    }
    
    public void mieszaj () {
        System.out.println("\n***** Zaczynam mieszanie *****");
        System.out.println("Kolor farby: " + nazwa);
        System.out.println("Toksyczność: " + toksycznosc);
        System.out.println("Jakość: " + jakosc);
    }
}
