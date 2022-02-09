public class Pawn implements IPiece{

    final Type type = Type.PAWN;
    final Team team;
    private Square position;
    String icon = "pawn.png";

    public Pawn(Team team){
        this.team = team;
    }

    @Override
    public Team getTeam() {
        return null;
    }

    @Override
    public Type getPiece() {
        return null;
    }

    @Override
    public void move() {

    }

    @Override
    public Move getLegalMoves() {
        return null;
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
