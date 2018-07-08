package it.polimi.ingsw.view.other_elements;

import it.polimi.ingsw.view.exceptions.IllegalCoordinatesException;

public class VCoordinates {
    private int x, y;

    /**
     * represents the x and y coordinates in the window pattern cards or frames
     * @param x columns span
     * @param y rows span
     */
    public VCoordinates(int x, int y)  {
        this.x = x;
        this.y = y;
    }

    /**
     * check if the coordinates are in the rows (4) and columns (5) constraints
     * @throws IllegalCoordinatesException if constraints not respected
     */
    public void check() throws IllegalCoordinatesException {
        if (this.x < 1 || this.y < 1 || this.x > 5 || this.y > 4)
            throw new IllegalCoordinatesException();
    }

    /**
     * get the x coordinate
     * @return 1 <= x <= 5
     */
    public int getX() {
        return x;
    }

    /**
     * get the y coordinate
     * @return 1 <= y <= 4
     */
    public int getY() {
        return y;
    }

    /**
     * never used, get a printable version of the x and y values
     * @return printable String
     */
    @Override
    public String toString() {
        return "x: " + this.x + ", y: " + this.y;
    }
}
