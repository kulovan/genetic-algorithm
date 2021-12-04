package com.janko;

import java.util.Random;

public class Calculator {

    Population population = new Population();
    Student fittest;
    Student secondFittest;
    int generationCount = 0;

    public static void main(String[] args) {
        Random rn = new Random();

        Calculator calculator = new Calculator();

        //Initialize population
        calculator.population.initializePopulation(3);

        //Calculate fitness of each individual
        calculator.population.calculateFitness();

        System.out.println("Generation: " + calculator.generationCount + " Fittest: " + calculator.population.fittest);

        //While population gets an individual with maximum fitness
        while (calculator.population.fittest < 5) {
            ++calculator.generationCount;

            //Do selection
            calculator.selection();

            //Do crossover
            calculator.crossover();

            //Do mutation under a random probability
            if (rn.nextInt()%7 < 5) {
                calculator.mutation();
            }

            //Add fittest offspring to population
            calculator.addFittestOffspring();

            //Calculate new fitness value
            calculator.population.calculateFitness();

            System.out.println("Generation: " + calculator.generationCount + " Fittest: " + calculator.population.fittest);
        }

        System.out.println("\nSolution found in generation " + calculator.generationCount);
        System.out.println("Fitness: "+calculator.population.getFittest().fitness);
        System.out.print("Genes: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(calculator.population.getFittest().genes[i]);
        }

        System.out.println("");
    }
    void selection() {

        //Select the most fittest individual
        fittest = population.getFittest();

        //Select the second most fittest individual
        secondFittest = population.getSecondFittest();
    }

    //Crossover
    void crossover() {
        Random rn = new Random();

        //Select a random crossover point
        int crossOverPoint = rn.nextInt(population.students[0].geneLength);

        //Swap values among parents
        for (int i = 0; i < crossOverPoint; i++) {
            int temp = fittest.genes[i];
            fittest.genes[i] = secondFittest.genes[i];
            secondFittest.genes[i] = temp;

        }

    }

    //Mutation
    void mutation() {
        Random rn = new Random();

        //Select a random mutation point
        int mutationPoint = rn.nextInt(population.students[0].geneLength);

        //Flip values at the mutation point
        if (fittest.genes[mutationPoint] == 0) {
            fittest.genes[mutationPoint] = 1;
        } else {
            fittest.genes[mutationPoint] = 0;
        }

        mutationPoint = rn.nextInt(population.students[0].geneLength);

        if (secondFittest.genes[mutationPoint] == 0) {
            secondFittest.genes[mutationPoint] = 1;
        } else {
            secondFittest.genes[mutationPoint] = 0;
        }
    }

    //Get fittest offspring
    Student getFittestOffspring() {
        if (fittest.fitness > secondFittest.fitness) {
            return fittest;
        }
        return secondFittest;
    }


    //Replace least fittest individual from most fittest offspring
    void addFittestOffspring() {

        //Update fitness values of offspring
        fittest.calcFitness();
        secondFittest.calcFitness();

        //Get index of least fit individual
        int leastFittestIndex = population.getLeastFittestIndex();

        //Replace least fittest individual from most fittest offspring
        population.students[leastFittestIndex] = getFittestOffspring();
    }

}
