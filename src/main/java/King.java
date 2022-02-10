public class King extends Piece{

    public King(Team team) {
        super(team, Type.KING);
    }

    @Override
    public Move[] getPossibleMoves() {
        return new Move[0];
    }
}
