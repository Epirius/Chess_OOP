public class Knight extends Piece{

    public Knight(Team team) {
        super(team, Type.KNIGHT);
    }

    @Override
    public Move[] getPossibleMoves()  {
        return new Move[0];
    }
}
