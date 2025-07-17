// Employee.java
public class Employee extends UserModel {
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