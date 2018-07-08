package it.polimi.ingsw.view.other_elements;

public class VSettings {
    private boolean CLIvsGUI; // false = CLI, true = GUI
    private boolean RMIvsSocket; // false = RMI, true = Socket

    /**
     * never used, had the goal of set some parameters in the View side.
     */
    public VSettings() {
        this.reset();
    }

    /**
     * settings to default values
     */
    public void reset() {
        this.CLIvsGUI = false; // false = cli, true = gui
        this.RMIvsSocket = false; // false = rmi, true = socket
    }

    /**
     * set gui as output choice
     */
    public void setGUI() {
        this.CLIvsGUI = true;
    }

    /**
     * set cli as output choice
     */
    public void setCLI() {
        this.CLIvsGUI = false;
    }

    /**
     * set socket as connection type
     */
    public void setSocket() {
        this.RMIvsSocket = true;
    }

    /**
     * set rmi as connection type
     */
    public void setRMI() {
        this.RMIvsSocket = false;
    }

    /**
     * get the cli state
     * @return true if cli was chosen, false if gui
     */
    public boolean isCLI () {
        return !CLIvsGUI;
    }

    /**
     * get the gui state
     * @return true if the gui was chosen, false if cli
     */
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
