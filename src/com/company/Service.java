package com.company;

import com.company.helpers.OdchylenieContainer;

/**
 * Created by Belzee on 13.06.2017.
 */
public class Service {

    private Double deltaT = 1.0 / 200.0;
    private int numberOfPoints = 101;
    private Double deltaX = 1.0 / numberOfPoints;

    private double[] uVel;
    private double[] vVel;
    private double[] a;

    private double[][] matrix;

    private OdchylenieContainer[] odchylenieContainers;

    public Service() {
        this.a = new double[numberOfPoints];
        this.uVel = new double[numberOfPoints];
        this.vVel = new double[numberOfPoints];

        this.odchylenieContainers = new OdchylenieContainer[9];

        initializeFirstTask();
    }

    public Service(int k) {
        this.a = new double[numberOfPoints];
        this.uVel = new double[numberOfPoints];
        this.vVel = new double[numberOfPoints];
        this.odchylenieContainers = new OdchylenieContainer[9];

        this.matrix = new double[(int) (4.0 / deltaT) + 10][numberOfPoints];

        initializeSecondTask();
    }

    private void initializeFirstTask() {
        for (int i = 0; i < numberOfPoints; i++) {
            double x = i * deltaX;
            uVel[i] = Math.sin(Math.PI * x) - 0.5 * Math.sin(2.0 * Math.PI * x);
            vVel[i] = 0.0;
            a[i] = -1.0 * Math.pow(Math.PI, 2.0) * Math.cos(Math.PI * 0.0) * Math.sin(Math.PI * x) + 2.0 * Math.pow(Math.PI, 2.0) * Math.cos(2.0 * Math.PI * 0.0) * Math.sin(2.0 * Math.PI * 0.0);
        }
    }

    private void initializeSecondTask() {
        for (int i = 0; i < numberOfPoints; i++) {
            double x = i * deltaX;
            uVel[i] = Math.exp(-100.0 * Math.pow(x - 0.5, 2.0));
            vVel[i] = 0.0;
        }
    }

    private void evaluateVelocity() {
        for (int i = 0; i < numberOfPoints; i++) {
            vVel[i] = vVel[i] + (deltaT / 2.0) * a[i];
        }
    }

    private void evaluateUVelocity() {
        for (int i = 0; i < numberOfPoints; i++) {
            uVel[i] = uVel[i] + deltaT * vVel[i];
        }
    }

    private void evaluateA() {
        for (int i = 1; i < numberOfPoints - 1; i++) {
            a[i] = (uVel[i + 1] + uVel[i - 1] - 2.0 * uVel[i]) / Math.pow(deltaX, 2.0);
        }
    }

    private double calculateAnalyticUVelocityByIteration(int iteration, double t) {
        double x = iteration * deltaX;
        return Math.cos(Math.PI * t) * Math.sin(Math.PI * x) - 0.5 * Math.cos(2.0 * Math.PI * t) * Math.sin(2.0 * Math.PI * x);
    }

    public void executeFirstTask() {
        double t = 0.0;
        int k = 0;

        do {
            evaluateVelocity();
            evaluateUVelocity();
            evaluateA();
            evaluateVelocity();

            if (t % 0.25 == 0.0) {
                for (int i = 0; i < numberOfPoints; i++) {
                    odchylenieContainers[k].addNewValue(i * deltaX, uVel[i], calculateAnalyticUVelocityByIteration(i, t));
                }
                k++;
            }

            t += deltaT;
        } while (t <= 2.0);
    }

    public void executeSecondTask() {
        double t = 0.0;

        do {
            uVel[0] = 0.0;
            uVel[numberOfPoints - 1] = 0.0;

            System.arraycopy(uVel, 0, matrix[(int) (t / deltaT)], 0, numberOfPoints);

            evaluateVelocity();
            evaluateUVelocity();
            evaluateA();
            evaluateVelocity();

            t += deltaT;
        } while (t <= 4.0);
    }
}
