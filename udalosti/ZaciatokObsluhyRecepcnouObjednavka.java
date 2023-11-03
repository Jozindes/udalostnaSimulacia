/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udalosti;

import jadro.*;
import zamestnanci.*;
import simulacia.*;

/**
 *
 * @author Jožko
 */
public class ZaciatokObsluhyRecepcnouObjednavka extends Udalost {
    private final double dlzkaObsluhy = ((KozmetickySalon) this.mojaSimulacia).dajCasTrvaniaVybaveniaObjednavky();
    
    public ZaciatokObsluhyRecepcnouObjednavka(double paSimulacnyCas, UdalostnaSimulacia paMojaSimulacia, Recepcna paKtoObsluhuje, Zakaznik paKohoObsluhuje) {
        super(paSimulacnyCas, paMojaSimulacia, paKtoObsluhuje, paKohoObsluhuje);
    }

    @Override
    public void naplanujDalsiuUdalost() {
        // vykonaj svoju cinnost
        
        // naplanuj dalsiu udalost
        KoniecObsluhyRecepcnouObjednavka novaUdalost = new KoniecObsluhyRecepcnouObjednavka(this.simulacnyCas + this.dlzkaObsluhy, this.mojaSimulacia, (Recepcna) this.zamestnanec, this.zakaznik);      
        this.mojaSimulacia.pridajNovuUdalost(novaUdalost);
        // nastav recepcnej zaciatocny cas obsluhy
        this.zamestnanec.zapisZaciatokObsluhy(this.simulacnyCas);
    }  
}
