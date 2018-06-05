package it.polimi.ingsw.controller.Server;

import it.polimi.ingsw.controller.Adapter;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.toolCards.ToolCard;
import it.polimi.ingsw.view.cards.VPrivateObjectiveCard;
import it.polimi.ingsw.view.cards.VPublicObjectiveCard;
import it.polimi.ingsw.view.cards.VToolCard;
import it.polimi.ingsw.view.cards.VWindowPattern;
import it.polimi.ingsw.view.exceptions.ConstraintNotValidException;
import it.polimi.ingsw.view.exceptions.InvalidPositionException;
import it.polimi.ingsw.view.exceptions.TooManyPlayersException;
import it.polimi.ingsw.view.game_elements.*;
import it.polimi.ingsw.view.other_elements.VColor;
import it.polimi.ingsw.view.other_elements.VCoordinates;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public final class AdapterCLI implements Adapter {

    public VWindowPattern patternToView(WindowPattern pattern) {
        VWindowPattern vPattern = new VWindowPattern();
        vPattern.setName(pattern.getName());
        vPattern.setToken(pattern.getFavorTokenToAssign());

        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 5; i++) {
                Coordinates xy = new Coordinates(i, j);
                try {
                    vPattern.setConstraint(diceToView(pattern.getDicePosition(xy)), coordinatesToView(xy)); // from model to Vpattern constraints
                } catch (ConstraintNotValidException e) {
                    println("Qualcosa è andato storto.");
                    println("Un vincolo del pattern non è corretto (solo colore OPPURE solo numero)");
                    errorExit();
                }
            }
        return vPattern;
    }

    public VWindowPatterns patternsToView(WindowPattern[] patterns) {
        VWindowPatterns vPatterns = new VWindowPatterns(patterns.length);
        for (int i = 0; i < patterns.length; i++) {
            try {
                vPatterns.add(patternToView(patterns[i]), i);
            } catch (InvalidPositionException e) {
                println("Errore: VWindowPatterns ha ricevuto una posizione non valida.");
                errorExit();
            }
        }
        return vPatterns;
    }

    public VFrame frameToView(Frame frame) { // Frame adapter from Model to View
        VFrame vFrame = new VFrame();
        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 5; i++) {
                Coordinates xy = new Coordinates(i, j);
                vFrame.setDice(diceToView(frame.getDice(xy)), coordinatesToView(xy));
            }
        return vFrame;
    }

    public VDice diceToView(Dice dice) { // Dice adapter from Model to View
        if (dice == null)
            return null;
        if (dice.getValue() == 0 && dice.getColor() == Color.UNCOLORED)
            return null;
        return new VDice(dice.getValue(), colorToView(dice.getColor()));
    }

    public Action booleanToAction(boolean action) {
        return action ? Action.INCREASE : Action.DECREASE;
    }

    public VCoordinates coordinatesToView(Coordinates xy) { // Coordinates adapter from View to Model
        int x, y;
        x = xy.getX() + 1;
        y = xy.getY() + 1;
        return new VCoordinates(x, y);
    }

    public Coordinates coordinatesToModel(VCoordinates xy) { // Coordinates adapter from Model to View
        int x, y;
        x = xy.getX() - 1;
        y = xy.getY() - 1;
        return new Coordinates(x, y);
    }

    public VPlayer playerToView(Player player) { // Player adapter from Model to View
        VPlayer vPlayer = new VPlayer(player.getName()); // initialize the player's name
        vPlayer.setColor(colorToView(player.getColor())); // set the player's color
        vPlayer.setFrame(frameToView(player.getFrame())); // set the player's frame
        vPlayer.setWpattern(patternToView(player.getWindowPattern())); // set the player's pattern
        return vPlayer;
    }

    public VGame gameToView(Game game) { // Game adapter from Model to View
        VGame vGame = new VGame();
        /* add all players in the game */
        for (Player p : game.getPlayers()) {
            try {
                vGame.addVPlayer(playerToView(p));
            }
            catch (TooManyPlayersException e) {
                println("Limite giocatori raggiunto. Il giocatore " + colorToView(p.getColor()) + p.getName() + VColor.RESET + " NON è stato aggiunto alla partita.");
            }
        }

        /* add all publicObjective cards in the game */
        for (PublicObjective pub : game.getPublicObjectives())
            vGame.addPublicObjective(publicObjectiveCardToView(pub));

        /* add CurrentDice */
        try {
            vGame.setVCurrentDice(currentDiceToView(game.getCurrentDice()));
        } catch (InvalidPositionException e) {
            println("Errore: CurrentDice ha ricevuto una posizione non valida.");
            errorExit();
        }


        /* add ToolCards */
        vGame.setTools(toolsToView(game.getToolCards()));

        /* add WindowPatterns */
        // vGame.setPatterns(patternsToView(game.getWindowPatternCards())); // to decomment in the future

        /* set round */
        vGame.setRound(game.getRound());

        /* set RoundTrack */
        vGame.setRoundTrack(roundTrackToView(game.getRoundTrack()));

        /* set Turn */
        vGame.setTurn(playerToView(game.getCurrentPlayer()));

        return vGame;
    }

    public VToolCards toolsToView(ToolCard[] tools) {
        VToolCards vTools = new VToolCards(tools.length);
        for (int i = 0; i < tools.length; i++) {
            try {
                vTools.add(toolCardToView(tools[i]), i);
            } catch (InvalidPositionException e) {
                println("Errore: ToolCards ha ricevuto una posizione non valida.");
                errorExit();
            }
        }
        return vTools;
    }

    public VToolCard toolCardToView(ToolCard toolCard) {
        return new VToolCard(toolCard.getName(), colorToView(toolCard.getColor()));
    }

    public VRoundTrack roundTrackToView(Dice[] track) {
        VRoundTrack vTrack = new VRoundTrack(track.length);
        for (int i = 0; i < track.length; i++)
            try {
                vTrack.add(diceToView(track[i]), i);
            }
            catch (InvalidPositionException e) {
                println("Errore: dimensioni non coerenti");
                errorExit();
            }
        return vTrack;
    }

    public VPrivateObjectiveCard privateObjectiveCardToView(PrivateObjective objCard) {
        return new VPrivateObjectiveCard(objCard.getName(), colorToView(objCard.getColor()));
    }

    public VPublicObjectiveCard publicObjectiveCardToView(PublicObjective objCard) {
        return new VPublicObjectiveCard(objCard.getName(), objCard.getPoints());
    }

    public VCurrentDice currentDiceToView(Dice[] currentDice) throws InvalidPositionException { // currentDice adapter from Model to View
        VCurrentDice vCurrentDice = new VCurrentDice(currentDice.length);
        for (int i = 0; i < currentDice.length; i++) {
            vCurrentDice.add(diceToView(currentDice[i]), i);
        }
        return vCurrentDice;
    }

    public VColor colorToView(Color color) { // Color adapter from Model  to View
        VColor vc = VColor.RESET;
        switch (color) {
            case RED:
                vc = VColor.RED;
                break;
            case GREEN:
                vc = VColor.GREEN;
                break;
            case BLUE:
                vc = VColor.BLUE;
                break;
            case PURPLE:
                vc = VColor.PURPLE;
                break;
            case YELLOW:
                vc = VColor.YELLOW;
                break;
            case UNCOLORED:
                vc = VColor.RESET;
                break;
        }
        return vc;
    }
}
