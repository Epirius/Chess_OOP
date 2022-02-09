public class Square {
    private final int Id;
    private IPiece piece;

    public Square(int Id){
        this.Id = Id;
    }

    //IPiece getPiece(){}
    //void setPiece(Piece piece){}
    int getSquareID(){
        return this.Id;
    }
}
