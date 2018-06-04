package it.polimi.ingsw.view.other_elements;

import it.polimi.ingsw.view.exceptions.IllegalCoordinatesException;

public class VCoordinates {
    private int x, y;

    public VCoordinates(int x, int y)  {
        this.x = x;
        this.y = y;
    }

    public void check() throws IllegalCoordinatesException {
        if (this.x < 1 || this.y < 1 || this.x > 5 || this.y > 4)
            throw new IllegalCoordinatesException();
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "x: " + this.x + ", y: " + this.y;
    }
}
