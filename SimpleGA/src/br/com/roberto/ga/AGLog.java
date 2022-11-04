package br.com.roberto.ga;

import java.util.Collections;
import java.util.List;

import br.com.roberto.ga.base.Individual;

public class AGLog {

	public static void printGeneration(int epoch, List<Individual> currentGeneration) {
		Collections.sort(currentGeneration, (o1, o2) -> (int) (o1.getFitness() - o2.getFitness()));
		System.out.println("Individuals of generation: " + epoch);
		for (Individual individual : currentGeneration) {
			System.out.println(individual);
		}
	}

	public static void logEpoch(int epoch, Individual bestIndividual) {
		// printCurrentGeneration();
		// log da época
		System.out.println("Epoch: " + epoch);
		System.out.println("Best individual:" + bestIndividual);
	}
}
