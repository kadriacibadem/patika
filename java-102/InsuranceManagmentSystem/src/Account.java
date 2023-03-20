import java.util.ArrayList;
import java.util.Objects;

public abstract class Account implements Comparable<Account>{
    private User user;
    private ArrayList<Insurance> insurances;
    private AuthenticationStatus authenticationStatus;

    public enum AuthenticationStatus{
        SUCCESS,
        FAILED,
    }
    public Account(User user) {
        this.user = user;
        this.insurances = new ArrayList<>();
    }
    final void showUserInfo(){
        System.out.println(
                "Name: " + user.getName() +
                "Surname: " + user.getSurname() +
                "Email: " + user.getEmail() +
                "Job: " + user.getJob() +
                "Adresses: " + user.getAdresses()
        );
    }

    public User login(String email, String password) throws InvalidAuthenticationException {
        if(user.getEmail().equals(email) && user.getPassword().equals(password)){
            authenticationStatus = AuthenticationStatus.SUCCESS;
            return user;
        }else{
            authenticationStatus = AuthenticationStatus.FAILED;
            throw new InvalidAuthenticationException("Invalid email or password");
        }
    }

    public abstract void addInsurance(Insurance insurance);
    public abstract void removeInsurance(Insurance insurance);

    public User getUser() {
        return user;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(user, account.user) &&
                Objects.equals(insurances, account.insurances) &&
                authenticationStatus == account.authenticationStatus;
    }
}
