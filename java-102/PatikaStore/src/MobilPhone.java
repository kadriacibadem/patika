public class MobilPhone extends Product{
    private int ram;
    private int memory;
    private double screenSize;
    private double power;
    private String color;

    public MobilPhone(int id, String name, TradeMark tradeMark, double price, int discount, int stock, int ram, int memory, double screenSize, double power, String color) {
        super(id, name, tradeMark, price, discount, stock);
        this.ram = ram;
        this.memory = memory;
        this.screenSize = screenSize;
        this.power = power;
        this.color = color;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
