package jadro;

import java.util.ArrayList;
import java.util.Observable;
import java.util.PriorityQueue;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jožko
 */
public abstract class UdalostnaSimulacia extends MonteCarlo {    
    protected PriorityQueue<Udalost> zoznamUdalosti;
    protected double simulacnyCas;
    protected final double maximalnySimulacnyCas;
    protected ArrayList<IObserver> zoznamObserverov;
    protected boolean pauza;
    
    public UdalostnaSimulacia(double paMaximalnySimulacnyCas, int paPocetReplikacii) {
        super(paPocetReplikacii);
        this.zoznamUdalosti = new PriorityQueue<>();
        this.simulacnyCas = 0;
        this.maximalnySimulacnyCas = paMaximalnySimulacnyCas;
        this.zoznamObserverov = new ArrayList<>();
    }
    

    @Override
    public void vykonajReplikaciu() {
        while (!this.zoznamUdalosti.isEmpty()) {
            if (this.pauza == false) {
                Udalost aktualneSpracovavana = this.zoznamUdalosti.poll();
                this.simulacnyCas = aktualneSpracovavana.dajSimulacnyCas();
                aktualneSpracovavana.naplanujDalsiuUdalost();
            }
            this.notifikujObserverov();
        }
    }
    
    // && this.simulacnyCas < this.maximalnySimulacnyCas
    
    public double dajSimulacnyCas() {
        return this.simulacnyCas;
    }
    
    public void pridajNovuUdalost(Udalost paUdalost) {
        this.zoznamUdalosti.add(paUdalost);
    }
    
    public void pridajObservera(IObserver ob) {
        this.zoznamObserverov.add(ob);
    }
    
    public void odstranObservera(IObserver ob) {
        this.zoznamObserverov.remove(ob);
    }
    
    public void notifikujObserverov() {
        for (IObserver akt : this.zoznamObserverov) {
            akt.update(this);
        }
    }
    
    public void zmenStavBehuSimulacie(boolean stav) {
        this.pauza = stav;
    }
    
    public void zmazCelyZoznamUdalosti() {
        this.zoznamUdalosti.clear();
    }
}
