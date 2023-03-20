public class AdressManager {
    public static void addAdress(Adress adress,User user){
        user.getAdresses().add(adress);
    }

    public static void removeAdress(Adress adress,User user){
        user.getAdresses().remove(adress);
    }

}
