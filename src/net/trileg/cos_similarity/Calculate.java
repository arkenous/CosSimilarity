package net.trileg.cos_similarity;

import java.io.*;
import java.util.ArrayList;

public class Calculate {
  public ArrayList<ArrayList<Double>> dataToArrayList(String dataPath) {
    ArrayList<ArrayList<Double>> result = new ArrayList<>();
    ArrayList<Double> axis = new ArrayList<>();

    try {
      File file = new File(dataPath);
      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

      String str = bufferedReader.readLine();
      while (str != null) {
        String[] array = str.split(" ");
        for (String item : array) axis.add(Double.valueOf(item));
        result.add(new ArrayList<>(axis));
        axis.clear();
        str = bufferedReader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return result;
  }


  public ArrayList<ArrayList<Double>> calculateAccelerometerVector(ArrayList<ArrayList<Double>> accelerometer) {
    //TODO 正確な式で変換部分を書き換える
    // 速度s
    double[] s = {0.0, 0.0, 0.0};
    // 位置p
    double[] p = {0.0, 0.0, 0.0};

    double[][] vector = new double[accelerometer.size()][accelerometer.get(0).size()];
    for (int time = 0; time < accelerometer.size(); time++) {
      for (int axis = 0; axis < accelerometer.get(time).size(); axis++) {
        s[axis] += accelerometer.get(time).get(axis);
        p[axis] += s[axis];
        vector[time][axis] = p[axis];
      }
    }

    ArrayList<ArrayList<Double>> result = new ArrayList<>();
    ArrayList<Double> axis = new ArrayList<>();

    for (double[] time : vector) {
      for (Double item : time) axis.add(item);
      result.add(new ArrayList<>(axis));
      axis.clear();
    }

    return result;
  }


  public double[] cosSimilarity(ArrayList<ArrayList<Double>> vecA, ArrayList<ArrayList<Double>> vecB) {
    double[] similarity = new double[vecA.size()];

    for (int i = 0; i < similarity.length; i++) {
      double AB = vecA.get(i).get(0) * vecB.get(i).get(0) + vecA.get(i).get(1) * vecB.get(i).get(1) + vecA.get(i).get(2) * vecB.get(i).get(2);
      double sizeA = Math.sqrt(Math.pow(vecA.get(i).get(0), 2) + Math.pow(vecA.get(i).get(1), 2) + Math.pow(vecA.get(i).get(2), 2));
      double sizeB = Math.sqrt(Math.pow(vecB.get(i).get(0), 2) + Math.pow(vecB.get(i).get(1), 2) + Math.pow(vecB.get(i).get(2), 2));

      similarity[i] = AB / (sizeA * sizeB);
    }
    return similarity;
  }
}
