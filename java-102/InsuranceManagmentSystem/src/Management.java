import java.util.Scanner;

public class Management {

    AccountManager accountManager = new AccountManager();
    public void addUser(String eMail, String passWord){
        User user = new User("Kadri","Acıbadem",eMail,passWord,"Student",null);
        Account account = new Individual(user);
        accountManager.accounts.add(account);
    }
}

