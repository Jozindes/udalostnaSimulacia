/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatory;

/**
 *
 * @author Jožko
 */
public class Exponencialne extends Generator {
    
    private final double lambda;
    
    public Exponencialne(double lambda) {
        this.lambda = lambda;
    }
    
    public double dajVygenerovanuHodnotu() {
        return Math.log(1 - this.generator.nextDouble()) / (-lambda);
    }
}
