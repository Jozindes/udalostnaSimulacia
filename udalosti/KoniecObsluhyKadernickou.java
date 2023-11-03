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
public class KoniecObsluhyKadernickou extends Udalost {
    public KoniecObsluhyKadernickou(double paSimulacnyCas, UdalostnaSimulacia paMojaSimulacia, Kadernicka paKtoObsluhuje, Zakaznik paKohoObsluhuje) {
        super(paSimulacnyCas, paMojaSimulacia, paKtoObsluhuje, paKohoObsluhuje);
    }

    @Override
    public void naplanujDalsiuUdalost() {
        // uvolnenie kadernicky - ak je rad pred kadernickami vacsi ako 0, tak hned kadernicku naplanuj pre dalsieho zakaznika
        if (((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredKadernickai() > 0) {
            
            // priemerna dlzka radu - zmena statistiky
            ((KozmetickySalon) this.mojaSimulacia).pripocitajVazenuPocetnostRadKadernicky((this.simulacnyCas - ((KozmetickySalon) this.mojaSimulacia).dajNaposledyZmenenaDlzkaRaduKadernicky()) * ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadeKadernicky());
            ((KozmetickySalon) this.mojaSimulacia).nastavNaposledyZmenenaDlzkaRaduKadernicky(this.simulacnyCas);
            
            Zakaznik prvy = ((KozmetickySalon) this.mojaSimulacia).dajPrvehoZakaznikaZRaduPredKadernickami();
            prvy.nastavKoniecCakaniaKadernicky(this.simulacnyCas);
            ((KozmetickySalon) this.mojaSimulacia).pripocitajCasCakaniaVRadePredKadernickami(prvy.dajKoniecCakaniaKadernicky() - prvy.dajZaciatokCakaniaKadernicky());
            ((KozmetickySalon) this.mojaSimulacia).pripocitajJednoCakanieVRadePredKadernickami();
            ZaciatokObsluhyKadernickou novaUdalostObjednavka = new ZaciatokObsluhyKadernickou(this.simulacnyCas, this.mojaSimulacia, (Kadernicka) this.zamestnanec, prvy);
            this.mojaSimulacia.pridajNovuUdalost(novaUdalostObjednavka);
            
            // ak sa zmensi pocet ludi v rade, tak treba skontrolovat ci netreba obsluzit nejakeho zakaznika pred recepcnymi objednavka
            if ((((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredKadernickai() + ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredKozmetickami()) <= 10) {
                            
                if (((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredRecepcnymi() > 0) {
                    
                    Recepcna ktoBudeObsluhovat = ((KozmetickySalon) this.mojaSimulacia).ktoraRecepcnaBudeObsluhovat();
                    if (ktoBudeObsluhovat != null) {
                        
                        // priemerna dlzka radu - zmena statistiky
                        ((KozmetickySalon) this.mojaSimulacia).pripocitajVazenuPocetnostRadRecepcneObjednavka((this.simulacnyCas - ((KozmetickySalon) this.mojaSimulacia).dajNaposledyZmenenaDlzkaRaduRecepcneObjednavka()) * ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredRecepcnymi());
                        ((KozmetickySalon) this.mojaSimulacia).nastavNaposledyZmenenaDlzkaRaduRecepcneObjednavka(this.simulacnyCas);
                               
                        Zakaznik prvyRecepcne = ((KozmetickySalon) this.mojaSimulacia).dajPrvehoZakaznikaZRaduPredRecepcnymi();
                        prvyRecepcne.nastavKoniecCakaniaRecepcneObjednavka(this.simulacnyCas);
                    
                        ((KozmetickySalon) this.mojaSimulacia).pripocitajCasCakaniaVRadePredRecepcnymiObjednavka(prvyRecepcne.dajKoniecCakaniaRecepcneObjednavka() - prvyRecepcne.dajZaciatokCakaniaRecepcneObjednavka());
                        ((KozmetickySalon) this.mojaSimulacia).pripocitajJednoCakanieVRadePredRecepcnymi();   
                        
                        ZaciatokObsluhyRecepcnouObjednavka novaUdalostObsluha = new ZaciatokObsluhyRecepcnouObjednavka(this.simulacnyCas, this.mojaSimulacia, ktoBudeObsluhovat, prvyRecepcne);
                        this.mojaSimulacia.pridajNovuUdalost(novaUdalostObsluha);
                    }                                                                            
                }
            }
        } else {
            // ak je dlzka radu 0, tak kadernicku vloz naspat medzi volne kadernicky
            ((KozmetickySalon) this.mojaSimulacia).vlozKadernickuNaspat((Kadernicka) this.zamestnanec);
        }
        
        // pripocitaj cas obsluhy k celkovemu casu obsluhy a pripocitaj jednu obsluhu
        ((KozmetickySalon) this.mojaSimulacia).pripocitajCelkovyCasStravenyKadernicky(this.simulacnyCas - this.zamestnanec.dajZaciatokObsluhy());
        ((KozmetickySalon) this.mojaSimulacia).pripocitajJednuObsluhuKadernicky();
        
        // nastav recepcnej koncovy cas obsluhy
        this.zamestnanec.zapisKoniecObsluhy(this.simulacnyCas);
                      
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
            if (this.zakaznik.chceLicenie()) {
                Kozmeticka ktora = ((KozmetickySalon) this.mojaSimulacia).ktoraKozmetickaBudeObsluhovat();
                if (ktora != null) {
                    ZaciatokObsluhyKozmetickouLicenie novaUdalost = new ZaciatokObsluhyKozmetickouLicenie(this.simulacnyCas, this.mojaSimulacia, (Kozmeticka) ktora, this.zakaznik);
                    this.mojaSimulacia.pridajNovuUdalost(novaUdalost);
                    
                    ((KozmetickySalon) this.mojaSimulacia).pripocitajCasCakaniaVRadePredKozmetickamiLicenie(0);
                    ((KozmetickySalon) this.mojaSimulacia).pripocitajJednoCakanieVRadePredKozmetickamiLicenie();
                } else {
                    
                    // priemerna dlzka radu - zmena statistiky
                    ((KozmetickySalon) this.mojaSimulacia).pripocitajVazenuPocetnostRadKozmeticky((this.simulacnyCas - ((KozmetickySalon) this.mojaSimulacia).dajNaposledyZmenenaDlzkaRaduKozmeticky()) * ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadeKozmeticky());
                    ((KozmetickySalon) this.mojaSimulacia).nastavNaposledyZmenenaDlzkaRaduKozmeticky(this.simulacnyCas);
                    
                    ((KozmetickySalon) this.mojaSimulacia).postavitZakaznikaDoRaduPredKozmetickami(this.zakaznik);
                    this.zakaznik.nastavZaciatokCakaniaKozmetickyLicenie(this.simulacnyCas);
                }
            } else {
                // ak nechce ani licenie ani cistenie, tak mu naplanuj platbu
                Recepcna ktoBudeObsluhovat = ((KozmetickySalon) this.mojaSimulacia).ktoraRecepcnaBudeObsluhovat();
                if (ktoBudeObsluhovat != null) {
                    ZaciatokObsluhyRecepcnouPlatba novaUdalostObsluha = new ZaciatokObsluhyRecepcnouPlatba(this.simulacnyCas, this.mojaSimulacia, ktoBudeObsluhovat, this.zakaznik);
                    this.mojaSimulacia.pridajNovuUdalost(novaUdalostObsluha);
                    
                    ((KozmetickySalon) this.mojaSimulacia).pripocitajCasCakaniaVRadePredRecepcnymiPlatba(0);
                    ((KozmetickySalon) this.mojaSimulacia).pripocitajJednoCakanieVRadePredRecepcnymiPlatba();
                } else {
                    // postav zakaznika do radu na zaplatenie pred recepciou, ak nie je volna ziadna recepcna
                    
                    // priemerna dlzka radu - zmena statistiky
                    ((KozmetickySalon) this.mojaSimulacia).pripocitajVazenuPocetnostRadRecepcnePlatba((this.simulacnyCas - ((KozmetickySalon) this.mojaSimulacia).dajNaposledyZmenenaDlzkaRaduRecepcnePlatba()) * ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredRecepcnymiPlatba());
                    ((KozmetickySalon) this.mojaSimulacia).nastavNaposledyZmenenaDlzkaRaduRecepcnePlatba(this.simulacnyCas);
                    
                    this.zakaznik.nastavZaciatokCakaniaRecepcnePlatba(this.simulacnyCas);
                    ((KozmetickySalon) this.mojaSimulacia).postavitZakaznikaDoRaduPredRecepcnymiPlatba(this.zakaznik);
                }
            }
        }
    }      
}
