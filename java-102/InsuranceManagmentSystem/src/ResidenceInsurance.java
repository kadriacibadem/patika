import java.util.Date;

public class ResidenceInsurance extends Insurance{
    public ResidenceInsurance(String insuranceName, double insurancePrice, Date insuranceStartDate, Date insuranceEndDate) {
        super(insuranceName, insurancePrice, insuranceStartDate, insuranceEndDate);
    }

    @Override
    void calculate() {

    }
}
