package utils;

import model.Lut;
import model.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LutWriter {

    public static void saveLutFile(String path, Lut lut) throws IOException {
        String fileContent = "";
        for (Point p:lut.getPoints()){
            fileContent += p.getX() + "|" + p.getY() + "\n";
        }
        Files.write(Paths.get(path),fileContent.getBytes());
    }

    public static String generateFileName(int gain, double deadZone,  double gamma) {
        return "GA" + gain +
                "-DZ" + Math.round(deadZone) +
                "-GM" + Math.round(gamma*100) +
                ".lut";
    }

}
