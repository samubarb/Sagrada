package it.polimi.ingsw.viewTest;

import it.polimi.ingsw.view.exceptions.TooManyPlayersException;
import it.polimi.ingsw.view.game_elements.VGame;
import it.polimi.ingsw.view.game_elements.VPlayer;
import it.polimi.ingsw.view.other_elements.VColor;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VGameTest {
    @Test
    public void printShadowTest() {
        for (VColor vc : VColor.values())
            print(vc.toString() + "\u2593");
        print(VColor.RESET.toString());
    }

    @Test
    public void printRankingTest() throws TooManyPlayersException {
        VGame game = new VGame();
        game.addVPlayer(new VPlayer("TestPlayer1", VColor.GREEN));
        game.addVPlayer(new VPlayer("TestPlayer2", VColor.BLUE));
        game.addVPlayer(new VPlayer("TestPlayer3", VColor.PURPLE));
        game.addVPlayer(new VPlayer("TestPlayer4", VColor.RED));
        game.setScore("TestPlayer1", 12);
        game.setScore("TestPlayer2", 42);
        game.setScore("TestPlayer3", 2);
        game.setScore("TestPlayer4", 7);

        game.notifyScore();
    }
}
