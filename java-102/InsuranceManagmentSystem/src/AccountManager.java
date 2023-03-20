import java.util.TreeSet;

public class AccountManager {
    TreeSet<Account> accounts = new TreeSet<>();

    public Account login(String email, String password) {
        try{
            for (Account account : accounts) {
                if(account.login(email, password) != null){
                    System.out.println("Login successful");
                    return account;

                }
            }
        }catch (InvalidAuthenticationException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


}
