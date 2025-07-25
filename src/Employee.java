import java.io.Serial;

public class Employee extends UserModel {
    @Serial
    private static final long serialVersionUID = 2L;

    public Employee(String login, String passw) {
        super(login, passw);
    }


    @Override
    public void getRole() {
        System.out.println("Роль: Сотрудник");
    }

    @Override
    public String toString() {
        return "Сотрудник: " + getLogin();
    }
}