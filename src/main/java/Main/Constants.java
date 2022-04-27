package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class Constants {

    public static final int boardOffset = 64;
    public static final int defaultDisplayWidth = 64 * 8 + boardOffset * 2;
    public static final int defaultDisplayHeight = 64 * 8 + boardOffset * 2;


    public static BufferedImage pawnW;
    public static BufferedImage rookW;
    public static BufferedImage knightW;
    public static BufferedImage bishopW;
    public static BufferedImage queenW;
    public static BufferedImage kingW;
    public static BufferedImage pawnB;
    public static BufferedImage rookB;
    public static BufferedImage knightB;
    public static BufferedImage bishopB;
    public static BufferedImage queenB;
    public static BufferedImage kingB;
    public static BufferedImage rookWB;

    static {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            pawnW = ImageIO.read(Objects.requireNonNull(classLoader.getResource("pawnW.png")));
            rookW = ImageIO.read(Objects.requireNonNull(classLoader.getResource("rookW.png")));
            knightW = ImageIO.read(Objects.requireNonNull(classLoader.getResource("knightW.png")));
            bishopW = ImageIO.read(Objects.requireNonNull(classLoader.getResource("bishopW.png")));
            queenW = ImageIO.read(Objects.requireNonNull(classLoader.getResource("queenW.png")));
            kingW = ImageIO.read(Objects.requireNonNull(classLoader.getResource("kingW.png")));
            pawnB = ImageIO.read(Objects.requireNonNull(classLoader.getResource("pawnB.png")));
            rookB = ImageIO.read(Objects.requireNonNull(classLoader.getResource("rookB.png")));
            knightB = ImageIO.read(Objects.requireNonNull(classLoader.getResource("knightB.png")));
            bishopB = ImageIO.read(Objects.requireNonNull(classLoader.getResource("bishopB.png")));
            queenB = ImageIO.read(Objects.requireNonNull(classLoader.getResource("queenB.png")));
            kingB = ImageIO.read(Objects.requireNonNull(classLoader.getResource("kingB.png")));
            rookWB = ImageIO.read(Objects.requireNonNull(classLoader.getResource("rookWB.png")));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The piece images were not loaded in properly.");
        }
    }
}
