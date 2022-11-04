package br.com.roberto.ga.binary;

import br.com.roberto.ga.base.Crossover;
import br.com.roberto.ga.base.Individual;

public class BinaryCrossover implements Crossover {

	@Override
	public IndividualPair doCrossover(Individual individualOne, Individual individualTwo) {

		int crossPoint = (int) (Math.random() * (BinaryIndividual.BIT_SIZE - 1)) + 1;

		String info1 = ((BinaryIndividual) individualOne).getInfo();
		String info2 = ((BinaryIndividual) individualOne).getInfo();

		String part11 = info1.substring(0, crossPoint); // parte 1 do ind 1
		String part12 = info2.substring(crossPoint, BinaryIndividual.BIT_SIZE); // parte 2 do ind 2

		BinaryIndividual childOne = new BinaryIndividual(part11 + part12);

		String part21 = info2.substring(0, crossPoint); // part 1 do ind 2

		String part22 = info1.substring(crossPoint, BinaryIndividual.BIT_SIZE); // part 2 do ind 1
		BinaryIndividual childTwo = new BinaryIndividual(part21 + part22);

		return new IndividualPair(childOne, childTwo);
	}
}
