import java.io.Serial;

public class Customer extends UserModel{
    @Serial
    private static final long serialVersionUID = 3L;

    public Customer(String login, String passw) {
        super(login, passw);
    }

    @Override
    public void getRole() {
        System.out.println("Роль: Покупатель");
    }

    @Override
    public String toString() {
        return "Покупатель: " + getLogin();
    }
}