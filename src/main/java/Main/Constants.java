package Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        try {
            //src/main/resources/images/bishopB.png
            pawnW = ImageIO.read(new File("./src/main/resources/images/pawnW.png"));
            rookW = ImageIO.read(new File("./src/main/resources/images/rookW.png"));
            knightW = ImageIO.read(new File("./src/main/resources/images/knightW.png"));
            bishopW = ImageIO.read(new File("./src/main/resources/images/bishopW.png"));
            queenW = ImageIO.read(new File("./src/main/resources/images/queenW.png"));
            kingW = ImageIO.read(new File("./src/main/resources/images/kingW.png"));
            pawnB = ImageIO.read(new File("./src/main/resources/images/pawnB.png"));
            rookB = ImageIO.read(new File("./src/main/resources/images/rookB.png"));
            knightB = ImageIO.read(new File("./src/main/resources/images/knightB.png"));
            bishopB = ImageIO.read(new File("./src/main/resources/images/bishopB.png"));
            queenB = ImageIO.read(new File("./src/main/resources/images/queenB.png"));
            kingB = ImageIO.read(new File("./src/main/resources/images/kingB.png"));
            rookWB = ImageIO.read(new File("./src/main/resources/images/rookWB.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The piece images were not loaded in properly.");
        }
    }
}
