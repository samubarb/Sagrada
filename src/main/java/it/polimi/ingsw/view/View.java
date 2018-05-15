package it.polimi.ingsw.view;

public interface View {
    void updateState(VGame game);
    VMove move(VDice dice, VCoordinates xy);
}
