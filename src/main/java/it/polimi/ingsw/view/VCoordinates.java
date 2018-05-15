package it.polimi.ingsw.view;

public class VCoordinates {
    private int x, y;

    public VCoordinates(int x, int y) throws IllegalCoordinatesException {
        if (x < 1 || y < 1 || x > 5 || y > 4)
            throw new IllegalCoordinatesException();
        else {
            this.x = x;
            this.y = y;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
