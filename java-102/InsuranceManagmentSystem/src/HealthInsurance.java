import java.util.Date;

public class HealthInsurance extends Insurance{
    public HealthInsurance(String insuranceName, double insurancePrice, Date insuranceStartDate, Date insuranceEndDate) {
        super(insuranceName, insurancePrice, insuranceStartDate, insuranceEndDate);
    }

    @Override
    void calculate() {
        this.setInsurancePrice(this.getInsurancePrice() * 2);
    }
}
