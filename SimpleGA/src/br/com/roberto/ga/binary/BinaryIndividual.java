package br.com.roberto.ga.binary;

import br.com.roberto.ga.base.Individual;

/**
 * Individual that represents the following function: f(x) = 0.1*x^(2) - 3*x + 25
 * @author roberto
 *
 */
public class BinaryIndividual implements Individual {

	/**
	 * Binary representation of the x value
	 */
	private String info;
	private double fitness;
	
	/**
	 * Amount of bits supported by this individual
	 */
	public static int BIT_SIZE = 15;

	public BinaryIndividual(String info) {
		this.info = info;
	}

	public static Individual createRandon() {
		int x = rand.nextInt((int) Math.pow(2, BinaryIndividual.BIT_SIZE));

		String rawInfo = Integer.toBinaryString(x);
		String info = String.format("%" + BinaryIndividual.BIT_SIZE + "s", rawInfo).replace(' ', '0');
		return new BinaryIndividual(info);
	}

	@Override
	public void calculateFitness() {
		int x = getInfoAsInteger();

		// f(x)=0.1*x^(2)-3*x+25
		fitness = 0.1 * Math.pow(x, 2) - 3 * x + 25;
	}

	@Override
	public double getFitness() {
		return fitness;
	}
	
	private int getInfoAsInteger() {
		return Integer.parseInt(info, 2);
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Individual [info=" + info + ", value=" + Integer.parseInt(info, 2) + ", fitness=" + fitness + "]";
	}
}
