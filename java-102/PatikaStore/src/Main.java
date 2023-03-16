import java.util.*;


public class Main {
    public static void main(String[] args) {
        ArrayList<TradeMark> tradeMarks = new ArrayList<>();
        tradeMarks.add(new TradeMark(1, "Samsung"));
        tradeMarks.add(new TradeMark(2, "Lenovo"));
        tradeMarks.add(new TradeMark(3, "Apple"));
        tradeMarks.add(new TradeMark(4, "Huawei"));
        tradeMarks.add(new TradeMark(5, "Casper"));
        tradeMarks.add(new TradeMark(6, "Asus"));
        tradeMarks.add(new TradeMark(7, "HP"));
        tradeMarks.add(new TradeMark(8, "Xiaomi"));
        tradeMarks.add(new TradeMark(9, "Monster"));
        Collections.sort(tradeMarks, new Comparator<TradeMark>() {
                @Override
                public int compare(TradeMark o1, TradeMark o2) {
                    int f = o1.getName().compareTo(o2.getName());
                    return (f != 0) ? f : o2.getName().compareTo(o2.getName());
                }
        });

        ArrayList<Notebook> notebooks = new ArrayList<>();
        notebooks.add(new Notebook(1, "HUAWEI Matebook 14 ",tradeMarks.get(4) , 7000.0, 0, 10, 16, 512, 14.0));
        notebooks.add(new Notebook(2, "LENOVO V14 IGL ", tradeMarks.get(5), 3699.0, 0, 10, 8, 1024, 14.0));
        notebooks.add(new Notebook(3, "ASUS Tuf Gaming ", tradeMarks.get(1), 8199.0, 0, 10, 32, 2048, 15.6));

        ArrayList<MobilPhone> mobilPhones = new ArrayList<>();
        mobilPhones.add(new MobilPhone(1, "SAMSUNG GALAXY A51", tradeMarks.get(7), 3199.0, 0, 10, 6, 128, 6.5,4000.0,"Siyah"));
        mobilPhones.add(new MobilPhone(2, "iPhone 11 64 GB", tradeMarks.get(0), 7379.0, 0, 10, 6, 64, 6.1,3046.0,"Mavi"));
        mobilPhones.add(new MobilPhone(3, "Redmi Note 10 Pro 8GB", tradeMarks.get(8), 4012.0, 0, 10, 12, 128, 6.5,4000.0,"Beyaz"));





        while(true) {
            System.out.println("************************************");
            System.out.println("PatikaStore Ürün Yönetim Paneli !");
            System.out.println("1 - Notebook İşlemleri\n" +
                    "2 - Cep Telefonu İşlemleri\n" +
                    "3 - Marka Listele\n" +
                    "0 - Çıkış Yap");
            System.out.println("************************************");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("1 - Notebook Listele\n" +
                        "2 - Notebook Ekle\n" +
                        "3 - Notebook Sil\n" +
                        "0 - Ana Menüye Dön");
                int choice2 = scanner.nextInt();
                switch (choice2) {
                    case 1:
                        System.out.println();
                        System.out.println("Notebook Listesi");

                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("%-11s%4s%15s%27s%15s%10s%15s%15s%16s%n",
                                "ID","Ürün ","Ürün Adı","Fiyat","İndirim Oranı","Stok","Ekran","RAM","Depolama");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        for (Notebook n: notebooks){
                            System.out.printf("%-10s%-13s%-30s%-11s%-17s%-13s%-17s%-15s%-15s%n",n.getId(),n.getTradeMark().getName(),n.getName(),n.getPrice(),n.getDiscount()
                                    ,n.getStock(),n.getScreenSize(),n.getRam(),n.getMemory());
                        }
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        break;
                    case 2:
                        System.out.println("Eklemek istediğiniz notebookun markasını seçiniz");
                        for (int i = 0; i < tradeMarks.size(); i++) {
                            System.out.println(i + 1 + " - " + tradeMarks.get(i).getName());
                        }
                        int choice3 = scanner.nextInt();
                        System.out.println("Eklemek istediğiniz notebookun modelini giriniz");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        System.out.println("Eklemek istediğiniz notebookun fiyatını giriniz");
                        double price = scanner.nextDouble();
                        System.out.println("Eklemek istediğiniz notebookun stok miktarını giriniz");
                        int stock = scanner.nextInt();
                        System.out.println("Eklemek istediğiniz notebookun ram miktarını giriniz");
                        int ram = scanner.nextInt();
                        System.out.println("Eklemek istediğiniz notebookun hafıza miktarını giriniz");
                        int memory = scanner.nextInt();
                        System.out.println("Eklemek istediğiniz notebookun ekran boyutunu giriniz");
                        double screenSize = scanner.nextDouble();
                        notebooks.add(new Notebook(notebooks.size() + 1, name, tradeMarks.get(choice3 - 1), price, 0, stock, ram, memory, screenSize));
                        break;
                    case 3:
                        System.out.println("Silmek istediğiniz notebookun id'sini giriniz");
                        int id = scanner.nextInt();
                        notebooks.remove(id - 1);
                        break;
                    case 0:
                        break;
                }
            } else if (choice == 2) {
                System.out.println("1 - Cep Telefonu Listele\n" +
                        "2 - Cep Telefonu Ekle\n" +
                        "3 - Cep Telefonu Sil\n" +
                        "0 - Ana Menüye Dön");
                int choice4 = scanner.nextInt();
                switch (choice4) {
                    case 1:
                        System.out.println("Cep Telefonu Listesi");

                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("%-11s%4s%15s%27s%11s%20s%10s%15s%16s%n",
                                "ID","Ürün ","Ürün Adı","Fiyat","Depolama","Ekran","Pil","RAM","Renk");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        for (MobilPhone mobilphone: mobilPhones){

                            System.out.printf("%-10s%-13s%-30s%-11s%-17s%-13s%-17s%-15s%-15s%n",mobilphone.getId(),mobilphone.getTradeMark().getName(),mobilphone.getName(),mobilphone.getPrice()
                                    ,mobilphone.getMemory(),mobilphone.getScreenSize(),mobilphone.getPower(),mobilphone.getRam(),mobilphone.getColor()
                            );
                        }
                        break;
                    case 2:
                        System.out.println("Eklemek istediğiniz cep telefonunun markasını seçiniz");
                        for (int i = 0; i < tradeMarks.size(); i++) {
                            System.out.println(i + 1 + " - " + tradeMarks.get(i).getName());
                        }
                        int choice5 = scanner.nextInt();
                        System.out.println("Eklemek istediğiniz cep telefonunun modelini giriniz");
                        String name = scanner.nextLine();
                        System.out.println("Eklemek istediğiniz cep telefonunun fiyatını giriniz");
                        double price = scanner.nextDouble();
                        System.out.println("Eklemek istediğiniz cep telefonunun stok miktarını giriniz");
                        int stock = scanner.nextInt();
                        System.out.println("Eklemek istediğiniz cep telefonunun ram miktarını giriniz");
                        int ram = scanner.nextInt();
                        System.out.println("Eklemek istediğiniz cep telefonunun hafıza miktarını giriniz");
                        int memory = scanner.nextInt();
                        System.out.println("Eklemek istediğiniz cep telefonunun ekran boyutunu giriniz");
                        double screenSize = scanner.nextDouble();
                        System.out.println("Eklemek istediğiniz cep telefonunun batarya kapasitesini giriniz");
                        double batteryCapacity = scanner.nextDouble();
                        System.out.println("Eklemek istediğiniz cep telefonunun renk seçeneklerini giriniz");
                        String color = scanner.next();
                        mobilPhones.add(new MobilPhone(mobilPhones.size() + 1, name, tradeMarks.get(choice5 - 1), price, 0, stock, ram, memory, screenSize, batteryCapacity, color));
                        break;
                    case 3:
                        System.out.println("Silmek istediğiniz cep telefonunun id'sini giriniz");
                        int id = scanner.nextInt();
                        mobilPhones.remove(id - 1);
                        break;
                    case 0:
                        break;
                }
            } else if (choice == 3) {
                System.out.println("Markalarımız");
                System.out.println("-----------------------------------");
                for (TradeMark tradeMark : tradeMarks) {
                    System.out.println(tradeMark.getName());
                }
            } else if (choice == 0) {
                System.out.println("Çıkış yapılıyor...");
                break;
            }
        }
    }
}