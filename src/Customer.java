// Customer.java
public class Customer extends UserModel {
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