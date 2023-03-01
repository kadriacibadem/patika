import java.util.Random;

public class Mine extends BattleLoc{
    public Mine(Player player ) {
        super(player,"Maden",new Snake(),"Savaş ve Ganimet",5);
    }

    public void itemDrop(Player player){
        Random random = new Random();
        System.out.println("İtem");
        int randomNumber = random.nextInt(1,101);
        if(randomNumber >= 1 && randomNumber <=15 ){
            randomNumber = random.nextInt(1,101);
            System.out.println("Silah");

            if(randomNumber >= 1 && randomNumber <=20){
                System.out.print(Weapon.weapons()[0].getName());
                player.getInventory().setWeapon(Weapon.weapons()[0]);

            }
            else if(randomNumber >= 21 && randomNumber <= 51){

                System.out.print(Weapon.weapons()[1].getName());
                player.getInventory().setWeapon(Weapon.weapons()[1]);
            }
            else{
                System.out.print(Weapon.weapons()[2].getName());
                player.getInventory().setWeapon(Weapon.weapons()[2]);
            }
        }
        else if(randomNumber >= 16 && randomNumber <= 31){
            randomNumber = random.nextInt(1,101);
            System.out.println("Zırh");
            if(randomNumber >= 1 && randomNumber<=50){

                System.out.print(Armor.armor()[0].getName());
                player.getInventory().setArmor(Armor.armor()[0]);
            }
            else if(randomNumber >= 51 && randomNumber >= 81){
                System.out.print(Armor.armor()[1].getName());
                player.getInventory().setArmor(Armor.armor()[1]);
            }
            else{
                System.out.print(Armor.armor()[2].getName());
                player.getInventory().setArmor(Armor.armor()[2]);
            }

        }

        else if(randomNumber >= 32 && randomNumber <=57){
            System.out.println("Para");
            randomNumber = random.nextInt();
            if(randomNumber >= 1 && randomNumber <= 50 ){
                System.out.print("Miktar: 1");
                player.setMoney(player.getMoney() + 1);
            }
            else if(randomNumber >= 51 && randomNumber <= 81){
                System.out.print("Miktar: 5");
                player.setMoney(player.getMoney() + 5);
            }
            else{
                System.out.print("Miktar: 10");
                player.setMoney(player.getMoney() + 10);
            }

        }
        else {
            System.out.println("Boş");
        }


    }

}
