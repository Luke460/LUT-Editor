package utils;

import javafx.scene.chart.XYChart;
import model.Lut;
import model.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    public static final String LUT_SERIES_KEY = "LUT";

    public static Lut generateCustomLut(int lutSize, double deadZone, double gain, double gamma){
        int l = lutSize; // points number
        double d = deadZone; // dead zone percentage value
        double s = gain; // or saturation
        double g = gamma; // shape of the series
        Lut lut = new Lut();
        for(int i=0; i<=l; i++){
            double x = i;
            double y = x+(d*((l-x)/l)) - (l-s)*(x/l) + (((Math.pow((l/2d)-x,2))/l)-(l/4d))*((g*100d)/l)*((s/100d)-(d/100d));
            //         ^x      ^deadzone      ^saturarion                         ^gamma
            lut.addPoint(
                    round((i*1d)/(l*1d),3),
                    round(y*0.01,4)
            );
        }
        return lut;
    }

    public static XYChart.Series getSeriesFromLut(Lut lut, String name){
        XYChart.Series series = new XYChart.Series();
        series.setName(name);
        for(Point p:lut.getPoints()){
            series.getData().add(new XYChart.Data(p.getX()*100, p.getY()*100));
        }
        return series;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

}
