public class Rook extends Piece{

    public Rook(Team team) {
        super(team, Type.ROOK);
    }

    @Override
    public Move[] getPossibleMoves() {
        return new Move[0];
    }
}
