/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udalosti;

import jadro.*;
import simulacia.KozmetickySalon;
import simulacia.Zakaznik;
import zamestnanci.*;

/**
 *
 * @author Jožko
 */
public class ZaciatokObsluhyKozmetickouCistenie extends Udalost {
    private final double dlzkaObsluhy = ((KozmetickySalon) this.mojaSimulacia).dajCasTrvaniaCisteniaPleti();
    
    public ZaciatokObsluhyKozmetickouCistenie(double paSimulacnyCas, UdalostnaSimulacia paMojaSimulacia, Kozmeticka paKtoObsluhuje, Zakaznik paKohoObsluhuje) {
        super(paSimulacnyCas, paMojaSimulacia, paKtoObsluhuje, paKohoObsluhuje);
    }

    @Override
    public void naplanujDalsiuUdalost() {
        // naplanuj dalsiu udalost
        KoniecObsluhyKozmetickouCistenie novaUdalost = new KoniecObsluhyKozmetickouCistenie(this.simulacnyCas + this.dlzkaObsluhy, this.mojaSimulacia, (Kozmeticka) this.zamestnanec, this.zakaznik);      
        this.mojaSimulacia.pridajNovuUdalost(novaUdalost);
        // nastav kadernicke zaciatocny cas obsluhy
        this.zamestnanec.zapisZaciatokObsluhy(this.simulacnyCas);
    }
}
