package net.trileg.cos_similarity;

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    // 加速度データ（データ数，三軸x, y, z）
//    double[][] A = {
//        {1.5, 2.5, 3.5},
//        {3.5, 30.5, 5.5},
//    };
//    double[][] B = {
//        {3.5, 4.5, 5.5},
//        {5.5, 6.5, 7.5},
//    };
//    double[][] C = {
//        {25.0, 10.0, 0.0},
//        {30.0, 5.0, 1.0},
//    };

    Calculate calculate = new Calculate();
    ArrayList<ArrayList<Double>> first = calculate.dataToArrayList("/Users/trileg/Desktop/20151125-VectorTest-2/0.dat");
    ArrayList<ArrayList<Double>> second = calculate.dataToArrayList("/Users/trileg/Desktop/20151125-VectorTest-2/1.dat");
    ArrayList<ArrayList<Double>> third = calculate.dataToArrayList("/Users/trileg/Desktop/20151125-VectorTest-2/2.dat");

    ArrayList<ArrayList<Double>> ichi = calculate.dataToArrayList("/Users/trileg/Desktop/20151125-VectorTest-3/0.dat");
    ArrayList<ArrayList<Double>> ni = calculate.dataToArrayList("/Users/trileg/Desktop/20151125-VectorTest-3/1.dat");
    ArrayList<ArrayList<Double>> san = calculate.dataToArrayList("/Users/trileg/Desktop/20151125-VectorTest-3/2.dat");

    ArrayList<ArrayList<Double>> vecFirst = calculate.calculateAccelerometerVector(first);
    ArrayList<ArrayList<Double>> vecSecond = calculate.calculateAccelerometerVector(second);
    ArrayList<ArrayList<Double>> vecThird = calculate.calculateAccelerometerVector(third);

    ArrayList<ArrayList<Double>> vecIchi = calculate.calculateAccelerometerVector(ichi);
    ArrayList<ArrayList<Double>> vecNi = calculate.calculateAccelerometerVector(ni);
    ArrayList<ArrayList<Double>> vecSan = calculate.calculateAccelerometerVector(san);

    System.out.println("----------   vecIchi begin here  ----------");
    for (ArrayList<Double> i : vecIchi) {
      System.out.println(i.get(0)+" "+i.get(1)+" "+i.get(2));
    }
    System.out.println("----------   vecIchi end here   ----------");
    System.out.println();
    System.out.println("----------   vecNi begin here  ----------");
    for (ArrayList<Double> i : vecNi) {
      System.out.println(i.get(0)+" "+i.get(1)+" "+i.get(2));
    }
    System.out.println("----------   vecNi end here   ----------");
    System.out.println();
    System.out.println("----------   vecSan begin here  ----------");
    for (ArrayList<Double> i : vecSan) {
      System.out.println(i.get(0)+" "+i.get(1)+" "+i.get(2));
    }
    System.out.println("----------   vecSan end here   ----------");


    double[] similarity = calculate.cosSimilarity(vecFirst, vecIchi);
    double similarityResult = 0.0;
    for (int i = 0; i < similarity.length - 1; i++) {
      similarityResult = similarityResult + similarity[i];
    }
    similarityResult /= similarity.length - 1;
    System.out.println("similarity: "+similarityResult);
  }
}
