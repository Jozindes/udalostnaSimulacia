/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udalosti;

import jadro.Udalost;
import jadro.UdalostnaSimulacia;
import simulacia.KozmetickySalon;
import simulacia.Zakaznik;
import zamestnanci.Recepcna;

/**
 *
 * @author Jožko
 */
public class ZaciatokObsluhyRecepcnouPlatba extends Udalost {    
    private final double dlzkaObsluhy = ((KozmetickySalon) this.mojaSimulacia).dajCasTrvaniaVybaveniaPlatby();
    
    public ZaciatokObsluhyRecepcnouPlatba(double paSimulacnyCas, UdalostnaSimulacia paMojaSimulacia, Recepcna paKtoObsluhuje, Zakaznik paKohoObsluhuje) {
        super(paSimulacnyCas, paMojaSimulacia, paKtoObsluhuje, paKohoObsluhuje);
    }

    @Override
    public void naplanujDalsiuUdalost() {
        // naplanuj dalsiu udalost
        KoniecObsluhyRecepcnouPlatba novaUdalost = new KoniecObsluhyRecepcnouPlatba(this.simulacnyCas + this.dlzkaObsluhy, this.mojaSimulacia, (Recepcna) this.zamestnanec, this.zakaznik);      
        this.mojaSimulacia.pridajNovuUdalost(novaUdalost);
        // nastav recepcnej zaciatocny cas obsluhy
        this.zamestnanec.zapisZaciatokObsluhy(this.simulacnyCas);
    }
}
