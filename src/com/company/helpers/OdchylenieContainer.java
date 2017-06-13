package com.company.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belzee on 13.06.2017.
 */
public class OdchylenieContainer {

    private List<Double> points;
    private List<Double> numeral;
    private List<Double> analytic;

    public OdchylenieContainer() {
        this.points = new ArrayList<>();
        this.numeral = new ArrayList<>();
        this.analytic = new ArrayList<>();
    }

    public void addNewValue(double x, double num, double anal) {
        this.points.add(x);
        this.numeral.add(num);
        this.analytic.add(anal);
    }

    public List<Double> getPoints() {
        return points;
    }

    public List<Double> getNumeral() {
        return numeral;
    }

    public List<Double> getAnalytic() {
        return analytic;
    }
}
