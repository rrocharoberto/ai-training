package br.com.roberto.ga.base;

import java.util.Random;

public interface Individual {

	static Random rand = new Random(AGParameters.SEED);
	
	void calculateFitness();

	double getFitness();

}
