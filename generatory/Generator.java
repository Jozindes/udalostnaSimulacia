/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatory;

import java.util.Random;

/**
 *
 * @author Jožko
 */
public class Generator {
    
    protected Random generator;
    
    public Generator() {
        this.generator = new Random(System.currentTimeMillis());
    }
}
