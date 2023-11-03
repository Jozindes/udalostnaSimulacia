/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatory;

/**
 *
 * @author Jožko
 */
public class Trojuholnikove extends Generator {
    
    private double a;
    private double b;
    private double c;
       
    public Trojuholnikove(double a, double b, double c) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public double dajVygenerovanuHodnotu() {
        double F = (this.c - this.a) / (this.b - this.a);
        double nahodne = this.generator.nextDouble();
        
        if (nahodne < F) {
            return a + Math.sqrt(nahodne * (b - a) * (c - a));
        } else {
            return b - Math.sqrt((1 - nahodne) * (b - a) * (b - c));
        }
    }
}
