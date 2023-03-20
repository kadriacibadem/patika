import java.util.Date;

public class TravelInsurance extends Insurance{
    public TravelInsurance(String insuranceName, double insurancePrice, Date insuranceStartDate, Date insuranceEndDate) {
        super(insuranceName, insurancePrice, insuranceStartDate, insuranceEndDate);
    }

    @Override
    void calculate() {

    }
}
