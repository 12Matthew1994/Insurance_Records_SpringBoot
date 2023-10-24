package insuranceRecords.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InsuredDTO {

    private long insuredId;

    public long getInsuredId() {
        return insuredId;
    }

    public void setInsuredId(long insuredId) {
        this.insuredId = insuredId;
    }

    @NotNull(message = "Vypište jméno")
    @NotBlank(message = "Vypište jméno")
    private String name;

    @NotNull(message = "Vyplňte příjmení")
    @NotBlank(message = "Vyplňte příjmení")
    private String surname;

    @NotNull(message = "Vyplňte město")
    @NotBlank(message = "Vyplňte město")
    private String city;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
