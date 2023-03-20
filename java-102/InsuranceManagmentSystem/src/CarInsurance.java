import java.util.Date;

public class CarInsurance extends Insurance{
    public CarInsurance(String insuranceName, double insurancePrice, Date insuranceStartDate, Date insuranceEndDate) {
        super(insuranceName, insurancePrice, insuranceStartDate, insuranceEndDate);
    }

    @Override
    void calculate() {

    }
}
