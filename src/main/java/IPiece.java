public interface IPiece <PieceType> {

    Team getTeam();

    Type getPiece();

    // Move the piece from current square to another
    void move();

    // Returns a list of all legal moves TODO: change return type to list of moves
    Move getLegalMoves();

    // this will return an icon not a string TODO: change return type to an icon.
    String getIcon();

}
