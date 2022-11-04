package br.com.roberto.ga;

import java.util.List;

import br.com.roberto.ga.base.Individual;
import br.com.roberto.ga.base.Selection;

public class RouletteWheelSelection implements Selection {

	private List<? extends Individual> currentGeneration;

	private double baseNumber;

	private double fitnessSum = 0; // soma de todos os fitness normalizado

	@Override
	public void prepareSelection(List<? extends Individual> currentGeneration) {
		this.currentGeneration = currentGeneration;

		double maior = currentGeneration.get(0).getFitness();
		double menor = maior;
		for (Individual individual : currentGeneration) {
			if (individual.getFitness() > maior) {
				maior = individual.getFitness();
			}
			if (individual.getFitness() < menor) {
				menor = individual.getFitness();
			}
		}
		baseNumber = maior + menor; // base para normalização

		fitnessSum = 0;
		for (Individual individual : currentGeneration) {
			fitnessSum += baseNumber - individual.getFitness();
		}
	}

	@Override
	public Individual chooseParent() {
		double point = rand.nextInt((int) fitnessSum); // ponto sorteado
		// System.out.println("point: " + point);

		double sum = 0; // soma dos fitness

		for (Individual ind : currentGeneration) {
			double sizeOfIndividual = baseNumber - ind.getFitness();
			// System.out.println("Size of individual " + ind.getInfoAsInteger() + ":" + sizeOfIndividual);
			sum += sizeOfIndividual;
			// System.out.println("sum: " + sum);
			if (sum > point) {
				// System.out.println("Parent choosen: " + ind);
				return ind;
			}
		}
		// se não achou nenhum, então retorna o último
		Individual individual = currentGeneration.get(currentGeneration.size() - 1);
		return individual;
	}
}
