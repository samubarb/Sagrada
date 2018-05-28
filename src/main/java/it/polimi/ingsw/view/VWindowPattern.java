package it.polimi.ingsw.view;

public class VWindowPattern {
    private String name;
    private int token;
    private VDice[][] pattern;

    public VWindowPattern() {
        this.pattern = new VDice[5][4];
    }

    public void setConstraint(VColor color, VCoordinates xy) {
        this.pattern[xy.getX() - 1][xy.getY() - 1] = new VDice (0, color);
    }

    public void setConstraint(int value, VCoordinates xy) {
        this.pattern[xy.getX() - 1][xy.getY() - 1] = new VDice (value, VColor.RESET);
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setToken(int token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("VWindowPattern\n");
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 5; i++) {
                string.append("|");
                if (this.pattern[i][j] == null) {
                    string.append("   ");
                } else {
                    string.append(this.pattern[i][j].toString());
                }
                string.append("|");
            }
            string.append("\n");
        }
        return string.toString();
    }
}
