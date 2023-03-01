public class ToolStore extends NormalLoc{
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    boolean onLocation() {
        System.out.println("----MAĞAZA----");


        boolean showMenu = true;

        while (showMenu) {
            System.out.println("1- Silahlar");
            System.out.println("2- Zırhlar");
            System.out.println("3- Çıkış Yap");
            String selectCase = Location.input.nextLine();
            while (!(selectCase.equals("1") || selectCase.equals("2") || selectCase.equals("3"))) {
                System.out.println("Geçersiz değer, tekrar giriniz.");
                selectCase = Location.input.nextLine();
            }

            switch (selectCase) {

                case "1":
                    printWeapon();
                    buyWeapon();
                    break;
                case "2":
                    printArmor();
                    buyArmor();
                    break;
                case "3":
                    System.out.println("Bir daha bekleriz");
                    return true;
            }
        }
        return true;
    }

    public void printWeapon(){
        System.out.println("Silahlar");
        System.out.println();

        for(Weapon w: Weapon.weapons()){
            System.out.println(w.getId()+"- "+w.getName()+ " Para: "+w.getPrice()+", Hasar: "+w.getDamage());
        }
        System.out.println("0 - Çıkış yap.");
    }


    public void buyWeapon(){

        System.out.println("Bir silah seçiniz: ");
        int selectWeaponId = input.nextInt();
        while (selectWeaponId < 0 || selectWeaponId > Weapon.weapons().length){

            System.out.println("Geçersiz değer. tekrar giriniz.");
            selectWeaponId = input.nextInt();
        }

        if(selectWeaponId!=0){
        Weapon selectedWeapon = Weapon.getWeaponObjbyId(selectWeaponId);

        if(selectedWeapon != null){
            if(selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                System.out.println("Yeterli paranız bulunmamaktadır !!");
                printWeapon();
                buyWeapon();
            }
            else{

                // SATIN ALMANIN GERÇEKLEŞTİĞİ ALAN

                System.out.println(selectedWeapon.getName()+" silahını satın aldınız");
                int balance = this.getPlayer().getMoney()-selectedWeapon.getPrice();
                this.getPlayer().setMoney(balance);

                System.out.println("Kalan paranız: "+this.getPlayer().getMoney());

                System.out.println("Önceki silahınız "+this.getPlayer().getInventory().getWeapon().getName());
                this.getPlayer().getInventory().setWeapon(selectedWeapon);

                System.out.println("Yeni silahınız: "+this.getPlayer().getInventory().getWeapon().getName());
            }
        }
        }

    }


    public void buyArmor(){
        System.out.print("Bir zırh seçiniz: ");
        int selectArmorId =  input.nextInt();

        while(selectArmorId<0 && selectArmorId> Armor.armor().length){
            System.out.print("Geçersiz değer tekrar giriniz: ");
            selectArmorId = input.nextInt();
        }


        if(selectArmorId!=0){

        Armor selectedArmor = Armor.getWeaponObjById(selectArmorId);
        if(selectedArmor!=null){

            if (selectedArmor.getPrice() > this.getPlayer().getMoney()){
                System.out.println("Satın alma işlemi gerçekleşmedi.\n" +
                                    "Bakiye yetersiz.");

            }

            else{
                int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                System.out.println(selectedArmor.getName()+" zırhını satın aldınız.");
                System.out.println("Kalan para: "+balance);
                this.getPlayer().setMoney(balance);
                this.getPlayer().getInventory().setArmor(selectedArmor);


            }

        }
        }


    }

    public void printArmor(){
        System.out.println("Zırhlar");
        System.out.println();

       for(Armor a: Armor.armor()){
            System.out.println("Id: "+a.getId()+"\tZırh: "+a.getName()+ "\t\tPara: "+a.getPrice()+"\t\tBlok: "+a.getBlock());
        }
        System.out.println("0- Çıkış yap");



    }
}
