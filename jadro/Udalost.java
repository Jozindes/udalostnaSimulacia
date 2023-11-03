package jadro;

import simulacia.Zakaznik;
import zamestnanci.Zamestnanec;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jožko
 */
public abstract class Udalost implements Comparable<Udalost> { 
    protected double simulacnyCas;
    protected UdalostnaSimulacia mojaSimulacia;
    protected Zakaznik zakaznik;
    protected Zamestnanec zamestnanec;
    
    public Udalost(double paSimulacnyCas, UdalostnaSimulacia paSimulacia, Zamestnanec paZamestnanec, Zakaznik paZakaznik) {
        this.simulacnyCas = paSimulacnyCas;
        this.mojaSimulacia = paSimulacia;
        this.zamestnanec = paZamestnanec;
        this.zakaznik = paZakaznik;
    }
    
    public double dajSimulacnyCas() {
        return this.simulacnyCas;
    }
    
    public UdalostnaSimulacia dajMojuSimulaciu() {
        return this.mojaSimulacia;
    }
    
    public Zamestnanec dajObsluhujucehoZamestnanca() {
        return this.zamestnanec;
    }
    
    public Zakaznik dajObsluhovanehoZakaznika() {
        return this.zakaznik;
    }
    
    public abstract void naplanujDalsiuUdalost();
    
    @Override
    public int compareTo(Udalost inaUdalost) {
        if(this.dajSimulacnyCas() > inaUdalost.dajSimulacnyCas()) {
            return 1;
        } else if (this.dajSimulacnyCas() < inaUdalost.dajSimulacnyCas()) {
            return -1;
        } else {
            return 0;
        }
    }
}

