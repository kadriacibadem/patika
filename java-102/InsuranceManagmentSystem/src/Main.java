import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        String password = scanner.nextLine();


        Management management = new Management();
        management.addUser(email, password);
        

        String loginEmail = scanner.nextLine();
        String loginPassword = scanner.nextLine();
        management.accountManager.login(email, password);
        management.accountManager.accounts.first().showUserInfo();

    }
}