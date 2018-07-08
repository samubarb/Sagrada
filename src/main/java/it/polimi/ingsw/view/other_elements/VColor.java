package it.polimi.ingsw.view.other_elements;

public enum VColor {
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    RESET("\u001B[0m");

    private final String colorCode;

    /**
     * represents the set of color used in the game
     * @param colorCode string to paint with that color the CLI output
     */
    VColor(String colorCode) {
        this.colorCode = colorCode;
    }

    /**
     * get the color code to paint text in the CLI
     * @return
     */
    @Override
    public String toString() {
        return colorCode;
    }
}
