/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udalosti;

import jadro.*;
import simulacia.*;
import zamestnanci.Recepcna;

/**
 *
 * @author Jožko
 */
public class PrichodDoSystemu extends Udalost {    
    private final double dalsiZakaznikPrideZa = ((KozmetickySalon) this.mojaSimulacia).dajCasDalsiehoPrichoduZakaznika(); // sem pojde hodnota z poissonovskeho generatora
    
    public PrichodDoSystemu(double paSimulacnyCas, UdalostnaSimulacia paMojaSimulacia, Zakaznik paObsluhovanyZakaznik) {
        super(paSimulacnyCas, paMojaSimulacia, null, paObsluhovanyZakaznik);
    }

    @Override
    public void naplanujDalsiuUdalost() {
        // pripocitaj jedneho zakaznika do celkoveho poctu zakaznikov
        ((KozmetickySalon) this.mojaSimulacia).zvysPocetLudiKtoriPrisliDoSystemu();
               
        // ak je pocet ludi v rade pri kadernickach a v rade pred kozmetickami mensi alebo rovny 10, preber objednavku, inak postav zakaznika do radu nech caka
        if ((((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredKadernickai() + ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredKozmetickami()) > 10) {   
                        
            // priemerna dlzka radu - zmena statistiky
            ((KozmetickySalon) this.mojaSimulacia).pripocitajVazenuPocetnostRadRecepcneObjednavka((this.simulacnyCas - ((KozmetickySalon) this.mojaSimulacia).dajNaposledyZmenenaDlzkaRaduRecepcneObjednavka()) * ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredRecepcnymi());
            ((KozmetickySalon) this.mojaSimulacia).nastavNaposledyZmenenaDlzkaRaduRecepcneObjednavka(this.simulacnyCas);
            
            // nastav zakaznikovi zaciatok cakania a postav ho do radu pred recepcynmi
            this.zakaznik.nastavZaciatokCakaniaRecepcneObjednavka(this.simulacnyCas);
            ((KozmetickySalon) this.mojaSimulacia).postavitZakaznikaDoRaduPredRecepcnymiObjednavka(this.zakaznik);
        } else {            
            // naplanuj dalsiu udalost - obsluha recepcnou
            Recepcna ktoBudeObsluhovat = ((KozmetickySalon) this.mojaSimulacia).ktoraRecepcnaBudeObsluhovat();
            if (ktoBudeObsluhovat != null) {
                ZaciatokObsluhyRecepcnouObjednavka novaUdalostObsluha = new ZaciatokObsluhyRecepcnouObjednavka(this.simulacnyCas, this.mojaSimulacia, ktoBudeObsluhovat, this.zakaznik);
                this.mojaSimulacia.pridajNovuUdalost(novaUdalostObsluha);
            
                // pripocitaj nulove cakanie v rade
                ((KozmetickySalon) this.mojaSimulacia).pripocitajCasCakaniaVRadePredRecepcnymiObjednavka(0);
                ((KozmetickySalon) this.mojaSimulacia).pripocitajJednoCakanieVRadePredRecepcnymi();
            } else {
                // postav zakaznika do radu pred recepciou, ak nie je volna ziadna recepcna
            
                // priemerna dlzka radu - zmena statistiky
                ((KozmetickySalon) this.mojaSimulacia).pripocitajVazenuPocetnostRadRecepcneObjednavka((this.simulacnyCas - ((KozmetickySalon) this.mojaSimulacia).dajNaposledyZmenenaDlzkaRaduRecepcneObjednavka()) * ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredRecepcnymi());
                ((KozmetickySalon) this.mojaSimulacia).nastavNaposledyZmenenaDlzkaRaduRecepcneObjednavka(this.simulacnyCas);
            
                this.zakaznik.nastavZaciatokCakaniaRecepcneObjednavka(this.simulacnyCas);
                ((KozmetickySalon) this.mojaSimulacia).postavitZakaznikaDoRaduPredRecepcnymiObjednavka(this.zakaznik);
            }
        }
        
        // naplanuj dalsiu udalost - dalsi prichod zakaznika
        if ((this.simulacnyCas + this.dalsiZakaznikPrideZa) < 28800) {
            Zakaznik dalsiZakaznik = new Zakaznik(this.simulacnyCas + this.dalsiZakaznikPrideZa, this.mojaSimulacia);
            PrichodDoSystemu novaUdalost = new PrichodDoSystemu(this.simulacnyCas + this.dalsiZakaznikPrideZa, this.mojaSimulacia, dalsiZakaznik);
            this.mojaSimulacia.pridajNovuUdalost(novaUdalost);
        }
    }
}
