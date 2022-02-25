package View;

import Main.Constants;
import Model.Team;
import Model.Type;

import java.awt.image.BufferedImage;

/**
 * @author Felix Kaasa
 */
public class ViewPiece {
    public final Type type;
    public final Team team;
    public final int position;
    public final BufferedImage image;

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
    }
}
