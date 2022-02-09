public interface IPiece <PieceType> {

    enum Team{
        WHITE,
        BLACK,
    }

    void setTeam();

    Team getTeam();

    void setPiece();

    // Get the type of the piece
    PieceType getPiece();

    // Get what team the piece is
    //Team getTeam(); TODO:

    // Move the piece from current square to another
    void move();

    // Returns a list of all legal moves TODO: change return type to list of moves
    int legalMoves();

    // this will return an icon not a string TODO: change return type to an icon.
    String getIcon();

}
