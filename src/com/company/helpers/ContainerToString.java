package com.company.helpers;

/**
 * Created by Belzee on 13.06.2017.
 */
public class ContainerToString {

    public String convert(OdchylenieContainer[] odchylenieContainer) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 101; i++) {
            for (OdchylenieContainer anOdchylenieContainer : odchylenieContainer) {
                sb.append(anOdchylenieContainer.getPoints().get(i))
                        .append("\t")
                        .append(anOdchylenieContainer.getNumeral().get(i))
                        .append("\t")
                        .append(anOdchylenieContainer.getAnalytic().get(i))
                        .append("\n");
            }
        }

        return sb.toString();
    }

    public String convert(double[][] matrix, double deltaX, double deltaT) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sb.append(deltaT * i)
                        .append("\t")
                        .append(deltaX * j)
                        .append("\t")
                        .append(matrix[i][j])
                        .append("\n");
            }
        }

        return sb.toString();
    }

}
