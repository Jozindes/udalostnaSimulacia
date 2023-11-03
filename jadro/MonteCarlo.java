package jadro;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jožko
 */
public abstract class MonteCarlo {
    
    protected final int pocetReplikacii;
    
    public MonteCarlo(int paPocetReplikacii) {
        this.pocetReplikacii = paPocetReplikacii;
    }
    
    public void vykonajVsetkyReplikacie() {
        for (int i = 0; i < this.pocetReplikacii; i++) {
            this.predReplikaciou();
            this.vykonajReplikaciu();
            this.poReplikacii();
        }
    }
        
    public abstract void predReplikaciou();
    
    public abstract void vykonajReplikaciu();
    
    public abstract void poReplikacii();
}
