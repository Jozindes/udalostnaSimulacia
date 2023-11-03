/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udalosti;

import jadro.Udalost;
import jadro.UdalostnaSimulacia;
import simulacia.KozmetickySalon;
import simulacia.Zakaznik;
import zamestnanci.Zamestnanec;

/**
 *
 * @author Jožko
 */
public class OdoberZakaznikovRadRecepcneObjednavka extends Udalost {

    public OdoberZakaznikovRadRecepcneObjednavka(double paSimulacnyCas, UdalostnaSimulacia paMojaSimulacia, Zamestnanec paKtoObsluhuje, Zakaznik paKohoObsluhuje) {
        super(paSimulacnyCas, paMojaSimulacia, paKtoObsluhuje, paKohoObsluhuje);
    }
    
    @Override
    public void naplanujDalsiuUdalost() {
        int kolko = ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredRecepcnymi();
        ((KozmetickySalon) this.mojaSimulacia).nastavKolkoLudiBoloPoslanychDomov(kolko);
        ((KozmetickySalon) this.mojaSimulacia).odstranVsetkychZakaznikovRadRecepcneObjednavka();
    }
    
}
