package Model;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Felix Kaasa
 */
public class testDirection {
    @Test
    public void testDirectionEnum(){
        //checking all directions in the middle of the board:
        Assert.assertEquals(36, Direction.NORTH.getDirection(28));
        Assert.assertEquals(37, Direction.NORTHEAST.getDirection(28));
        Assert.assertEquals(29, Direction.EAST.getDirection(28));
        Assert.assertEquals(21, Direction.SOUTHEAST.getDirection(28));
        Assert.assertEquals(20, Direction.SOUTH.getDirection(28));
        Assert.assertEquals(19, Direction.SOUTHWEST.getDirection(28));
        Assert.assertEquals(27, Direction.WEST.getDirection(28));
        Assert.assertEquals(35, Direction.NORTHWEST.getDirection(28));

        //checking at the top edge
        try {Direction.NORTH.getDirection(60);}
        catch (Throwable ex) {Assert.assertTrue(ex instanceof IndexOutOfBoundsException);}
        try {Direction.NORTHEAST.getDirection(60);}
        catch (Throwable ex) {Assert.assertTrue(ex instanceof IndexOutOfBoundsException);}
        try {Direction.NORTHWEST.getDirection(60);}
        catch (Throwable ex) {Assert.assertTrue(ex instanceof IndexOutOfBoundsException);}

        //checking at the bottom edge
        try {Direction.SOUTH.getDirection(3);}
        catch (Throwable ex) {Assert.assertTrue(ex instanceof IndexOutOfBoundsException);}
        try {Direction.SOUTHWEST.getDirection(3);}
        catch (Throwable ex) {Assert.assertTrue(ex instanceof IndexOutOfBoundsException);}
        try {Direction.SOUTHEAST.getDirection(3);}
        catch (Throwable ex) {Assert.assertTrue(ex instanceof IndexOutOfBoundsException);}

        //checking at the left edge
        try {Direction.WEST.getDirection(24);}
        catch (Throwable ex) {Assert.assertTrue(ex instanceof IndexOutOfBoundsException);}
        try {Direction.NORTHWEST.getDirection(24);}
        catch (Throwable ex) {Assert.assertTrue(ex instanceof IndexOutOfBoundsException);}
        try {Direction.SOUTHWEST.getDirection(24);}
        catch (Throwable ex) {Assert.assertTrue(ex instanceof IndexOutOfBoundsException);}

        //checking at the right edge
        try {Direction.EAST.getDirection(31);}
        catch (Throwable ex) {Assert.assertTrue(ex instanceof IndexOutOfBoundsException);}
        try {Direction.NORTHEAST.getDirection(31);}
        catch (Throwable ex) {Assert.assertTrue(ex instanceof IndexOutOfBoundsException);}
        try {Direction.SOUTHEAST.getDirection(31);}
        catch (Throwable ex) {Assert.assertTrue(ex instanceof IndexOutOfBoundsException);}
    }
}
