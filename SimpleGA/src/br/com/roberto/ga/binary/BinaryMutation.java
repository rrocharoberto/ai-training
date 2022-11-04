package br.com.roberto.ga.binary;

import java.util.List;
import java.util.Random;

import br.com.roberto.ga.base.AGParameters;
import br.com.roberto.ga.base.Individual;
import br.com.roberto.ga.base.Mutation;

public class BinaryMutation implements Mutation {

	private Random rand = new Random(AGParameters.SEED);

	@Override
	public void executeMutation(List<? extends Individual> currentGeneration) {
		// TODO: tarefa para casa
		// DICA: criar uma constante com o percentual (de 1% a 3%) de mutação
		// ou seja, percentual de indivíduos que serão mutados naquela época

		//esta implementação usa a taxa como quantidade de indivíduos que serão mutados.
		for (int i = 0; i < AGParameters.MUTATION_RATE; i++) {
			int elementChoosen = rand.nextInt(currentGeneration.size() - 1);
			BinaryIndividual individual = (BinaryIndividual) currentGeneration.get(elementChoosen);
			// System.out.println("Mutating: " + individual);

			int position = rand.nextInt(BinaryIndividual.BIT_SIZE);// posição que será alterada
			char bit = individual.getInfo().charAt(position);
			bit = bit == '0' ? '1' : '0'; // inverte o bit

			StringBuilder builder = new StringBuilder(individual.getInfo());
			builder.setCharAt(position, bit);// seta o novo caractere

			individual.setInfo(builder.toString());
			// System.out.println("Mutated: " + individual);
		}
	}
}
