package it.polimi.ingsw.view.other_elements;

public class VLogin {
    private String name, password;

    public VLogin() {

    }

    public VLogin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
}
