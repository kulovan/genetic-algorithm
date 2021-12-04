package com.janko;

import java.util.Random;

class Student {

    int fitness = 0;
    int[] genes = new int[5];
    int geneLength = 5;

    public Student() {
        Random rn = new Random();

        //Set genes randomly for each individual
        for (int i = 0; i < genes.length; i++) {
            this.genes[i] = Math.abs(rn.nextInt() % 2);
        }

        this.fitness = 0;
    }

    //Calculate fitness
    public void calcFitness() {

        fitness = 0;
        for (int i = 0; i < 5; i++) {
            if (genes[i] == 1) {
                ++fitness;
            }
        }
    }

}
