package maszyna;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

public class Fasada {
    
    private HashMap<String, Boolean> nazwaFarby = 
            new HashMap<String, Boolean>();
    private HashMap<String, Boolean> nazwaPigmentu = 
            new HashMap<String, Boolean>();
    private String dozwoloneZnakiFarba = 
            "[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-"
            + "ęóąśżźćĘÓĄŚŻŹĆ]";
    private String dozwoloneZnakiPigment = 
            "[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890]";
    
    Pattern pZnakiFarba = Pattern.compile(dozwoloneZnakiFarba);
    Pattern pZnakiPigment = Pattern.compile(dozwoloneZnakiPigment);
    Pattern pDzialania = Pattern.compile("[^+-x]");
    Pattern pLiczba = Pattern.compile("[^0123456789.]");
    
    private Boolean czyNazwaFarbyIstnieje (String nazwa) {
        return nazwaFarby.get(nazwa) != null;
    }
    
    private void dodajNazweFarby (String nazwa) {
        nazwaFarby.put(nazwa, true);
    }
    
    private Boolean czyNazwaPigmentuIstnieje (String nazwa) {
        return nazwaPigmentu.get(nazwa) != null;
    }
    
    private void dodajNazwePigmentu (String nazwa) {
        nazwaPigmentu.put(nazwa, true);
    }
    
    private Boolean czyPlikFarbyPoprawny (String nazwa, String jakosc, 
            String toksycznosc) {
        if (pZnakiFarba.matcher(nazwa).find())
            return false;
        if (!Character.isLetter(nazwa.charAt(0)))
            return false;
        if (pLiczba.matcher(jakosc).find())
            return false;
        if (pLiczba.matcher(toksycznosc).find())
            return false;
        if (Integer.valueOf(jakosc) < 0 || Integer.valueOf(jakosc) > 100)
            return false;
        if (Integer.valueOf(toksycznosc) < 0 ||
                Integer.valueOf(toksycznosc) > 100)
            return false;
        
        return true;
    }
    
    private Boolean czyPlikPigmentyPoprawny (String nazwa, String zmianaJakosci, 
            String zmianaToksycznosci) {
        if (pZnakiPigment.matcher(nazwa).find())
            return false;
        if (pLiczba.matcher(zmianaJakosci.substring(1)).find())
            return false;
        if (pLiczba.matcher(zmianaToksycznosci.substring(1)).find())
            return false;
        if (Double.valueOf(zmianaJakosci.substring(1)) <= 0)
            return false;
        if (Double.valueOf(zmianaToksycznosci.substring(1)) <= 0)
            return false;
        if (pDzialania.matcher(String.valueOf(zmianaJakosci.charAt(0))).find())
            return false;
        if (pDzialania.matcher(String.valueOf(
                zmianaToksycznosci.charAt(0))).find())
            return false;
        
        return true;
    }
    
    public ObservableList<Farba> czytajFarby (File plik) {
        ObservableList<Farba> farby = 
            ObservableCollections.observableList(new ArrayList<Farba>());
        try {
                List<String> l = Files.readAllLines(plik.toPath(),
                        Charset.defaultCharset());
                for (String line : l) {
                    String[] parts = line.split(" ");
                    if (czyNazwaFarbyIstnieje(parts[0])) {
                        System.err.println("Farby nie mają unikatowych nazw.");
                        JOptionPane.showMessageDialog(null, "Farby nie mają "
                                + "unikatowych nazw.");
                        return null;
                    }
                    if (!czyPlikFarbyPoprawny(parts[0], parts[1], parts[2])) {
                        System.err.println("Niepoprawny plik z definicjami "
                                + "farb.");
                        JOptionPane.showMessageDialog(null, "Niepoprawny plik z"
                                + " definicjami farb.");
                        return null;
                    }
                    Farba nowaFarba = new Farba(parts[0], parts[1], parts[2]);
                    dodajNazweFarby(parts[0]);
                    farby.add(nowaFarba);
                }
                return farby;
            } catch (IOException e1) {
            System.err.println("Error while reading file.");
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Nie można odczytać pliku "
                    + plik);
            return null;
        }
    }

    public ObservableList<Pigment> czytajPigmenty (File plik) {
        ObservableList<Pigment> pigmenty = 
            ObservableCollections.observableList(new ArrayList<Pigment>());
        try {
                List<String> l = Files.readAllLines(plik.toPath(),
                        Charset.defaultCharset());
                for (String line : l) {
                    String[] parts = line.split(" ");
                    if (czyNazwaPigmentuIstnieje(parts[0])) {
                        System.err.println("Pigmenty nie mają unikatowych "
                                + "nazw.");
                        JOptionPane.showMessageDialog(null, "Pigmenty nie mają "
                                + "unikatowych nazw.");
                        return null;
                    }
                    if (!czyPlikPigmentyPoprawny(parts[0], parts[3], parts[4])){
                        System.err.println("Niepoprawny plik z definicjami "
                                + "pigmentów.");
                        JOptionPane.showMessageDialog(null, "Niepoprawny plik z"
                                + " definicjami pigmentów.");
                        return null;
                    }
                    Pigment nowyPigment =  new Pigment(parts[0], parts[1],
                            parts[2], parts[3], parts[4]);
                    dodajNazwePigmentu(parts[0]);
                    pigmenty.add(nowyPigment);
                }
                return pigmenty;
            } catch (IOException e1) {
            System.err.println("Error while reading file.");
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Nie można odczytać pliku " 
                    + plik);
            return null;
        }
    }
}
