/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udalosti;

import jadro.*;
import simulacia.*;
import zamestnanci.*;

/**
 *
 * @author Jožko
 */
public class KoniecObsluhyRecepcnouPlatba extends Udalost {
    public KoniecObsluhyRecepcnouPlatba(double paSimulacnyCas, UdalostnaSimulacia paMojaSimulacia, Recepcna paKtoObsluhuje, Zakaznik paKohoObsluhuje) {
        super(paSimulacnyCas, paMojaSimulacia, paKtoObsluhuje, paKohoObsluhuje);
    }

    @Override
    public void naplanujDalsiuUdalost() {
        // ak je rad na platbu pred recepciou vacsi ako 0, tak hned recepcnu naplanuj pre dalsieho zakaznika       
        if (((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredRecepcnymiPlatba() > 0) {
            
            // priemerna dlzka radu - zmena statistiky
            ((KozmetickySalon) this.mojaSimulacia).pripocitajVazenuPocetnostRadRecepcnePlatba((this.simulacnyCas - ((KozmetickySalon) this.mojaSimulacia).dajNaposledyZmenenaDlzkaRaduRecepcnePlatba()) * ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredRecepcnymiPlatba());
            ((KozmetickySalon) this.mojaSimulacia).nastavNaposledyZmenenaDlzkaRaduRecepcnePlatba(this.simulacnyCas);
            
            Zakaznik prvy = ((KozmetickySalon) this.mojaSimulacia).dajPrvehoZakaznikaZRaduPredRecepcnymiPlatba();
            prvy.nastavKoniecCakaniaRecepcnePlatba(this.simulacnyCas);
            ((KozmetickySalon) this.mojaSimulacia).pripocitajCasCakaniaVRadePredRecepcnymiPlatba(prvy.dajKoniecCakaniaRecepcnePlatba() - prvy.dajZaciatokCakaniaRecepcnePlatba());
            ((KozmetickySalon) this.mojaSimulacia).pripocitajJednoCakanieVRadePredRecepcnymiPlatba();
            ZaciatokObsluhyRecepcnouPlatba novaUdalostObjednavka = new ZaciatokObsluhyRecepcnouPlatba(this.simulacnyCas, this.mojaSimulacia, (Recepcna) this.zamestnanec, prvy);
            this.mojaSimulacia.pridajNovuUdalost(novaUdalostObjednavka);
        } else {
            // ak je dlzka radu na platbu 0 ale zakaznici cakaju v rade na objednavku, tak recepcnu naplanuj na objednavku
            if (((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredRecepcnymi() > 0) {
                
                // ak je pocet ludi v rade pri kadernickach a v rade pred kozmetickami mensi alebo rovny 10, preber objednavku, inak nie
                if ((((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredKadernickai() + ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredKozmetickami()) <= 10) {
                    
                    ((KozmetickySalon) this.mojaSimulacia).pripocitajVazenuPocetnostRadRecepcneObjednavka((this.simulacnyCas - ((KozmetickySalon) this.mojaSimulacia).dajNaposledyZmenenaDlzkaRaduRecepcneObjednavka()) * ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredRecepcnymi());
                    ((KozmetickySalon) this.mojaSimulacia).nastavNaposledyZmenenaDlzkaRaduRecepcneObjednavka(this.simulacnyCas);
                
                    Zakaznik prvy = ((KozmetickySalon) this.mojaSimulacia).dajPrvehoZakaznikaZRaduPredRecepcnymi();
                    prvy.nastavKoniecCakaniaRecepcneObjednavka(this.simulacnyCas);
                    ((KozmetickySalon) this.mojaSimulacia).pripocitajCasCakaniaVRadePredRecepcnymiObjednavka(prvy.dajKoniecCakaniaRecepcneObjednavka() - prvy.dajZaciatokCakaniaRecepcneObjednavka());
                    ((KozmetickySalon) this.mojaSimulacia).pripocitajJednoCakanieVRadePredRecepcnymi();
                    ZaciatokObsluhyRecepcnouObjednavka novaUdalostObjednavka = new ZaciatokObsluhyRecepcnouObjednavka(this.simulacnyCas, this.mojaSimulacia, (Recepcna) this.zamestnanec, prvy);
                    this.mojaSimulacia.pridajNovuUdalost(novaUdalostObjednavka);
                } else {
                    ((KozmetickySalon) this.mojaSimulacia).vlozRecepcnuNaspat((Recepcna) this.zamestnanec);
                }
            } else {
                // ak je dlzka radu na objednavku 0, tak recepcnu vloz naspat medzi volne recepcne
                ((KozmetickySalon) this.mojaSimulacia).vlozRecepcnuNaspat((Recepcna) this.zamestnanec);
            }
        }
        
        // pripocitaj cas obsluhy k celkovemu casu obsluhy a pripocitaj jednu obsluhu
        ((KozmetickySalon) this.mojaSimulacia).pripocitajCelkovyCasStravenyRecepcne(this.simulacnyCas - this.zamestnanec.dajZaciatokObsluhy());
        ((KozmetickySalon) this.mojaSimulacia).pripocitajJednuObsluhuRecepcne();
        
        // nastav recepcnej koncovy cas obsluhy
        this.zamestnanec.zapisKoniecObsluhy(this.simulacnyCas);
                       
        ((KozmetickySalon) this.mojaSimulacia).zvysPocetLudiKtoriOdisliZoSystemu();
        
        ((KozmetickySalon) this.mojaSimulacia).pripocitajCelkovyCasZakaznikovVSysteme(this.simulacnyCas - this.zakaznik.dajCasPrichoduDoSystemu());
        
        if (((KozmetickySalon) this.mojaSimulacia).dajPocetLudiKtoriPrisliDoSystemu() == ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiKtoriOdisliZoSystemu() && this.mojaSimulacia.dajSimulacnyCas() > 28800) {
            this.mojaSimulacia.zmazCelyZoznamUdalosti();
            ((KozmetickySalon) this.mojaSimulacia).akoDlhoBoloOtvorenePo();
        }
    }
}
