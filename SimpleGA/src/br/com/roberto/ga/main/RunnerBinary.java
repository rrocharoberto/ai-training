package br.com.roberto.ga.main;

import br.com.roberto.ga.AGFramework;
import br.com.roberto.ga.RouletteWheelSelection;
import br.com.roberto.ga.base.Crossover;
import br.com.roberto.ga.base.InitialPopulation;
import br.com.roberto.ga.base.Mutation;
import br.com.roberto.ga.base.Selection;
import br.com.roberto.ga.binary.BinaryCrossover;
import br.com.roberto.ga.binary.BinaryInitialPopulation;
import br.com.roberto.ga.binary.BinaryMutation;

public class RunnerBinary {

	public static void main(String[] args) {
		Selection selection = new RouletteWheelSelection();
		Crossover crossover = new BinaryCrossover();
		Mutation mutation = new BinaryMutation();
		InitialPopulation initialPopulation = new BinaryInitialPopulation();
		
		new AGFramework(selection, crossover, mutation, initialPopulation).execute();
	}
}
