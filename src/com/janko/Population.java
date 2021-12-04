package com.janko;

class Population {

    int popSize = 10;
    Student[] students = new Student[10];
    int fittest = 0;

    //Initialize population
    public void initializePopulation(int size) {
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student();
        }
    }

    //Get the fittest individual
    public Student getFittest() {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < students.length; i++) {
            if (maxFit <= students[i].fitness) {
                maxFit = students[i].fitness;
                maxFitIndex = i;
            }
        }
        fittest = students[maxFitIndex].fitness;
        return students[maxFitIndex];
    }

    //Get the second most fittest individual
    public Student getSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i].fitness > students[maxFit1].fitness) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (students[i].fitness > students[maxFit2].fitness) {
                maxFit2 = i;
            }
        }
        return students[maxFit2];
    }

    //Get index of least fittest individual
    public int getLeastFittestIndex() {
        int minFitVal = Integer.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < students.length; i++) {
            if (minFitVal >= students[i].fitness) {
                minFitVal = students[i].fitness;
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

    //Calculate fitness of each individual
    public void calculateFitness() {

        for (int i = 0; i < students.length; i++) {
            students[i].calcFitness();
        }
        getFittest();
    }

}
