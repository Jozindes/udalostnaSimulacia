/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zamestnanci;

/**
 *
 * @author Jožko
 */
public class Zamestnanec implements Comparable<Zamestnanec> {
    private final int id;
    private double zaciatokObsluhy;
    private double koniecObsluhy;
    private double casCelkovejObsluhy;
    private boolean pracuje;
    private double casCelkovejObsluhyDo17;
    
    public Zamestnanec(int paId) {
        this.id = paId;
    }
    
    public void zapisZaciatokObsluhy(double paZaciatokObsluhy) {
        this.zaciatokObsluhy = paZaciatokObsluhy;
    }
    
    public void zapisKoniecObsluhy(double paKoniecObsluhy) {
        this.koniecObsluhy = paKoniecObsluhy;
        
        this.casCelkovejObsluhy = this.casCelkovejObsluhy + (this.koniecObsluhy - this.zaciatokObsluhy);
        if (paKoniecObsluhy < 28800) {
            this.casCelkovejObsluhyDo17 = this.casCelkovejObsluhyDo17 + (this.koniecObsluhy - this.zaciatokObsluhy);
        }
        
        this.zaciatokObsluhy = 0;
        this.koniecObsluhy = 0;               
    }
    
    public double dajZaciatokObsluhy() {
        return this.zaciatokObsluhy;
    }
    
    public double dajKoniecObsluhy() {
        return this.koniecObsluhy;
    }
        
    public double dajCasCelkovejObsluhy() {
        return this.casCelkovejObsluhy;
    }
    
    public double vypocitajVytazenieDo17() {       
        return this.casCelkovejObsluhyDo17 / 28800 * 100;
    }
    
    public double vypocitajVytazenie(double simulacnyCas) {
        return this.casCelkovejObsluhy / simulacnyCas * 100;
    }
    
    public int dajID() {
        return this.id;
    }
    
    public void nastavPracovanie(boolean stav) {
        this.pracuje = stav;
    }
    
    public boolean dajStavPracovania() {
        return this.pracuje;
    }

    @Override
    public int compareTo(Zamestnanec iny) {
        if(this.dajID() > iny.dajID()) {
            return 1;
        } else if (this.dajID() < iny.dajID()) {
            return -1;
        } else {
            return 0;
        }
    }   
}
