/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udalosti;

import jadro.*;
import simulacia.*;
import zamestnanci.Kozmeticka;
import zamestnanci.Recepcna;

/**
 *
 * @author Jožko
 */
public class KoniecObsluhyKozmetickouCistenie extends Udalost {
    public KoniecObsluhyKozmetickouCistenie(double paSimulacnyCas, UdalostnaSimulacia paMojaSimulacia, Kozmeticka paKtoObsluhuje, Zakaznik paKohoObsluhuje) {
        super(paSimulacnyCas, paMojaSimulacia, paKtoObsluhuje, paKohoObsluhuje);
    }

    @Override
    public void naplanujDalsiuUdalost() {        
        // ak je rad pred kozmetickai vacsi ako 0
        if (((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadePredKozmetickami() > 0) {
            
            // priemerna dlzka radu - zmena statistiky
            ((KozmetickySalon) this.mojaSimulacia).pripocitajVazenuPocetnostRadKozmeticky((this.simulacnyCas - ((KozmetickySalon) this.mojaSimulacia).dajNaposledyZmenenaDlzkaRaduKozmeticky()) * ((KozmetickySalon) this.mojaSimulacia).dajPocetLudiVRadeKozmeticky());
            ((KozmetickySalon) this.mojaSimulacia).nastavNaposledyZmenenaDlzkaRaduKozmeticky(this.simulacnyCas);
            
            Zakaznik prvy = ((KozmetickySalon) this.mojaSimulacia).dajPrvehoZakaznikaZRaduPredKozmetickami();
            // ak ide prvy zakaznik na cistenie
            if (prvy.chceCisteniePleti() && prvy.bolUzNaCisteni() == false) {
                ZaciatokObsluhyKozmetickouCistenie novaUdalost = new ZaciatokObsluhyKozmetickouCistenie(this.simulacnyCas, this.mojaSimulacia, (Kozmeticka) this.zamestnanec, prvy);
                this.mojaSimulacia.pridajNovuUdalost(novaUdalost);
                prvy.nastavKoniecCakaniaKozmetickyCistenie(this.simulacnyCas);
                ((KozmetickySalon) this.mojaSimulacia).pripocitajCasCakaniaVRadePredKozmetickamiCistenie(prvy.dajKoniecCakaniaKozmetickyCistenie() - prvy.dajZaciatokCakaniaKozmetickyCistenie());
                ((KozmetickySalon) this.mojaSimulacia).pripocitajJednoCakanieVRadePredKozmetickamiCistenie();
            } else {
                // ak ide prvy zakaznik na licenie
                ZaciatokObsluhyKozmetickouLicenie novaUdalost = new ZaciatokObsluhyKozmetickouLicenie(this.simulacnyCas, this.mojaSimulacia, (Kozmeticka) this.zamestnanec, prvy);
                this.mojaSimulacia.pridajNovuUdalost(novaUdalost);
                prvy.nastavKoniecCakaniaKozmetickyLicenie(this.simulacnyCas);
                ((KozmetickySalon) this.mojaSimulacia).pripocitajCasCakaniaVRadePredKozmetickamiLicenie(prvy.dajKoniecCakaniaKozmetickyLicenie() - prvy.dajZaciatokCakaniaKozmetickyLicenie());
                ((KozmetickySalon) this.mojaSimulacia).pripocitajJednoCakanieVRadePredKozmetickamiLicenie();
            }
            
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
            // ak je dlzka radu 0, tak kozmeticku vloz naspat medzi volne kadernicky
            ((KozmetickySalon) this.mojaSimulacia).vlozKozmetickuNaspat((Kozmeticka) this.zamestnanec);
        }
        
        // pripocitaj cas obsluhy k celkovemu casu obsluhy a pripocitaj jednu obsluhu
        ((KozmetickySalon) this.mojaSimulacia).pripocitajCelkovyCasStravenyKozmeticky(this.simulacnyCas - this.zamestnanec.dajZaciatokObsluhy());
        ((KozmetickySalon) this.mojaSimulacia).pripocitajJednuObsluhuKozmeticky();

        // nastav recepcnej koncovy cas obsluhy
        this.zamestnanec.zapisKoniecObsluhy(this.simulacnyCas);
              
        this.zakaznik.nastavBolUzNaCisteni(true);
        
        // naplanuj licenie zakaznika      
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
    }
}
