/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatory;

/**
 *
 * @author Jožko
 */
public class PrvokEmpiricke {
    private double minimum;
    private double maximum;
    private double pravdepodobnost;
    
    public PrvokEmpiricke(double paMin, double paMax, double paPravdepodobnost) {
        this.minimum = paMin;
        this.maximum = paMax + 1;
        this.pravdepodobnost = paPravdepodobnost;
    }
    
    public double dajMinimum() {
        return this.minimum;
    }
    
    public double dajMaximum() {
        return this.maximum;
    }
    
    public double dajPravdepodobnost() {
        return this.pravdepodobnost;
    }
}
