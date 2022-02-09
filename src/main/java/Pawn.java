public class Pawn implements IPiece <Pawn>{

    @Override
    public Team getTeam() {
        return Team.BLACK
    }

    @Override
    public Pawn getPiece() {
        return ;
    }

    @Override
    public void move() {

    }

    @Override
    public int legalMoves() {
        return 0;
    }

    @Override
    public String getIcon() {
        return null;
    }
}
