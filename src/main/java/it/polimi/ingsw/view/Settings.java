package it.polimi.ingsw.view;

public class Settings {
    private boolean CLIvsGUI; // false = CLI, true = GUI
    private boolean RMIvsSocket; // false = RMI, true = Socket

    public Settings() {
        reset();
    }

    public void reset() {
        this.CLIvsGUI = false;
        this.RMIvsSocket = false;
    }

    public void setGUI() {
        this.CLIvsGUI = true;
    }

    public void setCLI() {
        this.CLIvsGUI = false;
    }

    public void setSocket() {
        this.RMIvsSocket = true;
    }

    public void setRMI() {
        this.RMIvsSocket = false;
    }

    public boolean isCLI () {
        return !CLIvsGUI;
    }

    public boolean isGUI() {
        return CLIvsGUI;
    }

    public boolean isRMI () {
        return !RMIvsSocket;
    }

    public boolean isSocket() {
        return RMIvsSocket;
    }
}
