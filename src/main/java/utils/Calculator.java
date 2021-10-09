package utils;

import javafx.scene.chart.XYChart;

public class Calculator {

    public static XYChart.Series generateCustomLut(int l, double d, double s, double g){

        XYChart.Series series = new XYChart.Series();
        series.setName("LUT");

        for(int i=0; i<=l; i++){
            double x = i;
            double y = x+(d*((l-x)/l)) - (l-s)*(x/l) + (((Math.pow((l/2d)-x,2))/l)-(l/4d))*((g*100d)/l)*((s/100d)-(d/100d));
            //         ^x      ^deadzone      ^saturarion                         ^gamma
            series.getData().add(new XYChart.Data(x, y));
        }

        return series;

    }

}
