/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatory;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jožko
 */
public class DiskretneEmpiricke extends Generator {
    private ArrayList<PrvokEmpiricke> prvky;
    private Random generatorNasad;
    private Random ktoryGeneratorSaPouzije;
    private Random[] generatory;
    
    public DiskretneEmpiricke(ArrayList<PrvokEmpiricke> paPrvky) {
        this.prvky = paPrvky;
        this.generatorNasad = new Random();
        this.ktoryGeneratorSaPouzije = new Random(this.generatorNasad.nextInt());
        this.generatory = new Random[this.prvky.size()];
        for (int i = 0; i < this.prvky.size(); i++) {
            this.generatory[i] = new Random(this.generatorNasad.nextInt());
        }
    }
    
    public double dajVygenerovanuHodnotu() {
        double vygenerovanaHodnota = this.ktoryGeneratorSaPouzije.nextDouble();
        double sumaPravdepodobnosti = 0;
        for (int i = 0; i < this.prvky.size(); i++) {
            sumaPravdepodobnosti = sumaPravdepodobnosti + this.prvky.get(i).dajPravdepodobnost();
            if (vygenerovanaHodnota < sumaPravdepodobnosti) {
                return (this.generatory[i].nextInt((int) (this.prvky.get(i).dajMaximum() - this.prvky.get(i).dajMinimum())) + this.prvky.get(i).dajMinimum());
            }
        }
        return 0;
    }
}
