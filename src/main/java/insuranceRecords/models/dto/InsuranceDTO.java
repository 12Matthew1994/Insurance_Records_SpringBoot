package insuranceRecords.models.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class InsuranceDTO {


    private long insuranceId;


    @NotNull(message = "Vyplňte pojištění")
    private String insuranceType;

    @Positive(message = "Vyplňte cenu")
    private double price;

    @Future(message = "Špatné datum")
    @NotNull(message = "Vyplňte datum")
    private LocalDate validFrom;

    @Future(message = "Špatné datum")
    @NotNull(message = "Vyplňte datum")
    private LocalDate validTo;

    public long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public void setInsured(InsuredDTO insuredDTO) {
    }
}
