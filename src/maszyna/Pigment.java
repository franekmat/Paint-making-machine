package maszyna;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;
import java.util.UUID;
import javax.swing.JOptionPane;

public class Pigment {
    
    private final PropertyChangeSupport changeSupport = 
            new PropertyChangeSupport(this);
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    } 
    
    private String nazwa;
    private String zmianaJakosci;
    private String zmianaToksycznosci;
    private String kolorPoczatkowy;
    private String kolorKoncowy;
    private Character[] znaki = {'x', '+', '-'};
    
    public Pigment () {
        nazwa = UUID.randomUUID().toString().substring(0, 13);
        nazwa = nazwa.replaceAll("-", "q");
        Random rand = new Random();
        zmianaJakosci = String.valueOf(znaki[Math.abs(rand.nextInt()) % 3]) 
                + String.valueOf(100 * Math.round(
                        rand.nextDouble() * 100000d) / 100000d);
        zmianaToksycznosci = String.valueOf(znaki[Math.abs(rand.nextInt()) % 3]) 
                + String.valueOf(100 * Math.round(
                        rand.nextDouble() * 100000d) / 100000d);
        kolorPoczatkowy = UUID.randomUUID().toString().substring(0, 13);
        kolorKoncowy = UUID.randomUUID().toString().substring(0, 13);
    }
    
    public Pigment (String nazwa, String kolorPoczatkowy, String kolorKoncowy, 
            String zmianaJakosci, String zmianaToksycznosci) {
        this.nazwa = nazwa;
        this.zmianaJakosci = zmianaJakosci;
        this.zmianaToksycznosci = zmianaToksycznosci;
        this.kolorPoczatkowy = kolorPoczatkowy;
        this.kolorKoncowy = kolorKoncowy;
    }
    
    public String toString () {
        return nazwa;
    }
    
    public String getNazwa () {
        return nazwa;
    }
    
    public String getZmianaJakosci () {
        return zmianaJakosci;
    }
    
    public String getJakosc () {
        return zmianaJakosci.substring(1);
    }
    
    public Character getZnakZmianyJakosci () {
        return zmianaJakosci.charAt(0);
    }
    
    public String getZmianaToksycznosci () {
        return zmianaToksycznosci;
    }
    
    public String getToksycznosc () {
        return zmianaToksycznosci.substring(1);
    }
    
    public Character getZnakZmianyToksycznosci () {
        return zmianaToksycznosci.charAt(0);
    }
    
    public String getKolorPoczatkowy () {
        return kolorPoczatkowy;
    }
    
    public String getKolorKoncowy () {
        return kolorKoncowy;
    }
    
    public void setNazwa (String nazwa) {
        String old = this.nazwa;
        this.nazwa = nazwa;
        changeSupport.firePropertyChange("nazwa", old, nazwa);
    }
    
    public void setZmianaJakosci (String zmianaJakosci) {
        String old = this.zmianaJakosci;
        this.zmianaJakosci = zmianaJakosci;
        changeSupport.firePropertyChange("zmianaJakosci", old, zmianaJakosci);
    }
    
    public void setZmianaToksycznosci (String zmianaToksycznosci) {
        String old = this.zmianaToksycznosci;
        this.zmianaToksycznosci = zmianaToksycznosci;
        changeSupport.firePropertyChange("zmianaToksycznosci", old,
                zmianaToksycznosci);
    }
    
    public void setKolorPoczatkowy (String kolorPoczatkowy) {
        String old = this.kolorPoczatkowy;
        this.kolorPoczatkowy = kolorPoczatkowy;
        changeSupport.firePropertyChange("kolorPoczatkowy", old,
                kolorPoczatkowy);
    }
    
    public void setKolorKoncowy (String kolorKoncowy) {
        String old = this.kolorKoncowy;
        this.kolorKoncowy = kolorKoncowy;
        changeSupport.firePropertyChange("kolorKoncowy", old, kolorKoncowy);
    }
    
    public void wypiszPigment () {
        System.out.println("* nazwa: " + this.toString());
        System.out.println("* kolor początkowy: " + this.getKolorPoczatkowy());
        System.out.println("* kolor końcowy: " + this.getKolorKoncowy());
        System.out.println("* zmiana toksyczności: "
                + this.getZnakZmianyToksycznosci() 
                + this.getToksycznosc());
        System.out.println("* zmiana jakości: " + this.getZnakZmianyJakosci() 
                + this.getJakosc());
    }
    
    public Farba uzyjPigmentu (Farba farba) {
        if (farba.toString().equals(this.getKolorPoczatkowy())) {
            Double zmianaToksycznosci = Double.valueOf(this.getToksycznosc());
            Double zmianaJakosci = Double.valueOf(this.getJakosc());
            
            System.out.println("\n***** Dodawany pigment: *****");
            wypiszPigment();

            String nowyKolor = this.getKolorKoncowy();
            
            Double nowaToksycznosc = farba.getToksycznosc();
            Double nowaJakosc = farba.getJakosc();
            
            if (this.getZnakZmianyToksycznosci().equals('+'))
                nowaToksycznosc += zmianaToksycznosci;
            else if (this.getZnakZmianyToksycznosci().equals('-'))
                nowaToksycznosc -= zmianaToksycznosci;
            else
                nowaToksycznosc *= zmianaToksycznosci;
            
            if (this.getZnakZmianyJakosci().equals('+'))
                nowaJakosc += zmianaJakosci;
            else if (this.getZnakZmianyJakosci().equals('-'))
                nowaJakosc -= zmianaJakosci;
            else
                nowaJakosc *= zmianaJakosci;
            

            System.out.println("\n***** Otrzymana farba: *****");
            System.out.println("Kolor: " + nowyKolor);
            System.out.println("Toksyczność: "
                    + String.valueOf(nowaToksycznosc));
            System.out.println("Jakość: " + String.valueOf(nowaJakosc));

            return new Farba(nowyKolor, String.valueOf(nowaJakosc),
                    String.valueOf(nowaToksycznosc));
        }
        JOptionPane.showMessageDialog(null, "Konfiguracja maszyny nie "
                + "uwzględnia zmieszania koloru z danym pigmentem.");
        return farba;
    }
}
