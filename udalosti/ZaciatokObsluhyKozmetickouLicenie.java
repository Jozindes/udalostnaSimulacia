/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udalosti;

import jadro.Udalost;
import jadro.UdalostnaSimulacia;
import simulacia.KozmetickySalon;
import simulacia.Zakaznik;
import zamestnanci.Kozmeticka;

/**
 *
 * @author Jožko
 */
public class ZaciatokObsluhyKozmetickouLicenie extends Udalost {
    private final double dlzkaObsluhy; // sem pojde hodnota z generatora
    
    public ZaciatokObsluhyKozmetickouLicenie(double paSimulacnyCas, UdalostnaSimulacia paMojaSimulacia, Kozmeticka paKtoObsluhuje, Zakaznik paKohoObsluhuje) {
        super(paSimulacnyCas, paMojaSimulacia, paKtoObsluhuje, paKohoObsluhuje);
        
        double pravdepodobnost = ((KozmetickySalon) this.mojaSimulacia).dajPravdepodobnostTypuLicenia();
        if (pravdepodobnost < 0.3) {
            this.dlzkaObsluhy = ((KozmetickySalon) this.mojaSimulacia).dajCasJednoduchehoLicenia();
        } else {
            this.dlzkaObsluhy = ((KozmetickySalon) this.mojaSimulacia).dajCasZlozitehoLicenia();
        }
    }

    @Override
    public void naplanujDalsiuUdalost() {
        KoniecObsluhyKozmetickouLicenie novaUdalost = new KoniecObsluhyKozmetickouLicenie(this.simulacnyCas + this.dlzkaObsluhy, this.mojaSimulacia, (Kozmeticka) this.zamestnanec, this.zakaznik);      
        this.mojaSimulacia.pridajNovuUdalost(novaUdalost);
        // nastav kadernicke zaciatocny cas obsluhy
        this.zamestnanec.zapisZaciatokObsluhy(this.simulacnyCas);
    }
}
