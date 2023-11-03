/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udalosti;

import jadro.Udalost;
import jadro.UdalostnaSimulacia;
import java.util.logging.Level;
import java.util.logging.Logger;
import simulacia.KozmetickySalon;
import simulacia.Zakaznik;
import zamestnanci.Zamestnanec;

/**
 *
 * @author Jožko
 */
public class Spojitost extends Udalost {
    private final double dalsiaUdalostZaCas = ((KozmetickySalon) this.mojaSimulacia).dajMedzeruSpojitostSimulacie();
    
    public Spojitost(double paSimulacnyCas, UdalostnaSimulacia paMojaSimulacia, Zamestnanec paKtoObsluhuje, Zakaznik paKohoObsluhuje) {
        super(paSimulacnyCas, paMojaSimulacia, paKtoObsluhuje, paKohoObsluhuje);
    }
    
    @Override
    public void naplanujDalsiuUdalost() {
        Spojitost sp = new Spojitost(this.simulacnyCas + dalsiaUdalostZaCas, this.mojaSimulacia, null, null);
        this.mojaSimulacia.pridajNovuUdalost(sp);
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Spojitost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
