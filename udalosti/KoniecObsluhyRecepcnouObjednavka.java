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
public class KoniecObsluhyRecepcnouObjednavka extends Udalost {
    public KoniecObsluhyRecepcnouObjednavka(double paSimulacnyCas, UdalostnaSimulacia paMojaSimulacia, Recepcna paKtoObsluhuje, Zakaznik paKohoObsluhuje) {
        super(paSimulacnyCas, paMojaSimulacia, paKtoObsluhuje, paKohoObsluhuje);
    }

    @Override
    public void naplanujDalsiuUdalost() {
        // vykonaj svoju cinnost

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
                    // priemerna dlzka radu - zmena statistiky
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
                       
        // ak zakaznik chce upravu ucesu, tak mu ju naplanuj
        if (this.zakaznik.chceUpravitUces()) {
            Kadernicka ktora = ((KozmetickySalon) this.mojaSimulacia).ktoraKadernickaBudeObsluhovat();
            if (ktora != null) {
                ZaciatokObsluhyKadernickou novaUdalost = new ZaciatokObsluhyKadernickou(this.simulacnyCas, this.mojaSimulacia, (Kadernicka) ktora, this.zakaznik);
                this.mojaSimulacia.pridajNovuUdalost(novaUdalost);
                
                ((KozmetickySalon) this.mojaSimulacia).pripocitajCasCakaniaVRadePredKadernickami(0);
                ((KozmetickySalon) this.mojaSimulacia).pripocitajJednoCakanieVRadePredKadernickami();
                
            } else {
                
                // priemerna dlzka radu - zmena statistiky
                ((KozmetickySalon) this.mojaSimulacia).pripocitajVazenuPocetnostRadKadernicky((this.simulacnyCas - ((KozmetickySalon) this.mojaSimulacia).dajNaposledyZmenenaDlzkaRaduKadernicky()) * ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadeKadernicky());
                ((KozmetickySalon) this.mojaSimulacia).nastavNaposledyZmenenaDlzkaRaduKadernicky(this.simulacnyCas);
                
                ((KozmetickySalon) this.mojaSimulacia).postavitZakaznikaDoRaduPredKadernickami(this.zakaznik);
                this.zakaznik.nastavZaciatokCakaniaKadernicky(this.simulacnyCas);
            }
        } else {
            // ak zakaznik chce hlbkove licenie a hlbkove cistenie pleti, tak mu ho naplanuj
            if (this.zakaznik.chceLicenie() && this.zakaznik.chceCisteniePleti()) {
                Kozmeticka ktora = ((KozmetickySalon) this.mojaSimulacia).ktoraKozmetickaBudeObsluhovat();
                if (ktora != null) {
                    ZaciatokObsluhyKozmetickouCistenie novaUdalost = new ZaciatokObsluhyKozmetickouCistenie(this.simulacnyCas, this.mojaSimulacia, (Kozmeticka) ktora, this.zakaznik);
                    this.mojaSimulacia.pridajNovuUdalost(novaUdalost);
                    
                    ((KozmetickySalon) this.mojaSimulacia).pripocitajCasCakaniaVRadePredKozmetickamiCistenie(0);
                    ((KozmetickySalon) this.mojaSimulacia).pripocitajJednoCakanieVRadePredKozmetickamiCistenie();
                } else {
                    
                    // priemerna dlzka radu - zmena statistiky
                    ((KozmetickySalon) this.mojaSimulacia).pripocitajVazenuPocetnostRadKozmeticky((this.simulacnyCas - ((KozmetickySalon) this.mojaSimulacia).dajNaposledyZmenenaDlzkaRaduKozmeticky()) * ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadeKozmeticky());
                    ((KozmetickySalon) this.mojaSimulacia).nastavNaposledyZmenenaDlzkaRaduKozmeticky(this.simulacnyCas);
                    
                    ((KozmetickySalon) this.mojaSimulacia).postavitZakaznikaDoRaduPredKozmetickami(this.zakaznik);
                    this.zakaznik.nastavZaciatokCakaniaKozmetickyCistenie(this.simulacnyCas);
                }
            } else {
                // ak zakaznik chce len licenie
                Kozmeticka ktora = ((KozmetickySalon) this.mojaSimulacia).ktoraKozmetickaBudeObsluhovat();
                if (ktora != null) {
                    ZaciatokObsluhyKozmetickouLicenie novaUdalost = new ZaciatokObsluhyKozmetickouLicenie(this.simulacnyCas, this.mojaSimulacia, (Kozmeticka) ktora, this.zakaznik);
                    this.mojaSimulacia.pridajNovuUdalost(novaUdalost);
                    
                    // pripocitaj nulove cakanie v rade
                    ((KozmetickySalon) this.mojaSimulacia).pripocitajCasCakaniaVRadePredKozmetickamiLicenie(0);
                    ((KozmetickySalon) this.mojaSimulacia).pripocitajJednoCakanieVRadePredKozmetickamiLicenie();
                } else {
                    
                    // priemerna dlzka radu - zmena statistiky
                    ((KozmetickySalon) this.mojaSimulacia).pripocitajVazenuPocetnostRadKozmeticky((this.simulacnyCas - ((KozmetickySalon) this.mojaSimulacia).dajNaposledyZmenenaDlzkaRaduKozmeticky()) * ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadeKozmeticky());
                    ((KozmetickySalon) this.mojaSimulacia).nastavNaposledyZmenenaDlzkaRaduKozmeticky(this.simulacnyCas);
                    
                    ((KozmetickySalon) this.mojaSimulacia).postavitZakaznikaDoRaduPredKozmetickami(this.zakaznik);
                    this.zakaznik.nastavZaciatokCakaniaKozmetickyLicenie(this.simulacnyCas);
                }
            }
        }     
    } 
}
