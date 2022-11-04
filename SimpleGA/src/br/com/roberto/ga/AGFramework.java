package br.com.roberto.ga;

import java.util.ArrayList;
import java.util.List;

import br.com.roberto.ga.base.AGParameters;
import br.com.roberto.ga.base.Crossover;
import br.com.roberto.ga.base.Individual;
import br.com.roberto.ga.base.InitialPopulation;
import br.com.roberto.ga.base.Mutation;
import br.com.roberto.ga.base.Selection;

public class AGFramework {

	private List<Individual> currentGeneration;
	private List<Individual> newGeneration;

	private int epoch;

	private InitialPopulation initialPopulation;
	private Selection selection;
	private Crossover crossover;
	private Mutation mutation;

	public AGFramework(Selection selection, Crossover crossover, Mutation mutation,
			InitialPopulation initialPopulation) {
		this.selection = selection;
		this.crossover = crossover;
		this.mutation = mutation;
		this.initialPopulation = initialPopulation;
	}

	public void execute() {
		epoch = 0;
		currentGeneration = initialPopulation.createInitialPopulation();
		evaluate();
		while (!stopCriteria()) {
			AGLog.logEpoch(epoch, getTheBestIndividual());
			executeSelectionAndCrossing();
			currentGeneration = newGeneration;
			executeMutation();
			evaluate();
			epoch++;
		}
		AGLog.logEpoch(epoch, getTheBestIndividual());
	}

	private void executeMutation() {
		mutation.executeMutation(currentGeneration);
	}

	private Individual getTheBestIndividual() {
		Individual best = currentGeneration.get(0);
		for (Individual individual : currentGeneration) {
			if (individual.getFitness() < best.getFitness()) {
				best = individual;
			}
		}
		return best;
	}

	private void executeSelectionAndCrossing() {
		newGeneration = new ArrayList<>();
		selection.prepareSelection(currentGeneration);

		while (newGeneration.size() < currentGeneration.size()) {

			// choose the parents
			Individual individualOne = selection.chooseParent();
			Individual individualTwo = selection.chooseParent();

			Crossover.IndividualPair pair = crossover.doCrossover(individualOne, individualTwo);
			newGeneration.add(pair.individualOne);
			newGeneration.add(pair.individualTwo);
		}
	}

	private boolean stopCriteria() {
		return epoch >= AGParameters.MAX_ITERATIONS;
	}

	private void evaluate() {
		currentGeneration.stream().forEach(i -> i.calculateFitness());
	}
}
