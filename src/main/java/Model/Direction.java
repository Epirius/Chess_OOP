package Model;

public enum Direction {
    NORTH(8),
    EAST(1),
    SOUTH(-8),
    WEST(-1),
    NORTHEAST(9),
    NORTHWEST(7),
    SOUTHEAST(-7),
    SOUTHWEST(-9);

    public final int direction;

    private Direction(int directionIdd){
        this.direction = directionIdd;
    }


    /**
     * takes in a square ID and returns a square in a direction.
     * @param current square.
     * @return new square in a direction.
     */
    public int getDirection(int currentSquare){
        if ( currentSquare + direction > 63 || currentSquare + direction < 0){throw new IndexOutOfBoundsException("current square " + currentSquare + " + " + direction + " = " + (currentSquare + direction) + " which is out of bounds.");}

        int currentSquareY = IBoard.squareToCoordinates(currentSquare)[1];
        int newSquareY = IBoard.squareToCoordinates(currentSquare + direction)[1];

        if (direction > 1 && currentSquareY + 1 == newSquareY){return currentSquare + direction;}
        else if (direction < 2 && direction > -2 && currentSquareY == newSquareY){return  currentSquare + direction;}
        else if (direction < -1 && currentSquareY -1 == newSquareY){return  currentSquare + direction;}
        else {throw new IndexOutOfBoundsException("current y is: " + currentSquareY + " new y is: " + newSquareY + " which it should not be");}
    }
}
