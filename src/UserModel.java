public abstract class UserModel {
    private String login;
    private String passw;

    public UserModel(String login, String passw) {
        this.login = login;
        this.passw = passw;
    }

    public String getLogin() {
        return login;
    }

    public String getPassw() {
        return passw;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public abstract void getRole();
}
