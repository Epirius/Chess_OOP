public enum Direction {
    North(8),
    East(1),
    South(-8),
    West(-1),
    NorthEast(9),
    NorthWest(7),
    SouthEast(-7),
    SouthWest(-9);

    final int directionId;

    private Direction(int directionIdd){
        this.directionId = directionIdd;
    }

    public int getDirection(int currentSquare, Direction direction){
        int currentSquareY = IBoard.squareToCoordinates(currentSquare)[1];
        int newSquareY = IBoard.squareToCoordinates(currentSquare + directionId)[1];

        if (directionId > 1 && currentSquareY + 1 == newSquareY){return currentSquare + directionId;}
        else if (directionId < 2 && directionId > -2 && currentSquareY == newSquareY){return  currentSquare + directionId;}
        else if (directionId < -1 && currentSquareY -1 == newSquareY){return  currentSquare + directionId;}
        else {throw new IndexOutOfBoundsException("current y is: " + currentSquareY + "| new y is: " + newSquareY);}


    }


}
