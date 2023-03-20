import java.util.Date;

public abstract class Insurance {
    private String insuranceName;
    private double insurancePrice;
    private Date insuranceStartDate;
    private Date insuranceEndDate;

    public Insurance(String insuranceName, double insurancePrice, Date insuranceStartDate, Date insuranceEndDate) {
        this.insuranceName = insuranceName;
        this.insurancePrice = insurancePrice;
        this.insuranceStartDate = insuranceStartDate;
        this.insuranceEndDate = insuranceEndDate;
    }
    abstract void calculate();

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public double getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(double insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public Date getInsuranceStartDate() {
        return insuranceStartDate;
    }

    public void setInsuranceStartDate(Date insuranceStartDate) {
        this.insuranceStartDate = insuranceStartDate;
    }

    public Date getInsuranceEndDate() {
        return insuranceEndDate;
    }

    public void setInsuranceEndDate(Date insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }
}
