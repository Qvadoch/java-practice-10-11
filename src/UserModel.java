import java.io.Serial;
import java.io.Serializable;

public abstract class UserModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

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

    public abstract void getRole();
}
