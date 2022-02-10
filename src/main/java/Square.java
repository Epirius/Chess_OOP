public class Square {
    private final int Id;
    private Piece piece;

    public Square(int Id){
        this.Id = Id;
    }

    int getSquareId(){
        return this.Id;
    }

    void setPiece(Piece piece){
        this.piece = piece;
    }

    Piece getPiece(){
        return this.piece;
    }

    public void removePiece(){
        piece = null;
    }
}
