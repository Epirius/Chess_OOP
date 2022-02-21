public enum Direction {
    NORTH(8),
    EAST(1),
    SOUTH(-8),
    WEST(-1),
    NORTHEAST(9),
    NORTHWEST(7),
    SOUTHEAST(-7),
    SOUTHWEST(-9);

    final int directionId;

    private Direction(int directionIdd){
        this.directionId = directionIdd;
    }


    /**
     * takes in a square ID and returns a square in a direction.
     * @param current square.
     * @return new square in a direction.
     */
    public int getDirection(int currentSquare){
        if ( currentSquare + directionId > 63 || currentSquare + directionId < 0){throw new IndexOutOfBoundsException("current square " + currentSquare + " + " + directionId + " = " + (currentSquare + directionId) + " which is out of bounds.");}

        int currentSquareY = IBoard.squareToCoordinates(currentSquare)[1];
        int newSquareY = IBoard.squareToCoordinates(currentSquare + directionId)[1];

        if (directionId > 1 && currentSquareY + 1 == newSquareY){return currentSquare + directionId;}
        else if (directionId < 2 && directionId > -2 && currentSquareY == newSquareY){return  currentSquare + directionId;}
        else if (directionId < -1 && currentSquareY -1 == newSquareY){return  currentSquare + directionId;}
        else {throw new IndexOutOfBoundsException("current y is: " + currentSquareY + " new y is: " + newSquareY + " which it should not be");}
    }
}
