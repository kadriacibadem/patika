import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start(){
        System.out.println("Macera oyununa hoşgeldiniz !");
        System.out.println("Lütfen bir isim giriniz.");

        String playerName = input.next();

        Player player = new Player(playerName);

        System.out.println(player.getPlayerName()+" hoşgeldiniz!");
        player.selectChar();



        Location location = null;
        while (true){
            player.printPlayerInfo();
            System.out.println("#################### BÖLGELER ######################");
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("1- Güvenli Ev ==> Burası sizin için güvenli bir ev. Düşman yok");
            System.out.println("2- Eşya Dükkanı ==> Silah veya zırh satın alabilirsiniz.");
            System.out.println("3- Mağara ==> Ödül: <Yemek>, dikkatli ol zombi çıkabilir! ");
            System.out.println("4- Orman ==> Ödül: <Odun>,  dikkatli ol vampir çıkabilir! ");
            System.out.println("5- Nehir ==> Ödül: <Su> , dikkatli ol ayı çıkabilir! ");
            System.out.println("6- Maden ==> Özellik: Savaş ve Ganimet, dikkatli ol yılan çıkabilir!");
            System.out.println("0- Çıkış yap.");
            System.out.println("Lütfen gitmek istediğiniz bölgeyi seçiniz.");


            int selectLocation = input.nextInt();

            if(player.getInventory().isWater() &&
                    player.getInventory().isFood() &&
                    player.getInventory().isFirewood()
             && selectLocation == 1
            ){
                System.out.println("Tüm ödüller toplandı oyunu bitirdiniz. Tebrikler :)");
                break;

            }

            if(selectLocation==0){
                System.out.println("Çıkış yapıldı");
                break;
            }

            if( ( selectLocation==3 && player.getInventory().isFood() ) ||
                    (selectLocation==4 && player.getInventory().isFirewood())  ||
                    (selectLocation==5 && player.getInventory().isWater())
            ){

                System.out.println("Ödül kazandığınız bölgeye giriş yapamazsınız");
                continue;

            }


            switch(selectLocation){
                case 1: location = new SafeHouse(player);
                    break;

                case 2: location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4: location = new Forest(player);
                break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Mine(player);
                    break;
            }

            if(location==null){
                System.out.println("Geçerli bir değer giriniz.");
                continue;
            }

            if( !location.onLocation() ){
                System.out.println("Game Over.");
                break;
            }
        }

    }
}
