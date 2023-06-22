package tp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static double[][][] base = new double[625][2][];

	public static void main(String[] args) {
		for (int i = 0; i < base.length; i++) {
			base[i][0] = new double[4];
			base[i][1] = new double[3];
		}
		String lines[] = input.split("\n");
		int i = 0;
		while (i < lines.length) {
			String c[] = lines[i].split(",");
			if (c[0].equals("L")) {
				base[i][1][0] = 1;
				base[i][1][1] = 0;
				base[i][1][2] = 0;
			} else if (c[0].equals("B")) {
				base[i][1][0] = 0;
				base[i][1][1] = 1;
				base[i][1][2] = 0;
			} else if (c[0].equals("R")) {
				base[i][1][0] = 0;
				base[i][1][1] = 0;
				base[i][1][2] = 1;
			} else {
				base[i][1][0] = 0;
				base[i][1][1] = 0;
				base[i][1][2] = 0;
			}
			for (int j = 1; j < c.length; j++) {
				base[i][0][j - 1] = Double.parseDouble(c[j]);
			}
			i++;
		}
		double startTime = System.currentTimeMillis();
		execute(10000);
		double endTime = System.currentTimeMillis();
		double tempo = (endTime - startTime) / 1000;
		System.out.printf("Tempo: %.2f s\n", tempo);
	}

	private static void execute(int epocas) {
		Perceptron perceptron = new Perceptron(4, 3, 0.01);
		for (int e = 0; e < epocas; e++) {
			double erroAproxEpoca = 0;
			double erroClassE = 0;
			for (int a = 0; a < base.length; a++) {
				double[] x = base[a][0];
				double[] y = base[a][1];
				List<Double> xin = new ArrayList<Double>(x.length + 1);
				for (double d : x) {
					xin.add(d);
				}
				double[] theta = perceptron.treinar(xin, y);
				double erroAproxAmostra = 0;
				for (int i = 0; i < theta.length; i++) {
					erroAproxAmostra += Math.abs((y[i] - theta[i]));
				}
				double max = maior(theta);
				for (int i = 0; i < theta.length; i++) {
					if (theta[i] == max)
						theta[i] = 1;
					else
						theta[i] = 0;
				}
				double erroClassA = 0;
				for (int i = 0; i < theta.length; i++) {
					erroClassA += Math.abs((y[i] - theta[i]));
				}
				if (erroClassA > 0) {
					erroClassA = 1;
				} else {
					erroClassA = 0;
				}
				erroClassE += erroClassA;
				erroAproxEpoca += erroAproxAmostra;

			}
			System.out.println("Epoca: " + (e + 1) + " - erro de aproximação: " + erroAproxEpoca
					+ " - erro de classificação: " + erroClassE);
		}
	}

	private static double maior(double[] numbers) {
		double maxNumber = Double.MIN_VALUE;

		for (double number : numbers) {
			if (number > maxNumber) {
				maxNumber = number;
			}
		}
		return maxNumber;
	}
	static String input = "B,1,1,1,1\n" +
			"R,1,1,1,2\n" +
			"R,1,1,1,3\n" +
			"R,1,1,1,4\n" +
			"R,1,1,1,5\n" +
			"R,1,1,2,1\n" +
			"R,1,1,2,2\n" +
			"R,1,1,2,3\n" +
			"R,1,1,2,4\n" +
			"R,1,1,2,5\n" +
			"R,1,1,3,1\n" +
			"R,1,1,3,2\n" +
			"R,1,1,3,3\n" +
			"R,1,1,3,4\n" +
			"R,1,1,3,5\n" +
			"R,1,1,4,1\n" +
			"R,1,1,4,2\n" +
			"R,1,1,4,3\n" +
			"R,1,1,4,4\n" +
			"R,1,1,4,5\n" +
			"R,1,1,5,1\n" +
			"R,1,1,5,2\n" +
			"R,1,1,5,3\n" +
			"R,1,1,5,4\n" +
			"R,1,1,5,5\n" +
			"L,1,2,1,1\n" +
			"B,1,2,1,2\n" +
			"R,1,2,1,3\n" +
			"R,1,2,1,4\n" +
			"R,1,2,1,5\n" +
			"B,1,2,2,1\n" +
			"R,1,2,2,2\n" +
			"R,1,2,2,3\n" +
			"R,1,2,2,4\n" +
			"R,1,2,2,5\n" +
			"R,1,2,3,1\n" +
			"R,1,2,3,2\n" +
			"R,1,2,3,3\n" +
			"R,1,2,3,4\n" +
			"R,1,2,3,5\n" +
			"R,1,2,4,1\n" +
			"R,1,2,4,2\n" +
			"R,1,2,4,3\n" +
			"R,1,2,4,4\n" +
			"R,1,2,4,5\n" +
			"R,1,2,5,1\n" +
			"R,1,2,5,2\n" +
			"R,1,2,5,3\n" +
			"R,1,2,5,4\n" +
			"R,1,2,5,5\n" +
			"L,1,3,1,1\n" +
			"L,1,3,1,2\n" +
			"B,1,3,1,3\n" +
			"R,1,3,1,4\n" +
			"R,1,3,1,5\n" +
			"L,1,3,2,1\n" +
			"R,1,3,2,2\n" +
			"R,1,3,2,3\n" +
			"R,1,3,2,4\n" +
			"R,1,3,2,5\n" +
			"B,1,3,3,1\n" +
			"R,1,3,3,2\n" +
			"R,1,3,3,3\n" +
			"R,1,3,3,4\n" +
			"R,1,3,3,5\n" +
			"R,1,3,4,1\n" +
			"R,1,3,4,2\n" +
			"R,1,3,4,3\n" +
			"R,1,3,4,4\n" +
			"R,1,3,4,5\n" +
			"R,1,3,5,1\n" +
			"R,1,3,5,2\n" +
			"R,1,3,5,3\n" +
			"R,1,3,5,4\n" +
			"R,1,3,5,5\n" +
			"L,1,4,1,1\n" +
			"L,1,4,1,2\n" +
			"L,1,4,1,3\n" +
			"B,1,4,1,4\n" +
			"R,1,4,1,5\n" +
			"L,1,4,2,1\n" +
			"B,1,4,2,2\n" +
			"R,1,4,2,3\n" +
			"R,1,4,2,4\n" +
			"R,1,4,2,5\n" +
			"L,1,4,3,1\n" +
			"R,1,4,3,2\n" +
			"R,1,4,3,3\n" +
			"R,1,4,3,4\n" +
			"R,1,4,3,5\n" +
			"B,1,4,4,1\n" +
			"R,1,4,4,2\n" +
			"R,1,4,4,3\n" +
			"R,1,4,4,4\n" +
			"R,1,4,4,5\n" +
			"R,1,4,5,1\n" +
			"R,1,4,5,2\n" +
			"R,1,4,5,3\n" +
			"R,1,4,5,4\n" +
			"R,1,4,5,5\n" +
			"L,1,5,1,1\n" +
			"L,1,5,1,2\n" +
			"L,1,5,1,3\n" +
			"L,1,5,1,4\n" +
			"B,1,5,1,5\n" +
			"L,1,5,2,1\n" +
			"L,1,5,2,2\n" +
			"R,1,5,2,3\n" +
			"R,1,5,2,4\n" +
			"R,1,5,2,5\n" +
			"L,1,5,3,1\n" +
			"R,1,5,3,2\n" +
			"R,1,5,3,3\n" +
			"R,1,5,3,4\n" +
			"R,1,5,3,5\n" +
			"L,1,5,4,1\n" +
			"R,1,5,4,2\n" +
			"R,1,5,4,3\n" +
			"R,1,5,4,4\n" +
			"R,1,5,4,5\n" +
			"B,1,5,5,1\n" +
			"R,1,5,5,2\n" +
			"R,1,5,5,3\n" +
			"R,1,5,5,4\n" +
			"R,1,5,5,5\n" +
			"L,2,1,1,1\n" +
			"B,2,1,1,2\n" +
			"R,2,1,1,3\n" +
			"R,2,1,1,4\n" +
			"R,2,1,1,5\n" +
			"B,2,1,2,1\n" +
			"R,2,1,2,2\n" +
			"R,2,1,2,3\n" +
			"R,2,1,2,4\n" +
			"R,2,1,2,5\n" +
			"R,2,1,3,1\n" +
			"R,2,1,3,2\n" +
			"R,2,1,3,3\n" +
			"R,2,1,3,4\n" +
			"R,2,1,3,5\n" +
			"R,2,1,4,1\n" +
			"R,2,1,4,2\n" +
			"R,2,1,4,3\n" +
			"R,2,1,4,4\n" +
			"R,2,1,4,5\n" +
			"R,2,1,5,1\n" +
			"R,2,1,5,2\n" +
			"R,2,1,5,3\n" +
			"R,2,1,5,4\n" +
			"R,2,1,5,5\n" +
			"L,2,2,1,1\n" +
			"L,2,2,1,2\n" +
			"L,2,2,1,3\n" +
			"B,2,2,1,4\n" +
			"R,2,2,1,5\n" +
			"L,2,2,2,1\n" +
			"B,2,2,2,2\n" +
			"R,2,2,2,3\n" +
			"R,2,2,2,4\n" +
			"R,2,2,2,5\n" +
			"L,2,2,3,1\n" +
			"R,2,2,3,2\n" +
			"R,2,2,3,3\n" +
			"R,2,2,3,4\n" +
			"R,2,2,3,5\n" +
			"B,2,2,4,1\n" +
			"R,2,2,4,2\n" +
			"R,2,2,4,3\n" +
			"R,2,2,4,4\n" +
			"R,2,2,4,5\n" +
			"R,2,2,5,1\n" +
			"R,2,2,5,2\n" +
			"R,2,2,5,3\n" +
			"R,2,2,5,4\n" +
			"R,2,2,5,5\n" +
			"L,2,3,1,1\n" +
			"L,2,3,1,2\n" +
			"L,2,3,1,3\n" +
			"L,2,3,1,4\n" +
			"L,2,3,1,5\n" +
			"L,2,3,2,1\n" +
			"L,2,3,2,2\n" +
			"B,2,3,2,3\n" +
			"R,2,3,2,4\n" +
			"R,2,3,2,5\n" +
			"L,2,3,3,1\n" +
			"B,2,3,3,2\n" +
			"R,2,3,3,3\n" +
			"R,2,3,3,4\n" +
			"R,2,3,3,5\n" +
			"L,2,3,4,1\n" +
			"R,2,3,4,2\n" +
			"R,2,3,4,3\n" +
			"R,2,3,4,4\n" +
			"R,2,3,4,5\n" +
			"L,2,3,5,1\n" +
			"R,2,3,5,2\n" +
			"R,2,3,5,3\n" +
			"R,2,3,5,4\n" +
			"R,2,3,5,5\n" +
			"L,2,4,1,1\n" +
			"L,2,4,1,2\n" +
			"L,2,4,1,3\n" +
			"L,2,4,1,4\n" +
			"L,2,4,1,5\n" +
			"L,2,4,2,1\n" +
			"L,2,4,2,2\n" +
			"L,2,4,2,3\n" +
			"B,2,4,2,4\n" +
			"R,2,4,2,5\n" +
			"L,2,4,3,1\n" +
			"L,2,4,3,2\n" +
			"R,2,4,3,3\n" +
			"R,2,4,3,4\n" +
			"R,2,4,3,5\n" +
			"L,2,4,4,1\n" +
			"B,2,4,4,2\n" +
			"R,2,4,4,3\n" +
			"R,2,4,4,4\n" +
			"R,2,4,4,5\n" +
			"L,2,4,5,1\n" +
			"R,2,4,5,2\n" +
			"R,2,4,5,3\n" +
			"R,2,4,5,4\n" +
			"R,2,4,5,5\n" +
			"L,2,5,1,1\n" +
			"L,2,5,1,2\n" +
			"L,2,5,1,3\n" +
			"L,2,5,1,4\n" +
			"L,2,5,1,5\n" +
			"L,2,5,2,1\n" +
			"L,2,5,2,2\n" +
			"L,2,5,2,3\n" +
			"L,2,5,2,4\n" +
			"B,2,5,2,5\n" +
			"L,2,5,3,1\n" +
			"L,2,5,3,2\n" +
			"L,2,5,3,3\n" +
			"R,2,5,3,4\n" +
			"R,2,5,3,5\n" +
			"L,2,5,4,1\n" +
			"L,2,5,4,2\n" +
			"R,2,5,4,3\n" +
			"R,2,5,4,4\n" +
			"R,2,5,4,5\n" +
			"L,2,5,5,1\n" +
			"B,2,5,5,2\n" +
			"R,2,5,5,3\n" +
			"R,2,5,5,4\n" +
			"R,2,5,5,5\n" +
			"L,3,1,1,1\n" +
			"L,3,1,1,2\n" +
			"B,3,1,1,3\n" +
			"R,3,1,1,4\n" +
			"R,3,1,1,5\n" +
			"L,3,1,2,1\n" +
			"R,3,1,2,2\n" +
			"R,3,1,2,3\n" +
			"R,3,1,2,4\n" +
			"R,3,1,2,5\n" +
			"B,3,1,3,1\n" +
			"R,3,1,3,2\n" +
			"R,3,1,3,3\n" +
			"R,3,1,3,4\n" +
			"R,3,1,3,5\n" +
			"R,3,1,4,1\n" +
			"R,3,1,4,2\n" +
			"R,3,1,4,3\n" +
			"R,3,1,4,4\n" +
			"R,3,1,4,5\n" +
			"R,3,1,5,1\n" +
			"R,3,1,5,2\n" +
			"R,3,1,5,3\n" +
			"R,3,1,5,4\n" +
			"R,3,1,5,5\n" +
			"L,3,2,1,1\n" +
			"L,3,2,1,2\n" +
			"L,3,2,1,3\n" +
			"L,3,2,1,4\n" +
			"L,3,2,1,5\n" +
			"L,3,2,2,1\n" +
			"L,3,2,2,2\n" +
			"B,3,2,2,3\n" +
			"R,3,2,2,4\n" +
			"R,3,2,2,5\n" +
			"L,3,2,3,1\n" +
			"B,3,2,3,2\n" +
			"R,3,2,3,3\n" +
			"R,3,2,3,4\n" +
			"R,3,2,3,5\n" +
			"L,3,2,4,1\n" +
			"R,3,2,4,2\n" +
			"R,3,2,4,3\n" +
			"R,3,2,4,4\n" +
			"R,3,2,4,5\n" +
			"L,3,2,5,1\n" +
			"R,3,2,5,2\n" +
			"R,3,2,5,3\n" +
			"R,3,2,5,4\n" +
			"R,3,2,5,5\n" +
			"L,3,3,1,1\n" +
			"L,3,3,1,2\n" +
			"L,3,3,1,3\n" +
			"L,3,3,1,4\n" +
			"L,3,3,1,5\n" +
			"L,3,3,2,1\n" +
			"L,3,3,2,2\n" +
			"L,3,3,2,3\n" +
			"L,3,3,2,4\n" +
			"R,3,3,2,5\n" +
			"L,3,3,3,1\n" +
			"L,3,3,3,2\n" +
			"B,3,3,3,3\n" +
			"R,3,3,3,4\n" +
			"R,3,3,3,5\n" +
			"L,3,3,4,1\n" +
			"L,3,3,4,2\n" +
			"R,3,3,4,3\n" +
			"R,3,3,4,4\n" +
			"R,3,3,4,5\n" +
			"L,3,3,5,1\n" +
			"R,3,3,5,2\n" +
			"R,3,3,5,3\n" +
			"R,3,3,5,4\n" +
			"R,3,3,5,5\n" +
			"L,3,4,1,1\n" +
			"L,3,4,1,2\n" +
			"L,3,4,1,3\n" +
			"L,3,4,1,4\n" +
			"L,3,4,1,5\n" +
			"L,3,4,2,1\n" +
			"L,3,4,2,2\n" +
			"L,3,4,2,3\n" +
			"L,3,4,2,4\n" +
			"L,3,4,2,5\n" +
			"L,3,4,3,1\n" +
			"L,3,4,3,2\n" +
			"L,3,4,3,3\n" +
			"B,3,4,3,4\n" +
			"R,3,4,3,5\n" +
			"L,3,4,4,1\n" +
			"L,3,4,4,2\n" +
			"B,3,4,4,3\n" +
			"R,3,4,4,4\n" +
			"R,3,4,4,5\n" +
			"L,3,4,5,1\n" +
			"L,3,4,5,2\n" +
			"R,3,4,5,3\n" +
			"R,3,4,5,4\n" +
			"R,3,4,5,5\n" +
			"L,3,5,1,1\n" +
			"L,3,5,1,2\n" +
			"L,3,5,1,3\n" +
			"L,3,5,1,4\n" +
			"L,3,5,1,5\n" +
			"L,3,5,2,1\n" +
			"L,3,5,2,2\n" +
			"L,3,5,2,3\n" +
			"L,3,5,2,4\n" +
			"L,3,5,2,5\n" +
			"L,3,5,3,1\n" +
			"L,3,5,3,2\n" +
			"L,3,5,3,3\n" +
			"L,3,5,3,4\n" +
			"B,3,5,3,5\n" +
			"L,3,5,4,1\n" +
			"L,3,5,4,2\n" +
			"L,3,5,4,3\n" +
			"R,3,5,4,4\n" +
			"R,3,5,4,5\n" +
			"L,3,5,5,1\n" +
			"L,3,5,5,2\n" +
			"B,3,5,5,3\n" +
			"R,3,5,5,4\n" +
			"R,3,5,5,5\n" +
			"L,4,1,1,1\n" +
			"L,4,1,1,2\n" +
			"L,4,1,1,3\n" +
			"B,4,1,1,4\n" +
			"R,4,1,1,5\n" +
			"L,4,1,2,1\n" +
			"B,4,1,2,2\n" +
			"R,4,1,2,3\n" +
			"R,4,1,2,4\n" +
			"R,4,1,2,5\n" +
			"L,4,1,3,1\n" +
			"R,4,1,3,2\n" +
			"R,4,1,3,3\n" +
			"R,4,1,3,4\n" +
			"R,4,1,3,5\n" +
			"B,4,1,4,1\n" +
			"R,4,1,4,2\n" +
			"R,4,1,4,3\n" +
			"R,4,1,4,4\n" +
			"R,4,1,4,5\n" +
			"R,4,1,5,1\n" +
			"R,4,1,5,2\n" +
			"R,4,1,5,3\n" +
			"R,4,1,5,4\n" +
			"R,4,1,5,5\n" +
			"L,4,2,1,1\n" +
			"L,4,2,1,2\n" +
			"L,4,2,1,3\n" +
			"L,4,2,1,4\n" +
			"L,4,2,1,5\n" +
			"L,4,2,2,1\n" +
			"L,4,2,2,2\n" +
			"L,4,2,2,3\n" +
			"B,4,2,2,4\n" +
			"R,4,2,2,5\n" +
			"L,4,2,3,1\n" +
			"L,4,2,3,2\n" +
			"R,4,2,3,3\n" +
			"R,4,2,3,4\n" +
			"R,4,2,3,5\n" +
			"L,4,2,4,1\n" +
			"B,4,2,4,2\n" +
			"R,4,2,4,3\n" +
			"R,4,2,4,4\n" +
			"R,4,2,4,5\n" +
			"L,4,2,5,1\n" +
			"R,4,2,5,2\n" +
			"R,4,2,5,3\n" +
			"R,4,2,5,4\n" +
			"R,4,2,5,5\n" +
			"L,4,3,1,1\n" +
			"L,4,3,1,2\n" +
			"L,4,3,1,3\n" +
			"L,4,3,1,4\n" +
			"L,4,3,1,5\n" +
			"L,4,3,2,1\n" +
			"L,4,3,2,2\n" +
			"L,4,3,2,3\n" +
			"L,4,3,2,4\n" +
			"L,4,3,2,5\n" +
			"L,4,3,3,1\n" +
			"L,4,3,3,2\n" +
			"L,4,3,3,3\n" +
			"B,4,3,3,4\n" +
			"R,4,3,3,5\n" +
			"L,4,3,4,1\n" +
			"L,4,3,4,2\n" +
			"B,4,3,4,3\n" +
			"R,4,3,4,4\n" +
			"R,4,3,4,5\n" +
			"L,4,3,5,1\n" +
			"L,4,3,5,2\n" +
			"R,4,3,5,3\n" +
			"R,4,3,5,4\n" +
			"R,4,3,5,5\n" +
			"L,4,4,1,1\n" +
			"L,4,4,1,2\n" +
			"L,4,4,1,3\n" +
			"L,4,4,1,4\n" +
			"L,4,4,1,5\n" +
			"L,4,4,2,1\n" +
			"L,4,4,2,2\n" +
			"L,4,4,2,3\n" +
			"L,4,4,2,4\n" +
			"L,4,4,2,5\n" +
			"L,4,4,3,1\n" +
			"L,4,4,3,2\n" +
			"L,4,4,3,3\n" +
			"L,4,4,3,4\n" +
			"L,4,4,3,5\n" +
			"L,4,4,4,1\n" +
			"L,4,4,4,2\n" +
			"L,4,4,4,3\n" +
			"B,4,4,4,4\n" +
			"R,4,4,4,5\n" +
			"L,4,4,5,1\n" +
			"L,4,4,5,2\n" +
			"L,4,4,5,3\n" +
			"R,4,4,5,4\n" +
			"R,4,4,5,5\n" +
			"L,4,5,1,1\n" +
			"L,4,5,1,2\n" +
			"L,4,5,1,3\n" +
			"L,4,5,1,4\n" +
			"L,4,5,1,5\n" +
			"L,4,5,2,1\n" +
			"L,4,5,2,2\n" +
			"L,4,5,2,3\n" +
			"L,4,5,2,4\n" +
			"L,4,5,2,5\n" +
			"L,4,5,3,1\n" +
			"L,4,5,3,2\n" +
			"L,4,5,3,3\n" +
			"L,4,5,3,4\n" +
			"L,4,5,3,5\n" +
			"L,4,5,4,1\n" +
			"L,4,5,4,2\n" +
			"L,4,5,4,3\n" +
			"L,4,5,4,4\n" +
			"B,4,5,4,5\n" +
			"L,4,5,5,1\n" +
			"L,4,5,5,2\n" +
			"L,4,5,5,3\n" +
			"B,4,5,5,4\n" +
			"R,4,5,5,5\n" +
			"L,5,1,1,1\n" +
			"L,5,1,1,2\n" +
			"L,5,1,1,3\n" +
			"L,5,1,1,4\n" +
			"B,5,1,1,5\n" +
			"L,5,1,2,1\n" +
			"L,5,1,2,2\n" +
			"R,5,1,2,3\n" +
			"R,5,1,2,4\n" +
			"R,5,1,2,5\n" +
			"L,5,1,3,1\n" +
			"R,5,1,3,2\n" +
			"R,5,1,3,3\n" +
			"R,5,1,3,4\n" +
			"R,5,1,3,5\n" +
			"L,5,1,4,1\n" +
			"R,5,1,4,2\n" +
			"R,5,1,4,3\n" +
			"R,5,1,4,4\n" +
			"R,5,1,4,5\n" +
			"B,5,1,5,1\n" +
			"R,5,1,5,2\n" +
			"R,5,1,5,3\n" +
			"R,5,1,5,4\n" +
			"R,5,1,5,5\n" +
			"L,5,2,1,1\n" +
			"L,5,2,1,2\n" +
			"L,5,2,1,3\n" +
			"L,5,2,1,4\n" +
			"L,5,2,1,5\n" +
			"L,5,2,2,1\n" +
			"L,5,2,2,2\n" +
			"L,5,2,2,3\n" +
			"L,5,2,2,4\n" +
			"B,5,2,2,5\n" +
			"L,5,2,3,1\n" +
			"L,5,2,3,2\n" +
			"L,5,2,3,3\n" +
			"R,5,2,3,4\n" +
			"R,5,2,3,5\n" +
			"L,5,2,4,1\n" +
			"L,5,2,4,2\n" +
			"R,5,2,4,3\n" +
			"R,5,2,4,4\n" +
			"R,5,2,4,5\n" +
			"L,5,2,5,1\n" +
			"B,5,2,5,2\n" +
			"R,5,2,5,3\n" +
			"R,5,2,5,4\n" +
			"R,5,2,5,5\n" +
			"L,5,3,1,1\n" +
			"L,5,3,1,2\n" +
			"L,5,3,1,3\n" +
			"L,5,3,1,4\n" +
			"L,5,3,1,5\n" +
			"L,5,3,2,1\n" +
			"L,5,3,2,2\n" +
			"L,5,3,2,3\n" +
			"L,5,3,2,4\n" +
			"L,5,3,2,5\n" +
			"L,5,3,3,1\n" +
			"L,5,3,3,2\n" +
			"L,5,3,3,3\n" +
			"L,5,3,3,4\n" +
			"B,5,3,3,5\n" +
			"L,5,3,4,1\n" +
			"L,5,3,4,2\n" +
			"L,5,3,4,3\n" +
			"R,5,3,4,4\n" +
			"R,5,3,4,5\n" +
			"L,5,3,5,1\n" +
			"L,5,3,5,2\n" +
			"B,5,3,5,3\n" +
			"R,5,3,5,4\n" +
			"R,5,3,5,5\n" +
			"L,5,4,1,1\n" +
			"L,5,4,1,2\n" +
			"L,5,4,1,3\n" +
			"L,5,4,1,4\n" +
			"L,5,4,1,5\n" +
			"L,5,4,2,1\n" +
			"L,5,4,2,2\n" +
			"L,5,4,2,3\n" +
			"L,5,4,2,4\n" +
			"L,5,4,2,5\n" +
			"L,5,4,3,1\n" +
			"L,5,4,3,2\n" +
			"L,5,4,3,3\n" +
			"L,5,4,3,4\n" +
			"L,5,4,3,5\n" +
			"L,5,4,4,1\n" +
			"L,5,4,4,2\n" +
			"L,5,4,4,3\n" +
			"L,5,4,4,4\n" +
			"B,5,4,4,5\n" +
			"L,5,4,5,1\n" +
			"L,5,4,5,2\n" +
			"L,5,4,5,3\n" +
			"B,5,4,5,4\n" +
			"R,5,4,5,5\n" +
			"L,5,5,1,1\n" +
			"L,5,5,1,2\n" +
			"L,5,5,1,3\n" +
			"L,5,5,1,4\n" +
			"L,5,5,1,5\n" +
			"L,5,5,2,1\n" +
			"L,5,5,2,2\n" +
			"L,5,5,2,3\n" +
			"L,5,5,2,4\n" +
			"L,5,5,2,5\n" +
			"L,5,5,3,1\n" +
			"L,5,5,3,2\n" +
			"L,5,5,3,3\n" +
			"L,5,5,3,4\n" +
			"L,5,5,3,5\n" +
			"L,5,5,4,1\n" +
			"L,5,5,4,2\n" +
			"L,5,5,4,3\n" +
			"L,5,5,4,4\n" +
			"L,5,5,4,5\n" +
			"L,5,5,5,1\n" +
			"L,5,5,5,2\n" +
			"L,5,5,5,3\n" +
			"L,5,5,5,4\n" +
			"B,5,5,5,5\n";
}
