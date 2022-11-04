package br.com.roberto.ga.base;

public interface Crossover {

	public class IndividualPair {
		public Individual individualOne;
		public Individual individualTwo;

		public IndividualPair(Individual individualOne, Individual individualTwo) {
			this.individualOne = individualOne;
			this.individualTwo = individualTwo;
		}
	}
	
	IndividualPair doCrossover(Individual individualOne, Individual individualTwo);

}
