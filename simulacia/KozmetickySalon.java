/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulacia;

import generatory.DiskretneEmpiricke;
import generatory.Exponencialne;
import generatory.PrvokEmpiricke;
import generatory.RovnomerneSpojite;
import generatory.Trojuholnikove;
import java.util.ArrayList;
import java.util.LinkedList;
import zamestnanci.*;
import jadro.*;
import java.util.Collections;
import java.util.Random;
import udalosti.*;

/**
 *
 * @author Jožko
 */
public class KozmetickySalon extends UdalostnaSimulacia {
    // premenne v systeme
    private final int celkovyPocetRecepcnych;
    private final int celkovyPocetKadernicok;
    private final int celkovyPocetKozmeticok;
    
    private ArrayList<Recepcna> volneRecepcne;
    private ArrayList<Kadernicka> volneKadernicky;
    private ArrayList<Kozmeticka> volneKozmeticky;
    
    private ArrayList<Recepcna> pracujuceRecepcne;
    private ArrayList<Kadernicka> pracujuceKadernicky;
    private ArrayList<Kozmeticka> pracujuceKozmeticky;
    
    private LinkedList<Zakaznik> cakajuciPredRecepcnymiObjednavka;
    private LinkedList<Zakaznik> cakajuciPredRecepcnymiZaplatenie;
    private LinkedList<Zakaznik> cakajuciPredKadernickami;
    private LinkedList<Zakaznik> cakajuciPredKozmetickami;
    
    // premenne - generatory
    private RovnomerneSpojite generovanieVybavovaniaObjednavky;
    private Trojuholnikove generovanieCisteniaPleti;
    private RovnomerneSpojite generovanieVybavovaniaPlatby;
    
    private Random generovanieZaujmuOSluzby;
    private Random generovanieZaujmuOCisteniePleti;
    
    private Exponencialne generovaniePrichodov;
    
    private Random pravdepodobnostVyberuUcesu;
    private Random pravdepodobnostTypuLicenia;
    
    private Random jednoduchyUces;
    private DiskretneEmpiricke zlozityUces;
    private DiskretneEmpiricke svadobnyUces;
    
    private Random jednoducheLicenie;
    private Random zloziteLicenie;
    
    private Random generatorNasad;
    
    private double spojitostSimulacieMedzera;
    
    // statisticke premenne
    private int kolkoLudiPrisloDoSystemu;
    private int kolkoLudiOdisloZoSystemu;
    private double celkovyCasVSyseteme;
    
    private double celkovyCasCakaniaVRadePredRecepcnymiObjednavka;
    private int pocetCakaniVRadePredRecepcnymiObjednavka;
    
    private double celkovyCasCakaniaVRadePredRecepcnymiPlatba;
    private int pocetCakaniVRadePredRecepcnymiPlatba;
    
    private double celkovyCasCakaniaVRadePredKadernickami;
    private int pocetCakaniVRadePredKadernickami;
    
    private double celkovyCasCakaniaVRadePredKozmetickamiCistenie;
    private int pocetCakaniVRadePredKozmetickamiCistenie;
    
    private double celkovyCasCakaniaVRadePredKozmetickamiLicenie;
    private int pocetCakaniVRadePredKozmetickamiLicenie;
    
    private double naposledyZmenenaDlzkaRaduRecepcneObjednavka;
    private double naposledyZmenenaDlzkaRaduRecepcnePlatba;
    private double naposledyZmenenaDlzkaRaduKadernicky;
    private double naposledyZmenenaDlzkaRaduKozmeticky;
    
    private double vazenaPocetnostRadRecepcneObjednavky;
    private double vazenaPocetnostRadRecepcnePlatba;
    private double vazenaPocetnostRadKadernicky;
    private double vazenaPocetnostRadKozmeticky;
    
    private double celkovyCasStravenyRecepcne;
    private int pocetVybavovaniRecepcne;
    private double celkovyCasStravenyKadernicky;
    private int pocetVybavovaniKadernicky;
    private double celkovyCasStravenyKozmeticky;
    private int pocetVybavovaniKozmeticky;
    
    private double akoDlhoBoloOtvorenePoVyprsaniOtvaracichHodin;
    private int kolkoLudiBoloPoslanychDomov;
    
    // sumove premenne pouzivane pri replikaciach - statistiky od otvorenia do obluzenia posledneho zakaznika
    private int pocetVykonanychReplikacii;
    private double sumaVytazenieRecepcnych;
    private double sumaVytazenieKadernicok;
    private double sumaVytazenieKozmeticok;
    private double sumaCasVSysteme;
    private double sumaDlzkaRaduPredRecepcnymiObjednavka;
    private double sumaDlzkaRaduPredRecepcnymiPlatba;
    private double sumaDlzkaRaduPredKadernickami;
    private double sumaDlzkaRaduPredKozmetickami;
    private double sumaCasVRadePredRecepcnymiObjednavka;
    private double sumaCasVRadePredRecepcnymiPlatba;
    private double sumaCasVRadePredKadernickami;
    private double sumaCasVRadePredKozmetickamiCistenie;
    private double sumaCasVRadePredKozmetickamiLicenie;
    private double sumaCelkovyCasObsluhyRecepcne;
    private double sumaCelkovyCasObsluhyKadernicky;
    private double sumaCelkovyCasObsluhyKozmeticky;
    private double sumaAkoDlhoBoloOtvoreneNaviac;
    private double sumaKolkoLudiBoloVyhodenych;
    
    // statisticke premenne - pocitaju sa len do 17:00
    private final double prelomovyCas = 28800;
    private int kolkoLudiPrisloDoSystemuBO;
    private int kolkoLudiOdisloZoSystemuBO;
    private double celkovyCasVSysetemeBO;
    
    private double celkovyCasCakaniaVRadePredRecepcnymiObjednavkaBO;
    private int pocetCakaniVRadePredRecepcnymiObjednavkaBO;
    
    private double celkovyCasCakaniaVRadePredRecepcnymiPlatbaBO;
    private int pocetCakaniVRadePredRecepcnymiPlatbaBO;
    
    private double celkovyCasCakaniaVRadePredKadernickamiBO;
    private int pocetCakaniVRadePredKadernickamiBO;
    
    private double celkovyCasCakaniaVRadePredKozmetickamiCistenieBO;
    private int pocetCakaniVRadePredKozmetickamiCistenieBO;
    
    private double celkovyCasCakaniaVRadePredKozmetickamiLicenieBO;
    private int pocetCakaniVRadePredKozmetickamiLicenieBO;
      
    private double vazenaPocetnostRadRecepcneObjednavkyBO;
    private double vazenaPocetnostRadRecepcnePlatbaBO;
    private double vazenaPocetnostRadKadernickyBO;
    private double vazenaPocetnostRadKozmetickyBO;
    
    private double celkovyCasStravenyRecepcneBO;
    private int pocetVybavovaniRecepcneBO;
    private double celkovyCasStravenyKadernickyBO;
    private int pocetVybavovaniKadernickyBO;
    private double celkovyCasStravenyKozmetickyBO;
    private int pocetVybavovaniKozmetickyBO;
       
    // sumove premenne pouzivane pri replikaciach - statistiky od otvorenia do 17:00
    private double sumaVytazenieRecepcnychBO;
    private double sumaVytazenieKadernicokBO;
    private double sumaVytazenieKozmeticokBO;
    private double sumaCasVSystemeBO;
    private double sumaDlzkaRaduPredRecepcnymiObjednavkaBO;
    private double sumaDlzkaRaduPredRecepcnymiPlatbaBO;
    private double sumaDlzkaRaduPredKadernickamiBO;
    private double sumaDlzkaRaduPredKozmetickamiBO;
    private double sumaCasVRadePredRecepcnymiObjednavkaBO;
    private double sumaCasVRadePredRecepcnymiPlatbaBO;
    private double sumaCasVRadePredKadernickamiBO;
    private double sumaCasVRadePredKozmetickamiCistenieBO;
    private double sumaCasVRadePredKozmetickamiLicenieBO;
    private double sumaCelkovyCasObsluhyRecepcneBO;
    private double sumaCelkovyCasObsluhyKadernickyBO;
    private double sumaCelkovyCasObsluhyKozmetickyBO;
    
    public KozmetickySalon(int paMaximalnySimulacnyCas, int pocetRecepnych, int pocetKadernicok, int pocetKozmeticok, int paPocetReplikacii) {
        super(paMaximalnySimulacnyCas, paPocetReplikacii);
        this.celkovyPocetRecepcnych = pocetRecepnych;
        this.celkovyPocetKadernicok = pocetKadernicok;
        this.celkovyPocetKozmeticok = pocetKozmeticok;
    }

    @Override
    public void predReplikaciou() {
        // resetovanie vsetkych statistickych premennych pred jednou replikaciou
        if (this.pocetReplikacii > 1) {
            this.spojitostSimulacieMedzera = Double.MAX_VALUE;
        } else {
            this.spojitostSimulacieMedzera = 1;
        }
        
        this.kolkoLudiPrisloDoSystemu = 0;
        this.kolkoLudiOdisloZoSystemu = 0;
        this.celkovyCasVSyseteme = 0;
        
        this.celkovyCasCakaniaVRadePredRecepcnymiObjednavka = 0;
        this.pocetCakaniVRadePredRecepcnymiObjednavka = 0;
    
        this.celkovyCasCakaniaVRadePredRecepcnymiPlatba = 0;
        this.pocetCakaniVRadePredRecepcnymiPlatba = 0;
    
        this.celkovyCasCakaniaVRadePredKadernickami = 0;
        this.pocetCakaniVRadePredKadernickami = 0;
    
        this.celkovyCasCakaniaVRadePredKozmetickamiCistenie = 0;
        this.pocetCakaniVRadePredKozmetickamiCistenie = 0;
    
        this.celkovyCasCakaniaVRadePredKozmetickamiLicenie = 0;
        this.pocetCakaniVRadePredKozmetickamiLicenie = 0;
    
        this.naposledyZmenenaDlzkaRaduRecepcneObjednavka = 0;
        this.naposledyZmenenaDlzkaRaduRecepcnePlatba = 0;
        this.naposledyZmenenaDlzkaRaduKadernicky = 0;
        this.naposledyZmenenaDlzkaRaduKozmeticky = 0;
    
        this.vazenaPocetnostRadRecepcneObjednavky = 0;
        this.vazenaPocetnostRadRecepcnePlatba = 0;
        this.vazenaPocetnostRadKadernicky = 0;
        this.vazenaPocetnostRadKozmeticky = 0;
        
        this.celkovyCasStravenyRecepcne = 0;
        this.pocetVybavovaniRecepcne = 0;
        this.celkovyCasStravenyKadernicky = 0;
        this.pocetVybavovaniKadernicky = 0;
        this.celkovyCasStravenyKozmeticky = 0;
        this.pocetVybavovaniKozmeticky = 0;
        
        this.kolkoLudiBoloPoslanychDomov = 0;
        
        // resetovanie statistickych premennych - pocitaju sa len do 17:00
        this.kolkoLudiPrisloDoSystemuBO = 0;
        this.kolkoLudiOdisloZoSystemuBO = 0;
        this.celkovyCasVSysetemeBO = 0;
    
        this.celkovyCasCakaniaVRadePredRecepcnymiObjednavkaBO = 0;
        this.pocetCakaniVRadePredRecepcnymiObjednavkaBO = 0;
    
        this.celkovyCasCakaniaVRadePredRecepcnymiPlatbaBO = 0;
        this.pocetCakaniVRadePredRecepcnymiPlatbaBO = 0;
    
        this.celkovyCasCakaniaVRadePredKadernickamiBO = 0;
        this.pocetCakaniVRadePredKadernickamiBO = 0;
    
        this.celkovyCasCakaniaVRadePredKozmetickamiCistenieBO = 0;
        this.pocetCakaniVRadePredKozmetickamiCistenieBO = 0;
    
        this.celkovyCasCakaniaVRadePredKozmetickamiLicenieBO = 0;
        this.pocetCakaniVRadePredKozmetickamiLicenieBO = 0;
    
        this.vazenaPocetnostRadRecepcneObjednavkyBO = 0;
        this.vazenaPocetnostRadRecepcnePlatbaBO = 0;
        this.vazenaPocetnostRadKadernickyBO = 0;
        this.vazenaPocetnostRadKozmetickyBO = 0;

        this.celkovyCasStravenyRecepcneBO = 0;
        this.pocetVybavovaniRecepcneBO = 0;
        this.celkovyCasStravenyKadernickyBO = 0;
        this.pocetVybavovaniKadernickyBO = 0;
        this.celkovyCasStravenyKozmetickyBO = 0;
        this.pocetVybavovaniKozmetickyBO = 0;
            
        // vytvorenie zoznamov volneho personalu
        this.volneRecepcne = new ArrayList<>();
        this.volneKadernicky = new ArrayList<>();
        this.volneKozmeticky = new ArrayList<>();
        
        // vytvorenie zoznamov obsadeneho personalu
        this.pracujuceRecepcne = new ArrayList<>();
        this.pracujuceKadernicky = new ArrayList<>();
        this.pracujuceKozmeticky = new ArrayList<>();
        
        // vlozenie volnych recepcnych
        for (int i = 1; i <= this.celkovyPocetRecepcnych; i++) {
            Recepcna nova = new Recepcna(i);
            this.volneRecepcne.add(nova);
        }       
        // vlozenie volnych kadernicok
        for (int i = 1; i <= this.celkovyPocetKadernicok; i++) {
            Kadernicka nova = new Kadernicka(i);
            this.volneKadernicky.add(nova);
        }
        // vlozenie volnych kozmeticok
        for (int i = 1; i <= this.celkovyPocetKozmeticok; i++) {
            Kozmeticka nova = new Kozmeticka(i);
            this.volneKozmeticky.add(nova);
        }
        
        // vytvorenie radov pre zakaznikov pred recepcnymi, kadernickami a kozmetickami
        this.cakajuciPredRecepcnymiObjednavka = new LinkedList<>();
        this.cakajuciPredRecepcnymiZaplatenie = new LinkedList<>();
        this.cakajuciPredKadernickami = new LinkedList<>();
        this.cakajuciPredKozmetickami = new LinkedList<>();
        
        // nastav simulacny cas
        this.simulacnyCas = 0;
        
        // inicializacia generatorov
        
        this.generatorNasad = new Random();
        // generator hodnoty, podla ktorej zakaznikovi vyplnim jeho zaujem o sluzby
        this.generovanieZaujmuOSluzby = new Random(this.generatorNasad.nextInt());
        // generator hodnoty, podla ktorej zakaznikovi vyplnim jeho zaujem o cistenie pleti
        this.generovanieZaujmuOCisteniePleti = new Random(this.generatorNasad.nextInt());
        // generator hodnoty, ktora predstavuje cas dalsiho prichodu zakaznika
        this.generovaniePrichodov = new Exponencialne((double) 1/450);
        // generator hodnoty, ktora predstavuje cas vybavovania objednavky
        this.generovanieVybavovaniaObjednavky = new RovnomerneSpojite(80, 320);
        // generator hodnoty, ktora predstavuje cas cistenia pleti
        this.generovanieCisteniaPleti = new Trojuholnikove(360, 900, 540);
        // generator hodnoty, ktora predstavuje cas vybavovania platby
        this.generovanieVybavovaniaPlatby = new RovnomerneSpojite(130, 230);
        // generator hodnoty, podla ktorej zakaznikovi vyberiem typ ucesu
        this.pravdepodobnostVyberuUcesu = new Random(this.generatorNasad.nextInt());
        // generator hodnoty, podla ktorej zakaznikovi vyberiem typ licenia
        this.pravdepodobnostTypuLicenia = new Random(this.generatorNasad.nextInt());
        // generator hodnoty, ktora predstavuje cas robenia jednoducheho ucesu
        this.jednoduchyUces = new Random(this.generatorNasad.nextInt());
        // generator hodnoty, ktora predstavuje cas robenia zloziteho ucesu
        PrvokEmpiricke p1 = new PrvokEmpiricke(30, 60, 0.4);
        PrvokEmpiricke p2 = new PrvokEmpiricke(61, 120, 0.6);
        ArrayList<PrvokEmpiricke> l = new ArrayList<>();
        l.add(p1);
        l.add(p2);
        this.zlozityUces = new DiskretneEmpiricke(l);
        // generator hodnoty, ktora predstavuje cas robenia svadobneho ucesu
        PrvokEmpiricke o1 = new PrvokEmpiricke(50, 60, 0.2);
        PrvokEmpiricke o2 = new PrvokEmpiricke(61, 100, 0.3);
        PrvokEmpiricke o3 = new PrvokEmpiricke(101, 150, 0.5);
        ArrayList<PrvokEmpiricke> m = new ArrayList<>();
        m.add(o1);
        m.add(o2);
        m.add(o3);
        this.svadobnyUces = new DiskretneEmpiricke(m);
        // generator hodnoty, ktora predstavuje cas jednoducheho licenia
        this.jednoducheLicenie = new Random(this.generatorNasad.nextInt());
        // generator hodnoty, ktora predstavuje cas zloziteho licenia
        this.zloziteLicenie = new Random(this.generatorNasad.nextInt());
        
        // pridanie prvej udalosti do zoznamu udalosti, start simulacie
        double kedyPridePrvy = this.dajCasDalsiehoPrichoduZakaznika();
        Zakaznik prvy = new Zakaznik(0 + kedyPridePrvy, this);
        PrichodDoSystemu novaUdalost = new PrichodDoSystemu(0 + kedyPridePrvy, this, prvy);
        this.pridajNovuUdalost(novaUdalost);
        // pridanie udalosti, ktora zabezpeci spojitost simulacie
        Spojitost spojitostSim = new Spojitost(50, this, null, null);
        this.pridajNovuUdalost(spojitostSim);
        // pridanie udalosti, ktora odstrani o 17:00 vsetkych zakaznikov z radu pred recepcnymi objednavka
        OdoberZakaznikovRadRecepcneObjednavka odober = new OdoberZakaznikovRadRecepcneObjednavka(28800, this, null, null);
        this.pridajNovuUdalost(odober);
    }

    @Override
    public void poReplikacii() {
        // celkovy pocet replikacii
        this.pocetVykonanychReplikacii = this.pocetVykonanychReplikacii + 1;
        
        // celkova suma priemernej vytazenosti recepcnych
        double priem = 0;
        for (Recepcna akt : this.zoznamVsetkychRecepcnych()) {
            priem = priem + akt.vypocitajVytazenie(this.simulacnyCas);
        }
        priem = priem / this.zoznamVsetkychRecepcnych().size();
        this.sumaVytazenieRecepcnych = this.sumaVytazenieRecepcnych + priem;
        
        priem = 0;
        for (Recepcna akt : this.zoznamVsetkychRecepcnych()) {
            priem = priem + akt.vypocitajVytazenieDo17();
        }
        priem = priem / this.zoznamVsetkychRecepcnych().size();
        
        this.sumaVytazenieRecepcnychBO = this.sumaVytazenieRecepcnychBO + priem;
        
        // celkova suma priemernej vytazenosti kadernicok
        priem = 0;
        for (Kadernicka akt : this.zoznamVsetkychKadernicok()) {
            priem = priem + akt.vypocitajVytazenie(this.simulacnyCas);           
        }
        priem = priem / this.zoznamVsetkychKadernicok().size();
        this.sumaVytazenieKadernicok = this.sumaVytazenieKadernicok + priem;
        
        priem = 0;
        for (Kadernicka akt : this.zoznamVsetkychKadernicok()) {
            priem = priem + akt.vypocitajVytazenieDo17();
        }
        priem = priem / this.zoznamVsetkychKadernicok().size();              
        this.sumaVytazenieKadernicokBO = this.sumaVytazenieKadernicokBO + priem;
        
        // celkova suma priemernej vytazenosti kozmeticok
        priem = 0;
        for (Kozmeticka akt : this.zoznamVsetkychKozmeticok()) {
            priem = priem + akt.vypocitajVytazenie(this.simulacnyCas);
        }
        priem = priem / this.zoznamVsetkychKozmeticok().size();
        this.sumaVytazenieKozmeticok = this.sumaVytazenieKozmeticok + priem;
        
        priem = 0;
        for (Kozmeticka akt : this.zoznamVsetkychKozmeticok()) {
            priem = priem + akt.vypocitajVytazenieDo17();
        }
        priem = priem / this.zoznamVsetkychKozmeticok().size();        
        
        this.sumaVytazenieKozmeticokBO = this.sumaVytazenieKozmeticokBO + priem;
        
        // suma priemerneho casu zakaznikov v systeme
        this.sumaCasVSysteme = this.sumaCasVSysteme + (this.celkovyCasVSyseteme / this.kolkoLudiOdisloZoSystemu);
        this.sumaCasVSystemeBO = this.sumaCasVSystemeBO + (this.celkovyCasVSysetemeBO / this.kolkoLudiOdisloZoSystemuBO);
        
        // suma priemerneho casu v rade pred recepcnymi - objednavka
        this.sumaCasVRadePredRecepcnymiObjednavka = this.sumaCasVRadePredRecepcnymiObjednavka + (this.celkovyCasCakaniaVRadePredRecepcnymiObjednavka / this.pocetCakaniVRadePredRecepcnymiObjednavka);
        this.sumaCasVRadePredRecepcnymiObjednavkaBO = this.sumaCasVRadePredRecepcnymiObjednavkaBO + (this.celkovyCasCakaniaVRadePredRecepcnymiObjednavkaBO / this.pocetCakaniVRadePredRecepcnymiObjednavkaBO);
        
        // suma priemerneho casu v rade pred recepcnymi - platba
        this.sumaCasVRadePredRecepcnymiPlatba = this.sumaCasVRadePredRecepcnymiPlatba + (this.celkovyCasCakaniaVRadePredRecepcnymiPlatba / this.pocetCakaniVRadePredRecepcnymiPlatba);
        this.sumaCasVRadePredRecepcnymiPlatbaBO = this.sumaCasVRadePredRecepcnymiPlatbaBO + (this.celkovyCasCakaniaVRadePredRecepcnymiPlatbaBO / this.pocetCakaniVRadePredRecepcnymiPlatbaBO);
        
        // suma priemerneho casu v rade pred kadernickami
        this.sumaCasVRadePredKadernickami = this.sumaCasVRadePredKadernickami + (this.celkovyCasCakaniaVRadePredKadernickami / this.pocetCakaniVRadePredKadernickami);
        this.sumaCasVRadePredKadernickamiBO = this.sumaCasVRadePredKadernickamiBO + (this.celkovyCasCakaniaVRadePredKadernickamiBO / this.pocetCakaniVRadePredKadernickamiBO);
        
        // suma priemerneho casu v rade pred kozetickami - cistenie
        this.sumaCasVRadePredKozmetickamiCistenie = this.sumaCasVRadePredKozmetickamiCistenie + (this.celkovyCasCakaniaVRadePredKozmetickamiCistenie / this.pocetCakaniVRadePredKozmetickamiCistenie);
        this.sumaCasVRadePredKozmetickamiCistenieBO = this.sumaCasVRadePredKozmetickamiCistenieBO + (this.celkovyCasCakaniaVRadePredKozmetickamiCistenieBO / this.pocetCakaniVRadePredKozmetickamiCistenieBO);
        
        // suma priemerneho casu v rade pred kozetickami - licenie
        this.sumaCasVRadePredKozmetickamiLicenie = this.sumaCasVRadePredKozmetickamiLicenie + (this.celkovyCasCakaniaVRadePredKozmetickamiLicenie / this.pocetCakaniVRadePredKozmetickamiLicenie);
        this.sumaCasVRadePredKozmetickamiLicenieBO = this.sumaCasVRadePredKozmetickamiLicenieBO + (this.celkovyCasCakaniaVRadePredKozmetickamiLicenieBO / this.pocetCakaniVRadePredKozmetickamiLicenieBO);
    
        // suma priemernej dlzky radu pred recepcnymi - objednavka
        this.sumaDlzkaRaduPredRecepcnymiObjednavka = this.sumaDlzkaRaduPredRecepcnymiObjednavka + (this.vazenaPocetnostRadRecepcneObjednavky / this.simulacnyCas);
        this.sumaDlzkaRaduPredRecepcnymiObjednavkaBO = this.sumaDlzkaRaduPredRecepcnymiObjednavkaBO + (this.vazenaPocetnostRadRecepcneObjednavkyBO / (this.prelomovyCas));
        
        // suma priemernej dlzky radu pred recepcnymi - platba
        this.sumaDlzkaRaduPredRecepcnymiPlatba = this.sumaDlzkaRaduPredRecepcnymiPlatba + (this.vazenaPocetnostRadRecepcnePlatba / this.simulacnyCas);
        this.sumaDlzkaRaduPredRecepcnymiPlatbaBO = this.sumaDlzkaRaduPredRecepcnymiPlatbaBO + (this.vazenaPocetnostRadRecepcnePlatbaBO / (this.prelomovyCas));
        
        // suma priemernej dlzky radu pred kadernickami
        this.sumaDlzkaRaduPredKadernickami = this.sumaDlzkaRaduPredKadernickami + (this.vazenaPocetnostRadKadernicky / this.simulacnyCas);
        this.sumaDlzkaRaduPredKadernickamiBO = this.sumaDlzkaRaduPredKadernickamiBO + (this.vazenaPocetnostRadKadernickyBO / (this.prelomovyCas));
        
        // suma priemernej dlzky radu pred kozmetickami
        this.sumaDlzkaRaduPredKozmetickami = this.sumaDlzkaRaduPredKozmetickami + (this.vazenaPocetnostRadKozmeticky / this.simulacnyCas);
        this.sumaDlzkaRaduPredKozmetickamiBO = this.sumaDlzkaRaduPredKozmetickamiBO + (this.vazenaPocetnostRadKozmetickyBO / (this.prelomovyCas));
        
        // suma celkoveho casu obsluhy recepcnymi
        this.sumaCelkovyCasObsluhyRecepcne = this.sumaCelkovyCasObsluhyRecepcne + (this.celkovyCasStravenyRecepcne / this.pocetVybavovaniRecepcne);
        this.sumaCelkovyCasObsluhyRecepcneBO = this.sumaCelkovyCasObsluhyRecepcneBO + (this.celkovyCasStravenyRecepcneBO / this.pocetVybavovaniRecepcneBO);
        
        // suma celkoveho casu obsluhy kadernickami
        this.sumaCelkovyCasObsluhyKadernicky = this.sumaCelkovyCasObsluhyKadernicky + (this.celkovyCasStravenyKadernicky / this.pocetVybavovaniKadernicky);
        this.sumaCelkovyCasObsluhyKadernickyBO = this.sumaCelkovyCasObsluhyKadernickyBO + (this.celkovyCasStravenyKadernickyBO / this.pocetVybavovaniKadernickyBO);
        
        // suma celkoveho casu obsluhy kozmetickami
        this.sumaCelkovyCasObsluhyKozmeticky = this.sumaCelkovyCasObsluhyKozmeticky + (this.celkovyCasStravenyKozmeticky / this.pocetVybavovaniKozmeticky);
        this.sumaCelkovyCasObsluhyKozmetickyBO = this.sumaCelkovyCasObsluhyKozmetickyBO + (this.celkovyCasStravenyKozmetickyBO / this.pocetVybavovaniKozmetickyBO);
    
        // suma celkoveho casu, ktory bol salon otvoreny nadramec pracovnej doby
        this.sumaAkoDlhoBoloOtvoreneNaviac = this.sumaAkoDlhoBoloOtvoreneNaviac + this.akoDlhoBoloOtvorenePoVyprsaniOtvaracichHodin;
    
        // suma ludi, ktori boli vyhodeni z radu pred recepcnymi
        this.sumaKolkoLudiBoloVyhodenych = this.sumaKolkoLudiBoloVyhodenych + this.kolkoLudiBoloPoslanychDomov;
    }
    
    
    // Recepcne - zaciatok
    // vyberie recepcnu zo zoznamu volnych recepcnych, ktora pocas dna pracovala najmenej
    public Recepcna ktoraRecepcnaBudeObsluhovat() {
        if (!this.volneRecepcne.isEmpty()) {
            double najmenej = Double.MAX_VALUE;
            Recepcna ktoraMaNajmenej = null;
            for (Recepcna aktualna : this.volneRecepcne) {
                if (aktualna.dajCasCelkovejObsluhy() < najmenej) {
                    najmenej = aktualna.dajCasCelkovejObsluhy();
                    ktoraMaNajmenej = aktualna;
                }
            }
            ktoraMaNajmenej.nastavPracovanie(true);
            this.volneRecepcne.remove(ktoraMaNajmenej);
            this.pracujuceRecepcne.add(ktoraMaNajmenej);
            return ktoraMaNajmenej;
        } else {
            return null;
        }
    }
    
    // vlozi recepcnu do zoznamu volnych recepcnych
    public void vlozRecepcnuNaspat(Recepcna paRecepcna) {
        paRecepcna.nastavPracovanie(false);
        this.pracujuceRecepcne.remove(paRecepcna);
        this.volneRecepcne.add(paRecepcna);
    }
    
    public void vlozZakaznikaDoRaduObjednavka(Zakaznik paZakazik) {
        this.cakajuciPredRecepcnymiObjednavka.add(paZakazik);
    }
    
    public int dajPocetLudiVRadePredRecepcnymi() {
        return this.cakajuciPredRecepcnymiObjednavka.size();
    }
    
    public int dajPocetLudiVRadePredRecepcnymiPlatba() {
        return this.cakajuciPredRecepcnymiZaplatenie.size();
    }
    
    public Zakaznik dajPrvehoZakaznikaZRaduPredRecepcnymi() {
        return this.cakajuciPredRecepcnymiObjednavka.remove(0);
    }
    
    public Zakaznik dajPrvehoZakaznikaZRaduPredRecepcnymiPlatba() {
        return this.cakajuciPredRecepcnymiZaplatenie.remove(0);
    }
    
    public void postavitZakaznikaDoRaduPredRecepcnymiObjednavka(Zakaznik paZakaznik) {
        this.cakajuciPredRecepcnymiObjednavka.add(paZakaznik);
    }
    
    public void postavitZakaznikaDoRaduPredRecepcnymiPlatba(Zakaznik paZakaznik) {
        this.cakajuciPredRecepcnymiZaplatenie.add(paZakaznik);
    }
    
    public ArrayList<Recepcna> zoznamVsetkychRecepcnych() {      
        ArrayList<Recepcna> vsetky = new ArrayList<>();
        vsetky.addAll(this.volneRecepcne);
        vsetky.addAll(this.pracujuceRecepcne);
        Collections.sort(vsetky);
        return vsetky;
    }
    
    public void odstranVsetkychZakaznikovRadRecepcneObjednavka() {
        this.kolkoLudiPrisloDoSystemu = this.kolkoLudiPrisloDoSystemu - this.cakajuciPredRecepcnymiObjednavka.size();
        this.cakajuciPredRecepcnymiObjednavka.clear();
    }          
    // Recepcne - koniec
    
    
    // Kadernicky - zaciatok
    // vyberie kadernicku zo zoznamu volnych kadernicok, ktora pocas dna pracovala najmenej
    public Kadernicka ktoraKadernickaBudeObsluhovat() {
        if (!this.volneKadernicky.isEmpty()) {
            double najmenej = Double.MAX_VALUE;
            Kadernicka ktoraMaNajmenej = null;
            for (Kadernicka aktualna : this.volneKadernicky) {
                if (aktualna.dajCasCelkovejObsluhy() < najmenej) {
                    najmenej = aktualna.dajCasCelkovejObsluhy();
                    ktoraMaNajmenej = aktualna;
                }
            }
            ktoraMaNajmenej.nastavPracovanie(true);
            this.volneKadernicky.remove(ktoraMaNajmenej);
            this.pracujuceKadernicky.add(ktoraMaNajmenej);
            return ktoraMaNajmenej;
        } else {
            return null;
        }
    }
    
    public void vlozKadernickuNaspat(Kadernicka paKadernicka) {
        paKadernicka.nastavPracovanie(false);
        this.pracujuceKadernicky.remove(paKadernicka);
        this.volneKadernicky.add(paKadernicka);
    }
    
    public void vlozZakaznikaDoRaduKadernicky(Zakaznik paZakazik) {
        this.cakajuciPredKadernickami.add(paZakazik);
    }
    
    public int dajPocetLudiVRadePredKadernickai() {
        return this.cakajuciPredKadernickami.size();
    }
    
    public Zakaznik dajPrvehoZakaznikaZRaduPredKadernickami() {
        return this.cakajuciPredKadernickami.remove(0);
    }
    
    public void postavitZakaznikaDoRaduPredKadernickami(Zakaznik paZakaznik) {
        this.cakajuciPredKadernickami.add(paZakaznik);
    }
    
    public ArrayList<Kadernicka> zoznamVsetkychKadernicok() {      
        ArrayList<Kadernicka> vsetky = new ArrayList<>();
        vsetky.addAll(this.volneKadernicky);
        vsetky.addAll(this.pracujuceKadernicky);
        Collections.sort(vsetky);
        return vsetky;
    }   
    // Kadernicky - koniec
    
    // Kozmeticky - zaciatok
    // vyberie kozmeticku zo zoznamu volnych kozmeticok, ktora pocas dna pracovala najmenej
    public Kozmeticka ktoraKozmetickaBudeObsluhovat() {
        if (!this.volneKozmeticky.isEmpty()) {
            double najmenej = Double.MAX_VALUE;
            Kozmeticka ktoraMaNajmenej = null;
            for (Kozmeticka aktualna : this.volneKozmeticky) {
                if (aktualna.dajCasCelkovejObsluhy() < najmenej) {
                    najmenej = aktualna.dajCasCelkovejObsluhy();
                    ktoraMaNajmenej = aktualna;
                }
            }
            ktoraMaNajmenej.nastavPracovanie(true);
            this.volneKozmeticky.remove(ktoraMaNajmenej);
            this.pracujuceKozmeticky.add(ktoraMaNajmenej);
            return ktoraMaNajmenej;
        } else {
            return null;
        }
    }   
    
    public void vlozKozmetickuNaspat(Kozmeticka paKozmeticka) {
        paKozmeticka.nastavPracovanie(false);
        this.pracujuceKozmeticky.remove(paKozmeticka);
        this.volneKozmeticky.add(paKozmeticka);
    }
    
    public void vlozZakaznikaDoRaduKozmeticky(Zakaznik paZakazik) {
        this.cakajuciPredKozmetickami.add(paZakazik);
    }
    
    public int dajPocetLudiVRadePredKozmetickami() {
        return this.cakajuciPredKozmetickami.size();
    }
    
    public Zakaznik dajPrvehoZakaznikaZRaduPredKozmetickami() {
        return this.cakajuciPredKozmetickami.remove(0);
    }
    
    public void postavitZakaznikaDoRaduPredKozmetickami(Zakaznik paZakaznik) {
        this.cakajuciPredKozmetickami.add(paZakaznik);
    }
    
    public ArrayList<Kozmeticka> zoznamVsetkychKozmeticok() {      
        ArrayList<Kozmeticka> vsetky = new ArrayList<>();
        vsetky.addAll(this.volneKozmeticky);
        vsetky.addAll(this.pracujuceKozmeticky);
        Collections.sort(vsetky);
        return vsetky;
    }   
    // Kozmeticky - koniec
    
    // hodnota z generatora
    public double dajCasTrvaniaVybaveniaObjednavky() {
        return this.generovanieVybavovaniaObjednavky.dajVygenerovanuHodnotu();
    }
    
    // hodnota z generatora
    public double dajCasTrvaniaCisteniaPleti() {
        return this.generovanieCisteniaPleti.dajVygenerovanuHodnotu();
    }
    
    // hodnota z generatora
    public double dajCasTrvaniaVybaveniaPlatby() {
        return this.generovanieVybavovaniaPlatby.dajVygenerovanuHodnotu();
    }
    
    // hodnota z generatora
    public double dajPravdepodobnostZaujmuOSluzby() {
        return this.generovanieZaujmuOSluzby.nextDouble();
    }
    
    // hodnota z generatora
    public double dajPravdepodobnostZaujmuOCisteniePleti() {
        return this.generovanieZaujmuOCisteniePleti.nextDouble();
    }
    
    // hodnota z generatora
    public double dajCasDalsiehoPrichoduZakaznika() {
        return this.generovaniePrichodov.dajVygenerovanuHodnotu();
    }
    
    // hodnota z generatora
    public double dajPravdepodobnostVyberuUcesu() {
        return this.pravdepodobnostVyberuUcesu.nextDouble();
    }
    
    // hodnota z generatora
    public double dajPravdepodobnostTypuLicenia() {
        return this.pravdepodobnostTypuLicenia.nextDouble();
    }
    
    // hodnota z generatora
    public double dajCasJednoduchehoUcesu() {
        return (this.jednoduchyUces.nextInt(21) + 10) * 60;
    }
    
    // hodnota z generatora
    public double dajCasZlozitehoUcesu() {
        return this.zlozityUces.dajVygenerovanuHodnotu() * 60;
    }
    
    // hodnota z generatora
    public double dajCasSvadobnehoUcesu() {
        return this.svadobnyUces.dajVygenerovanuHodnotu() * 60;
    }
    
    // hodnota z generatora
    public double dajCasJednoduchehoLicenia() {
        return (this.jednoducheLicenie.nextInt(16) + 10) * 60;
    }
    
    // hodnota z generatora
    public double dajCasZlozitehoLicenia() {
        return (this.zloziteLicenie.nextInt(81) + 20) * 60;
    }
    
    // metody na zmeny hodnot v statistikach   
    public void zvysPocetLudiKtoriPrisliDoSystemu() {
        this.kolkoLudiPrisloDoSystemu = this.kolkoLudiPrisloDoSystemu + 1;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.kolkoLudiPrisloDoSystemuBO = this.kolkoLudiPrisloDoSystemuBO + 1;
        }
    }
    
    public int dajPocetLudiKtoriPrisliDoSystemu() {
        return this.kolkoLudiPrisloDoSystemu;
    }
    
    public void zvysPocetLudiKtoriOdisliZoSystemu() {
        this.kolkoLudiOdisloZoSystemu = this.kolkoLudiOdisloZoSystemu + 1;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.kolkoLudiOdisloZoSystemuBO = this.kolkoLudiOdisloZoSystemuBO + 1;
        }
    }
    
    public int dajPocetLudiKtoriOdisliZoSystemu() {
        return this.kolkoLudiOdisloZoSystemu;
    }
    
    public int dajPocetLudiVRadeRecepcne() {
        return this.cakajuciPredRecepcnymiObjednavka.size();
    }
    
    public int dajPocetLudiVRadeKadernicky() {
        return this.cakajuciPredKadernickami.size();
    }
    
    public int dajPocetLudiVRadeKozmeticky() {
        return this.cakajuciPredKozmetickami.size();
    }
    
    // formatuje aktualny simulacny cas do formatu hh:mm:ss
    public String dajFormatovanySimulacnyCas() {
        int kolkoJeHodinNaZaciatku = 9;
        double kopiaSimulacnehoCasu = this.simulacnyCas;
        int hodiny = (int) kopiaSimulacnehoCasu / 3600;
        kopiaSimulacnehoCasu = kopiaSimulacnehoCasu - (hodiny * 3600);
        int minuty = (int) kopiaSimulacnehoCasu / 60;
        kopiaSimulacnehoCasu = kopiaSimulacnehoCasu - (minuty * 60);
        int sekundy = (int) kopiaSimulacnehoCasu;
        
        String hod;
        if (kolkoJeHodinNaZaciatku + hodiny >= 10) {
            hod = String.valueOf((kolkoJeHodinNaZaciatku + hodiny) % 24);
        } else {
            hod = "0" + String.valueOf((kolkoJeHodinNaZaciatku + hodiny) % 24);
        }
        
        String min;
        if (minuty >= 10) {
            min = String.valueOf(minuty);
        } else {
            min = "0" + String.valueOf(minuty);
        }
        
        String sek;
        if (sekundy >= 10) {
            sek = String.valueOf(sekundy);
        } else {
            sek = "0" + String.valueOf(sekundy);
        }
        
        return hod + ":" + min + ":" + sek;
    }
    
    // formatuje akykolvek cas zadany v sekundach do formatu hh:mm:ss - vyuzivam pri vypise statistik v grafickom okne
    public String dajFormatovanySimulacnyCas(double paSekundy) {
        double kopiaSimulacnehoCasu = paSekundy;
        int hodiny = (int) kopiaSimulacnehoCasu / 3600;
        kopiaSimulacnehoCasu = kopiaSimulacnehoCasu - (hodiny * 3600);
        int minuty = (int) kopiaSimulacnehoCasu / 60;
        kopiaSimulacnehoCasu = kopiaSimulacnehoCasu - (minuty * 60);
        int sekundy = (int) kopiaSimulacnehoCasu;
        
        String hod;
        if (hodiny >= 10) {
            hod = String.valueOf(hodiny % 24);
        } else {
            hod = "0" + String.valueOf(hodiny % 24);
        }
        
        String min;
        if (minuty >= 10) {
            min = String.valueOf(minuty);
        } else {
            min = "0" + String.valueOf(minuty);
        }
        
        String sek;
        if (sekundy >= 10) {
            sek = String.valueOf(sekundy);
        } else {
            sek = "0" + String.valueOf(sekundy);
        }
        
        return hod + ":" + min + ":" + sek;
    }
    
    // Statistiky
    // recepcne - objednavka
    public void pripocitajCasCakaniaVRadePredRecepcnymiObjednavka(double cas) {
        this.celkovyCasCakaniaVRadePredRecepcnymiObjednavka = this.celkovyCasCakaniaVRadePredRecepcnymiObjednavka + cas;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.celkovyCasCakaniaVRadePredRecepcnymiObjednavkaBO = this.celkovyCasCakaniaVRadePredRecepcnymiObjednavkaBO + cas;
        }
    }
    
    public void pripocitajJednoCakanieVRadePredRecepcnymi() {
        this.pocetCakaniVRadePredRecepcnymiObjednavka = this.pocetCakaniVRadePredRecepcnymiObjednavka + 1;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.pocetCakaniVRadePredRecepcnymiObjednavkaBO = this.pocetCakaniVRadePredRecepcnymiObjednavkaBO + 1;
        }
    }
    
    public double dajCasCakaniaVRadePredRecepcnymiObjednavka() {
        return this.celkovyCasCakaniaVRadePredRecepcnymiObjednavka;
    }
    
    public double dajPocetCakaniVRadePredRecepcnymiObjednavka() {
        return this.pocetCakaniVRadePredRecepcnymiObjednavka;
    }
    
    public double dajPriemernyCasCakaniaVRadePredRecepcnymiObjednavka() {
        return this.celkovyCasCakaniaVRadePredRecepcnymiObjednavka / this.pocetCakaniVRadePredRecepcnymiObjednavka;
    }
    
    
    // recepcne - platba   
    public void pripocitajCasCakaniaVRadePredRecepcnymiPlatba(double cas) {
        this.celkovyCasCakaniaVRadePredRecepcnymiPlatba = this.celkovyCasCakaniaVRadePredRecepcnymiPlatba + cas;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.celkovyCasCakaniaVRadePredRecepcnymiPlatbaBO = this.celkovyCasCakaniaVRadePredRecepcnymiPlatbaBO + cas;
        }
    }
    
    public void pripocitajJednoCakanieVRadePredRecepcnymiPlatba() {
        this.pocetCakaniVRadePredRecepcnymiPlatba = this.pocetCakaniVRadePredRecepcnymiPlatba + 1;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.pocetCakaniVRadePredRecepcnymiPlatbaBO = this.pocetCakaniVRadePredRecepcnymiPlatbaBO + 1;
        }
    }
    
    public double dajCasCakaniaVRadePredRecepcnymiPlatba() {
        return this.celkovyCasCakaniaVRadePredRecepcnymiPlatba;
    }
    
    public double dajPocetCakaniVRadePredRecepcnymiPlatba() {
        return this.pocetCakaniVRadePredRecepcnymiPlatba;
    }
    
    public double dajPriemernyCasCakaniaVRadePredRecepcnymiPlatba() {
        return this.celkovyCasCakaniaVRadePredRecepcnymiPlatba / this.pocetCakaniVRadePredRecepcnymiPlatba;
    }
    
    // kadernicky 
    public void pripocitajCasCakaniaVRadePredKadernickami(double cas) {
        this.celkovyCasCakaniaVRadePredKadernickami = this.celkovyCasCakaniaVRadePredKadernickami + cas;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.celkovyCasCakaniaVRadePredKadernickamiBO = this.celkovyCasCakaniaVRadePredKadernickamiBO + cas;
        }
    }
    
    public void pripocitajJednoCakanieVRadePredKadernickami() {
        this.pocetCakaniVRadePredKadernickami = this.pocetCakaniVRadePredKadernickami + 1;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.pocetCakaniVRadePredKadernickamiBO = this.pocetCakaniVRadePredKadernickamiBO + 1;
        }
    }
         
    public double dajPriemernyCasCakaniaVRadePredKadernickmi() {
        return this.celkovyCasCakaniaVRadePredKadernickami / this.pocetCakaniVRadePredKadernickami;
    }
    
    // kozmeticky - cistenie
    public void pripocitajCasCakaniaVRadePredKozmetickamiCistenie(double cas) {
        this.celkovyCasCakaniaVRadePredKozmetickamiCistenie = this.celkovyCasCakaniaVRadePredKozmetickamiCistenie + cas;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.celkovyCasCakaniaVRadePredKozmetickamiCistenieBO = this.celkovyCasCakaniaVRadePredKozmetickamiCistenieBO + cas;
        }
    }
    
    public void pripocitajJednoCakanieVRadePredKozmetickamiCistenie() {
        this.pocetCakaniVRadePredKozmetickamiCistenie = this.pocetCakaniVRadePredKozmetickamiCistenie + 1;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.pocetCakaniVRadePredKozmetickamiCistenieBO = this.pocetCakaniVRadePredKozmetickamiCistenieBO + 1;
        }
    }
         
    public double dajPriemernyCasCakaniaVRadePredKozmetickamiCistenie() {
        return this.celkovyCasCakaniaVRadePredKozmetickamiCistenie / this.pocetCakaniVRadePredKozmetickamiCistenie;
    }
    
    // kozmeticky - licenie
    public void pripocitajCasCakaniaVRadePredKozmetickamiLicenie(double cas) {
        this.celkovyCasCakaniaVRadePredKozmetickamiLicenie = this.celkovyCasCakaniaVRadePredKozmetickamiLicenie + cas;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.celkovyCasCakaniaVRadePredKozmetickamiLicenieBO = this.celkovyCasCakaniaVRadePredKozmetickamiLicenieBO + cas;
        }
    }
    
    public void pripocitajJednoCakanieVRadePredKozmetickamiLicenie() {
        this.pocetCakaniVRadePredKozmetickamiLicenie = this.pocetCakaniVRadePredKozmetickamiLicenie + 1;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.pocetCakaniVRadePredKozmetickamiLicenieBO = this.pocetCakaniVRadePredKozmetickamiLicenieBO + 1;
        }
    }
         
    public double dajPriemernyCasCakaniaVRadePredKozmetickamiLicenie() {
        return this.celkovyCasCakaniaVRadePredKozmetickamiLicenie / this.pocetCakaniVRadePredKozmetickamiLicenie;
    }
    
    // priemerne casy obsluhy zakaznika na jednotlivych stanovistiach
    public void pripocitajCelkovyCasStravenyRecepcne(double cas) {
        this.celkovyCasStravenyRecepcne = this.celkovyCasStravenyRecepcne + cas;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.celkovyCasStravenyRecepcneBO = this.celkovyCasStravenyRecepcneBO + cas;
        }
    }
    
    public void pripocitajJednuObsluhuRecepcne() {
        this.pocetVybavovaniRecepcne = this.pocetVybavovaniRecepcne + 1;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.pocetVybavovaniRecepcneBO = this.pocetVybavovaniRecepcneBO + 1;
        }
    }
    
    public double dajPriemernyCasStravenyRecepcne() {
        return this.celkovyCasStravenyRecepcne / this.pocetVybavovaniRecepcne;
    }
          
    public void pripocitajCelkovyCasStravenyKadernicky(double cas) {
        this.celkovyCasStravenyKadernicky = this.celkovyCasStravenyKadernicky + cas;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.celkovyCasStravenyKadernickyBO = this.celkovyCasStravenyKadernickyBO + cas;
        }
    }
    
    public void pripocitajJednuObsluhuKadernicky() {
        this.pocetVybavovaniKadernicky = this.pocetVybavovaniKadernicky + 1;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.pocetVybavovaniKadernickyBO = this.pocetVybavovaniKadernickyBO + 1;
        }
    }
    
    public double dajPriemernyCasStravenyKadernicky() {
        return this.celkovyCasStravenyKadernicky / this.pocetVybavovaniKadernicky;
    }
    
    public void pripocitajCelkovyCasStravenyKozmeticky(double cas) {
        this.celkovyCasStravenyKozmeticky = this.celkovyCasStravenyKozmeticky + cas;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.celkovyCasStravenyKozmetickyBO = this.celkovyCasStravenyKozmetickyBO + cas;
        }
    }
    
    public void pripocitajJednuObsluhuKozmeticky() {
        this.pocetVybavovaniKozmeticky = this.pocetVybavovaniKozmeticky + 1;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.pocetVybavovaniKozmetickyBO = this.pocetVybavovaniKozmetickyBO + 1;
        }
    }
    
    public double dajPriemernyCasStravenyKozmeticky() {
        return this.celkovyCasStravenyKozmeticky / this.pocetVybavovaniKozmeticky;
    }
          
    // spojitost simulacie - zmena casu medzi udalostami Spojitost
    public void zmenaMedzerySpojitostSimulacie(double medzera) {
        this.spojitostSimulacieMedzera = medzera;
    }
    
    public double dajMedzeruSpojitostSimulacie() {
        return this.spojitostSimulacieMedzera;
    }
    
    // Statistiky
    // cas poslednej zmeny dlzky radu
    public void nastavNaposledyZmenenaDlzkaRaduRecepcneObjednavka(double cas) {
        this.naposledyZmenenaDlzkaRaduRecepcneObjednavka = cas;
    }
    
    // cas poslednej zmeny dlzky radu
    public double dajNaposledyZmenenaDlzkaRaduRecepcneObjednavka() {
        return this.naposledyZmenenaDlzkaRaduRecepcneObjednavka;
    }
    
    // cas poslednej zmeny dlzky radu
    public void nastavNaposledyZmenenaDlzkaRaduRecepcnePlatba(double cas) {
        this.naposledyZmenenaDlzkaRaduRecepcnePlatba = cas;
    }
    
    // cas poslednej zmeny dlzky radu
    public double dajNaposledyZmenenaDlzkaRaduRecepcnePlatba() {
        return this.naposledyZmenenaDlzkaRaduRecepcnePlatba;
    }
    
    // cas poslednej zmeny dlzky radu
    public void nastavNaposledyZmenenaDlzkaRaduKadernicky(double cas) {
        this.naposledyZmenenaDlzkaRaduKadernicky = cas;
    }
    
    // cas poslednej zmeny dlzky radu
    public double dajNaposledyZmenenaDlzkaRaduKadernicky() {
        return this.naposledyZmenenaDlzkaRaduKadernicky;
    }
    
    // cas poslednej zmeny dlzky radu
    public void nastavNaposledyZmenenaDlzkaRaduKozmeticky(double cas) {
        this.naposledyZmenenaDlzkaRaduKozmeticky = cas;
    }
    
    // cas poslednej zmeny dlzky radu
    public double dajNaposledyZmenenaDlzkaRaduKozmeticky() {
        return this.naposledyZmenenaDlzkaRaduKozmeticky;
    }
    
    // vazene pocetnosti
    public void pripocitajVazenuPocetnostRadRecepcneObjednavka(double kolko) {
        this.vazenaPocetnostRadRecepcneObjednavky = this.vazenaPocetnostRadRecepcneObjednavky + kolko;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.vazenaPocetnostRadRecepcneObjednavkyBO = this.vazenaPocetnostRadRecepcneObjednavkyBO + kolko;
        }
    }
    
    public void pripocitajVazenuPocetnostRadRecepcnePlatba(double kolko) {
        this.vazenaPocetnostRadRecepcnePlatba = this.vazenaPocetnostRadRecepcnePlatba + kolko;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.vazenaPocetnostRadRecepcnePlatbaBO = this.vazenaPocetnostRadRecepcnePlatbaBO + kolko;
        }
    }
    
    public void pripocitajVazenuPocetnostRadKadernicky(double kolko) {
        this.vazenaPocetnostRadKadernicky = this.vazenaPocetnostRadKadernicky + kolko;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.vazenaPocetnostRadKadernickyBO = this.vazenaPocetnostRadKadernickyBO + kolko;
        }
    }
    
    public void pripocitajVazenuPocetnostRadKozmeticky(double kolko) {
        this.vazenaPocetnostRadKozmeticky = this.vazenaPocetnostRadKozmeticky + kolko;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.vazenaPocetnostRadKozmetickyBO = this.vazenaPocetnostRadKozmetickyBO + kolko;
        }
    }
    
    // priemerne dlzky radov
    public double dajPriemernuDlzkuRaduRecepcneObjednavka() {
        return this.vazenaPocetnostRadRecepcneObjednavky / this.simulacnyCas;
    }
    
    public double dajPriemernuDlzkuRaduRecepcnePlatba() {
        return this.vazenaPocetnostRadRecepcnePlatba / this.simulacnyCas;
    }
    
    public double dajPriemernuDlzkuRaduKadernicky() {
        return this.vazenaPocetnostRadKadernicky/ this.simulacnyCas;
    }
    
    public double dajPriemernuDlzkuRaduKozmeticky() {
        return this.vazenaPocetnostRadKozmeticky / this.simulacnyCas;
    }
    
    // priemerny cas zakaznika v systeme
    public void pripocitajCelkovyCasZakaznikovVSysteme(double cas) {
        this.celkovyCasVSyseteme = this.celkovyCasVSyseteme + cas;
        if (this.simulacnyCas < this.prelomovyCas) {
            this.celkovyCasVSysetemeBO = this.celkovyCasVSysetemeBO + cas;
        }
    }
    
    public double dajPriemernyCasStravenyVSysteme() {
        return this.celkovyCasVSyseteme / this.kolkoLudiOdisloZoSystemu;
    }
    
    // priemerne hodnoty po vykonani vsetkych replikacii
    public double dajPriemerneVytazenieRecepcnychPoReplikaciach() {
        return this.sumaVytazenieRecepcnych / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemerneVytazenieKadernicokPoReplikaciach() {
        return this.sumaVytazenieKadernicok / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemerneVytazenieKozmeticokPoReplikaciach() {
        return this.sumaVytazenieKozmeticok / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernyCasZakaznikaVSystemePoReplikaciach() {
        return this.sumaCasVSysteme / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernyCasVRadePredRecepcnymiObjednavkaPoReplikaciach() {
        return this.sumaCasVRadePredRecepcnymiObjednavka / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernyCasVRadePredRecepcnymiPlatbaPoReplikaciach() {
        return this.sumaCasVRadePredRecepcnymiPlatba / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernyCasVRadePredKadernickamiPoReplikaciach() {
        return this.sumaCasVRadePredKadernickami / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernyCasVRadePredKozmetickamiCisteniePoReplikaciach() {
        return this.sumaCasVRadePredKozmetickamiCistenie / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernyCasVRadePredKozmetickamiLiceniePoReplikaciach() {
        return this.sumaCasVRadePredKozmetickamiLicenie / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernuDlzkuRaduPredRecepcnymiObjednavkaPoReplikaciach() {
        return this.sumaDlzkaRaduPredRecepcnymiObjednavka / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernuDlzkuRaduPredRecepcnymiPlatbaPoReplikaciach() {
        return this.sumaDlzkaRaduPredRecepcnymiPlatba / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernuDlzkuRaduPredKadernickamiPoReplikaciach() {
        return this.sumaDlzkaRaduPredKadernickami / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernuDlzkuRaduPredKozmetickamiPoReplikaciach() {
        return this.sumaDlzkaRaduPredKozmetickami / this.pocetVykonanychReplikacii;
    }
    
    public double dajSmerodajnuOdchylkuCasuZakaznikaVSysteme() {
        double sumaNaDruhu = Math.pow(this.dajPriemernyCasZakaznikaVSystemePoReplikaciach(), 2);        
        double prvaZatvorka = sumaNaDruhu / (this.pocetVykonanychReplikacii - 1);
        double druhaZatvorka = this.dajPriemernyCasZakaznikaVSystemePoReplikaciach() / (this.pocetVykonanychReplikacii - 1);
        double zatvorkaNaDruhu = Math.pow(druhaZatvorka, 2);
        return Math.sqrt(prvaZatvorka - zatvorkaNaDruhu);
    }
    
    public double dajLavostrannyInterval90CasuZakaznikaVSysteme() {
        return this.dajPriemernyCasZakaznikaVSystemePoReplikaciach() - (this.dajSmerodajnuOdchylkuCasuZakaznikaVSysteme() * 1.645 / Math.sqrt(this.pocetVykonanychReplikacii));
    }
    
    public double dajPravostrannyInterval90CasuZakaznikaVSysteme() {
        return this.dajPriemernyCasZakaznikaVSystemePoReplikaciach() + (this.dajSmerodajnuOdchylkuCasuZakaznikaVSysteme() * 1.645 / Math.sqrt(this.pocetVykonanychReplikacii));
    }
    
    public double dajPriemernuDobuObsluhyRecepcnymiPoReplikaciach() {
        return this.sumaCelkovyCasObsluhyRecepcne / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernuDobuObsluhyKadernickamiPoReplikaciach() {
        return this.sumaCelkovyCasObsluhyKadernicky / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernuDobuObsluhyKozmetickamiPoReplikaciach() {
        return this.sumaCelkovyCasObsluhyKozmeticky / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernuDobuPracovaniaNaviacPoReplikaciach() {
        return this.sumaAkoDlhoBoloOtvoreneNaviac / this.pocetVykonanychReplikacii;
    }
    
    public void ukonciCeluSimulaciu() {
        this.zmazCelyZoznamUdalosti();
    }
    
    // priemerne hodnoty do 17:00 po vykonani vsetkych replikacii
    public double dajPriemerneVytazenieRecepcnychPoReplikaciachBO() {
        return this.sumaVytazenieRecepcnychBO / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemerneVytazenieKadernicokPoReplikaciachBO() {
        return this.sumaVytazenieKadernicokBO / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemerneVytazenieKozmeticokPoReplikaciachBO() {
        return this.sumaVytazenieKozmeticokBO / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernyCasZakaznikaVSystemePoReplikaciachBO() {
        return this.sumaCasVSystemeBO / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernyCasVRadePredRecepcnymiObjednavkaPoReplikaciachBO() {
        return this.sumaCasVRadePredRecepcnymiObjednavkaBO / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernyCasVRadePredRecepcnymiPlatbaPoReplikaciachBO() {
        return this.sumaCasVRadePredRecepcnymiPlatbaBO / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernyCasVRadePredKadernickamiPoReplikaciachBO() {
        return this.sumaCasVRadePredKadernickamiBO / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernyCasVRadePredKozmetickamiCisteniePoReplikaciachBO() {
        return this.sumaCasVRadePredKozmetickamiCistenieBO / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernyCasVRadePredKozmetickamiLiceniePoReplikaciachBO() {
        return this.sumaCasVRadePredKozmetickamiLicenieBO / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernuDlzkuRaduPredRecepcnymiObjednavkaPoReplikaciachBO() {
        return this.sumaDlzkaRaduPredRecepcnymiObjednavkaBO / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernuDlzkuRaduPredRecepcnymiPlatbaPoReplikaciachBO() {
        return this.sumaDlzkaRaduPredRecepcnymiPlatbaBO / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernuDlzkuRaduPredKadernickamiPoReplikaciachBO() {
        return this.sumaDlzkaRaduPredKadernickamiBO / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernuDlzkuRaduPredKozmetickamiPoReplikaciachBO() {
        return this.sumaDlzkaRaduPredKozmetickamiBO / this.pocetVykonanychReplikacii;
    }
    
    public double dajSmerodajnuOdchylkuCasuZakaznikaVSystemeBO() {
        double sumaNaDruhu = Math.pow(this.dajPriemernyCasZakaznikaVSystemePoReplikaciachBO(), 2);        
        double prvaZatvorka = sumaNaDruhu / (this.pocetVykonanychReplikacii - 1);
        double druhaZatvorka = this.dajPriemernyCasZakaznikaVSystemePoReplikaciachBO()/ (this.pocetVykonanychReplikacii - 1);
        double zatvorkaNaDruhu = Math.pow(druhaZatvorka, 2);
        return Math.sqrt(prvaZatvorka - zatvorkaNaDruhu);
    }
    
    public double dajLavostrannyInterval90CasuZakaznikaVSystemeBO() {
        return this.dajPriemernyCasZakaznikaVSystemePoReplikaciachBO()- (this.dajSmerodajnuOdchylkuCasuZakaznikaVSystemeBO() * 1.645 / Math.sqrt(this.pocetVykonanychReplikacii));
    }
    
    public double dajPravostrannyInterval90CasuZakaznikaVSystemeBO() {
        return this.dajPriemernyCasZakaznikaVSystemePoReplikaciachBO()+ (this.dajSmerodajnuOdchylkuCasuZakaznikaVSystemeBO() * 1.645 / Math.sqrt(this.pocetVykonanychReplikacii));
    }
    
    public double dajPriemernuDobuObsluhyRecepcnymiPoReplikaciachBO() {
        return this.sumaCelkovyCasObsluhyRecepcneBO / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernuDobuObsluhyKadernickamiPoReplikaciachBO() {
        return this.sumaCelkovyCasObsluhyKadernickyBO / this.pocetVykonanychReplikacii;
    }
    
    public double dajPriemernuDobuObsluhyKozmetickamiPoReplikaciachBO() {
        return this.sumaCelkovyCasObsluhyKozmetickyBO / this.pocetVykonanychReplikacii;
    }
    
    // ako dlho bolo otvorene po skonceni otvaracich hodin
    public double akoDlhoBoloOtvorenePo() {
        this.akoDlhoBoloOtvorenePoVyprsaniOtvaracichHodin = this.simulacnyCas - this.prelomovyCas;
        return this.akoDlhoBoloOtvorenePoVyprsaniOtvaracichHodin;
    }
    
    // nastav, kolko ludi bolo poslanych domov
    public void nastavKolkoLudiBoloPoslanychDomov(int kolko) {
        this.kolkoLudiBoloPoslanychDomov = kolko;
    }
    
    // daj, kolko ludi bolo poslanych domov
    public int dajKolkoLudiBoloPoslanychDomov() {
        return this.kolkoLudiBoloPoslanychDomov;
    }
    
    // daj priemerny pocet vyhodenych ludi z radu pred recepcnymi
    public double dajPriemernyPocetVyhodenychLudiPoReplikaciachBO() {
        return this.sumaKolkoLudiBoloVyhodenych / this.pocetVykonanychReplikacii;
    }
}
