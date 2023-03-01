import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int originalHealth;
    private int money;
    private String playerName;
    private String charName;
    private Scanner input = new Scanner(System.in);

    private Inventory inventory;





    public Player(String playerName){

        this.playerName = playerName;
        this.inventory = new Inventory();
    }

    public void printPlayerInfo(){

        System.out.println(
                "Silahınız: "+this.getInventory().getWeapon().getName()+
                "\tZırh: "+this.getInventory().getArmor().getName()+
                "\tBloklama: "+this.getInventory().getArmor().getBlock()+
                "\tSağlık: "+this.getHealth()+
                "\tHasar: "+this.getTotalDamage()+
                "\tPara: "+this.getMoney());
        System.out.println("---------------------------------------------------------------------------------------------");

    }

    public int getTotalDamage(){
        return damage +this.getInventory().getWeapon().getDamage();
    }
    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }

    // -------SELECTION-------
    public void selectChar(){
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Karakterler: ");
        System.out.println("---------------------------------------------------------------------------------------------");

        GameCharacter[] charList = {new Samurai(),new Archer(),new Knight()};

        for (GameCharacter gc: charList){
            System.out.println("Id: " +gc.getId()+
                    "\t Karakter: "+gc.getName()+
              "\t\t Hasar: "+gc.getDamage()+
              "\t\t Sağlık: "+gc.getHealth()+
               "\t\t Para: "+gc.getMoney());
        }
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Bir karakter seçiniz.");
        String selectChar = input.nextLine();

        switch (selectChar){

            case "1": initPlayer(charList[0]);
            break;

            case "2": initPlayer(charList[1]);
            break;

            case "3": initPlayer(charList[2]);
            break;

            default:
                initPlayer(charList[0]);
        }

    }





    public void initPlayer(GameCharacter gameChar){

        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOriginalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());



    }
}
