package functions;

import calculations.GaussMethod;


public class LogarithmicFunction implements Approximation {

    private String a;
    private String b;
    private String s;
    private String deviation;

    @Override
    public String getTypeOfFunction() {
        return "phi = a*ln(x) + b";
    }

    @Override
    public String getTitle() {
        return "��������������� �������";
    }

    @Override
    public double calculateY(double x) {
        double coefficientA = Double.parseDouble(a.replace(",", "."));
        double coefficientB = Double.parseDouble(b.replace(",", "."));
        return coefficientA * Math.log(x) + coefficientB;
    }

    @Override
    public void calculate(double[] x, double[] y) {
        double[][] array = new double[2][3];
        double sx = 0;
        double sxx = 0;
        double sxy = 0;
        double sy = 0;
        double n = x.length;
        double currentX;
        for(int i = 0; i < x.length; i++) {
            currentX = Math.log(x[i]);
            sx += currentX;
            sxx += Math.pow(currentX, 2);
            sy += y[i];
            sxy += currentX * y[i];
        }
        array[0][0] = sxx;
        array[0][1] = sx;
        array[0][2] = sxy;
        array[1][0] = sx;
        array[1][1] = n;
        array[1][2] = sy;

        double[] bounds = GaussMethod.solve(array);
        a = parse(bounds[0]);
        b = parse(bounds[1]);

        double e;
        double sum = 0;
        for(int i = 0; i < x.length; i++) {
            e = y[i] - (bounds[0] * Math.log(x[i]) + bounds[1]);
            sum += Math.pow(e, 2);
        }

        s = parse(sum);

        deviation = parse(Math.sqrt(sum / n));
    }

    @Override
    public String getA() {
        return a;
    }

    @Override
    public String getB() {
        return b;
    }

    @Override
    public String getC() {
        return "-";
    }

    @Override
    public String getS() {
        return s;
    }

    @Override
    public String getStandardDeviation() {
        return deviation;
    }
}
