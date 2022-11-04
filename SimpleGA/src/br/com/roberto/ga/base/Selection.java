package br.com.roberto.ga.base;

import java.util.List;
import java.util.Random;

public interface Selection {

	Random rand = new Random(AGParameters.SEED);
	
	void prepareSelection(List<? extends Individual> currentGeneration);

	Individual chooseParent();

}
