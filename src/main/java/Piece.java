public abstract class Piece implements IPiece{
    final Type type;
    final Team team;
    private Square position;

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
    public abstract Move[] getPossibleMoves();

    @Override
    public Move[] getLegalMoves() {
        return new Move[0]; //TODO
    }

    @Override
    public void move() {
        //TODO
    }
}
