/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulacia;

import jadro.UdalostnaSimulacia;

/**
 *
 * @author Jožko
 */
public class Zakaznik {   
    private final double prichodDoSystemu;
    private double zaciatokCakaniaRecepcneObjednavka;
    private double koniecCakaniaRecepcneObjednavka;
    private double zaciatokCakaniaRecepcnePlatba;
    private double koniecCakaniaRecepcnePlatba;
    private double zaciatokCakaniaKadernicky;
    private double koniecCakaniaKadernicky;
    private double zaciatokCakaniaKozmetickyCistenie;
    private double koniecCakaniaKozmetickyCistenie;
    private double zaciatokCakaniaKozmetickyLicenie;
    private double koniecCakaniaKozmetickyLicenie;
          
    private boolean upravaUcesu = false;
    private boolean cisteniePleti = false;
    private boolean licenie = false;
    private boolean uzBolNaCisteni = false;
       
    private final UdalostnaSimulacia mojaSimulacia;
    
    public Zakaznik(double paPrichodDoSystemu, UdalostnaSimulacia paSimulacia) {
        this.prichodDoSystemu = paPrichodDoSystemu;
        this.mojaSimulacia = paSimulacia;
        double vygenerovanaHodnotaZaujemOSluzby = ((KozmetickySalon) this.mojaSimulacia).dajPravdepodobnostZaujmuOSluzby();
        if (vygenerovanaHodnotaZaujemOSluzby < 0.2) {
            this.upravaUcesu = true;
        } else if (vygenerovanaHodnotaZaujemOSluzby >= 0.2 && vygenerovanaHodnotaZaujemOSluzby < 0.35) {
            this.licenie = true;
            double vygenerovanaHodnotaZaujemOCisteniePleti = ((KozmetickySalon) this.mojaSimulacia).dajPravdepodobnostZaujmuOCisteniePleti();
            if (vygenerovanaHodnotaZaujemOCisteniePleti < 0.35) {
                this.cisteniePleti = true;
            }
        } else {
            this.upravaUcesu = true;
            this.licenie = true;
            double vygenerovanaHodnotaZaujemOCisteniePleti = ((KozmetickySalon) this.mojaSimulacia).dajPravdepodobnostZaujmuOCisteniePleti();
            if (vygenerovanaHodnotaZaujemOCisteniePleti < 0.35) {
                this.cisteniePleti = true;
            }
        }
    }
    
    public double dajCasPrichoduDoSystemu() {
        return this.prichodDoSystemu;
    }
    // ----------- objednavka
    public void nastavZaciatokCakaniaRecepcneObjednavka(double paSimulacnyCas) {
        this.zaciatokCakaniaRecepcneObjednavka = paSimulacnyCas;
    }
    
    public void nastavKoniecCakaniaRecepcneObjednavka(double paSimulacnyCas) {
        this.koniecCakaniaRecepcneObjednavka = paSimulacnyCas;
    }
    
    public double dajZaciatokCakaniaRecepcneObjednavka() {
        return this.zaciatokCakaniaRecepcneObjednavka;
    }
    
    public double dajKoniecCakaniaRecepcneObjednavka() {
        return this.koniecCakaniaRecepcneObjednavka;
    }
    // ---------------- platba
    
    public void nastavZaciatokCakaniaRecepcnePlatba(double paSimulacnyCas) {
        this.zaciatokCakaniaRecepcnePlatba = paSimulacnyCas;
    }
    
    public void nastavKoniecCakaniaRecepcnePlatba(double paSimulacnyCas) {
        this.koniecCakaniaRecepcnePlatba = paSimulacnyCas;
    }
    
    public double dajZaciatokCakaniaRecepcnePlatba() {
        return this.zaciatokCakaniaRecepcnePlatba;
    }
    
    public double dajKoniecCakaniaRecepcnePlatba() {
        return this.koniecCakaniaRecepcnePlatba;
    }
    
    // -----------
    public void nastavZaciatokCakaniaKadernicky(double paSimulacnyCas) {
        this.zaciatokCakaniaKadernicky = paSimulacnyCas;
    }
    
    public void nastavKoniecCakaniaKadernicky(double paSimulacnyCas) {
        this.koniecCakaniaKadernicky = paSimulacnyCas;
    }
    
    public double dajZaciatokCakaniaKadernicky() {
        return this.zaciatokCakaniaKadernicky;
    }
    
    public double dajKoniecCakaniaKadernicky() {
        return this.koniecCakaniaKadernicky;
    }
    // ----------- kozmeticky cistenie
    public void nastavZaciatokCakaniaKozmetickyCistenie(double paSimulacnyCas) {
        this.zaciatokCakaniaKozmetickyCistenie = paSimulacnyCas;
    }
    
    public void nastavKoniecCakaniaKozmetickyCistenie(double paSimulacnyCas) {
        this.koniecCakaniaKozmetickyCistenie = paSimulacnyCas;
    }
    
    public double dajZaciatokCakaniaKozmetickyCistenie() {
        return this.zaciatokCakaniaKozmetickyCistenie;
    }
    
    public double dajKoniecCakaniaKozmetickyCistenie() {
        return this.koniecCakaniaKozmetickyCistenie;
    }
    
    // -----------
    public void nastavZaciatokCakaniaKozmetickyLicenie(double paSimulacnyCas) {
        this.zaciatokCakaniaKozmetickyLicenie = paSimulacnyCas;
    }

    public void nastavKoniecCakaniaKozmetickyLicenie(double paSimulacnyCas) {
        this.koniecCakaniaKozmetickyLicenie = paSimulacnyCas;
    }
    
    public double dajZaciatokCakaniaKozmetickyLicenie() {
        return this.zaciatokCakaniaKozmetickyLicenie;
    }
    
    public double dajKoniecCakaniaKozmetickyLicenie() {
        return this.koniecCakaniaKozmetickyLicenie;
    }

    // -----------
    public boolean chceUpravitUces() {
        return this.upravaUcesu;
    }

    // -----------
    public boolean chceCisteniePleti() {
        return this.cisteniePleti;
    }
    
    public boolean chceLicenie() {
        return this.licenie;
    }
    
    public void nastavBolUzNaCisteni(boolean stav) {
        this.uzBolNaCisteni = stav;
    }
    
    public boolean bolUzNaCisteni() {
        return this.uzBolNaCisteni;
    }       
}
