/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udalosti;

import jadro.Udalost;
import jadro.UdalostnaSimulacia;
import simulacia.KozmetickySalon;
import simulacia.Zakaznik;
import zamestnanci.*;

/**
 *
 * @author Jožko
 */
public class ZaciatokObsluhyKadernickou extends Udalost {
    private final double dlzkaObsluhy; // sem pojde hodnota z generatora
    
    public ZaciatokObsluhyKadernickou(double paSimulacnyCas, UdalostnaSimulacia paMojaSimulacia, Kadernicka paKtoObsluhuje, Zakaznik paKohoObsluhuje) {
        super(paSimulacnyCas, paMojaSimulacia, paKtoObsluhuje, paKohoObsluhuje);
        
        double pravdepodobnost = ((KozmetickySalon) this.mojaSimulacia).dajPravdepodobnostVyberuUcesu();
        if (pravdepodobnost < 0.4) {
            this.dlzkaObsluhy = ((KozmetickySalon) this.mojaSimulacia).dajCasJednoduchehoUcesu();
        } else if (pravdepodobnost >= 0.4 && pravdepodobnost < 0.8) {
            this.dlzkaObsluhy = ((KozmetickySalon) this.mojaSimulacia).dajCasZlozitehoUcesu();
        } else {
            this.dlzkaObsluhy = ((KozmetickySalon) this.mojaSimulacia).dajCasSvadobnehoUcesu();
        }
    }

    @Override
    public void naplanujDalsiuUdalost() {
        KoniecObsluhyKadernickou novaUdalost = new KoniecObsluhyKadernickou(this.simulacnyCas + this.dlzkaObsluhy, this.mojaSimulacia, (Kadernicka) this.zamestnanec, this.zakaznik);      
        this.mojaSimulacia.pridajNovuUdalost(novaUdalost);
        // nastav kadernicke zaciatocny cas obsluhy
        this.zamestnanec.zapisZaciatokObsluhy(this.simulacnyCas);
    }
}
