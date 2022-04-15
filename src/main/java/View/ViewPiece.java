package View;

import Main.Constants;
import Model.Team;
import Model.Type;
import org.imgscalr.Scalr;

import java.awt.image.BufferedImage;

/**
 * @author Felix Kaasa
 */
public class ViewPiece  {
    public final Type type;
    public final Team team;
    public final int position;
    public final BufferedImage image;
    public final BufferedImage extraLargeImage;
    public final BufferedImage largeImage;
    public final BufferedImage smallImage;
    public final BufferedImage extraSmallImage;

    public ViewPiece(Type type, Team team, int position){
        this.type = type;
        this.team = team;
        this.position = position;

        switch (type) {
            case PAWN -> image = (team == Team.WHITE ? Constants.pawnW : Constants.pawnB);
            case ROOK -> image = (team == Team.WHITE ? Constants.rookW : Constants.rookB);
            case KNIGHT -> image = (team == Team.WHITE ? Constants.knightW : Constants.knightB);
            case BISHOP -> image = (team == Team.WHITE ? Constants.bishopW : Constants.bishopB);
            case QUEEN -> image = (team == Team.WHITE ? Constants.queenW : Constants.queenB);
            case KING -> image = (team == Team.WHITE ? Constants.kingW : Constants.kingB);
            default -> throw new RuntimeException("Could not get the image of a piece, because the type could not be recognized.");
        }

        this.extraLargeImage = Scalr.resize(this.image, Scalr.Method.BALANCED, (int) (this.image.getWidth() * 1.5f), (int) (this.image.getHeight() * 1.5f));
        this.largeImage = Scalr.resize(this.image, Scalr.Method.BALANCED, (int) (this.image.getWidth() * 1.2f), (int) (this.image.getHeight() * 1.2f));
        this.smallImage = Scalr.resize(this.image, Scalr.Method.BALANCED, (int) (this.image.getWidth() / 1.2f), (int) (this.image.getHeight() / 1.2f));
        this.extraSmallImage = Scalr.resize(this.image, Scalr.Method.BALANCED, (int) (this.image.getWidth() / 1.5f), (int) (this.image.getHeight() / 1.5f));
    }
}
