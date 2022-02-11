import java.util.ArrayList;
import java.util.List;

/**
 * @author Felix Kaasa
 */

public abstract class Piece implements IPiece{
    final Type type;
    final Team team;

    public Piece(Team team, Type type){
        this.team = team;
        this.type = type;
    }

    @Override
    public Team getTeam(){
        return this.team;
    }

    @Override
    public Type getPiece(){
        return type;
    }


    @Override
    public List<Move> getLegalMoves() {
        return new ArrayList<Move>(); //TODO
    }

    @Override
    public void move() {
        //TODO
    }

    @Override
    public void kill() {
        //TODO
    }
}
