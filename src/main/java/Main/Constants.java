package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class Constants {
    public static final int squareSize = 64;
    public static final int boardOffset = 64;
    public static final int displayWidth = squareSize * 8 + boardOffset * 2;
    public static final int displayHeight = squareSize * 8 + boardOffset * 2;


    // COLORS
    public static final Color colorBackground = new Color(90, 94, 89);
    public static final Color colorDarkSquare = new Color(21, 29, 36);
    public static final Color colorLightSquare = new Color(237, 132, 99);

    // Images
    public static  BufferedImage pawnW;
    public static  BufferedImage rookW;
    public static  BufferedImage knightW;
    public static  BufferedImage bishopW;
    public static  BufferedImage queenW;
    public static  BufferedImage kingW;

    public static  BufferedImage pawnB;
    public static  BufferedImage rookB;
    public static  BufferedImage knightB;
    public static  BufferedImage bishopB;
    public static  BufferedImage queenB;
    public static  BufferedImage kingB;

    static {
        try {
            pawnW = ImageIO.read(new File("./images/pawnW.png"));
            rookW = ImageIO.read(new File("./images/rookW.png"));
            knightW = ImageIO.read(new File("./images/knightW.png"));
            bishopW = ImageIO.read(new File("./images/bishopW.png"));
            queenW = ImageIO.read(new File("./images/queenW.png"));
            kingW = ImageIO.read(new File("./images/kingW.png"));

            pawnB = ImageIO.read(new File("./images/pawnB.png"));
            rookB = ImageIO.read(new File("./images/rookB.png"));
            knightB = ImageIO.read(new File("./images/knightB.png"));
            bishopB = ImageIO.read(new File("./images/bishopB.png"));
            queenB = ImageIO.read(new File("./images/queenB.png"));
            kingB = ImageIO.read(new File("./images/kingB.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The piece images were not loaded in properly.");
        }
    }

}
