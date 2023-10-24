package insuranceRecords.models.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {

    private long userId;

    @Email(message = "Vyplňte validní email")
    @NotBlank(message = "Vyplňte uživatelský email")
    @NotNull(message = "Vyplňte uživatelský email")
    private String email;

    @NotBlank(message = "Vyplňte uživatelské heslo")
    @NotNull(message = "Vyplňte uživatelské heslo")
    @Size(min = 6, message = "Heslo musí mít alespoň 6 znaků")
    private String password;

    @NotBlank(message = "Vyplňte uživatelské heslo")
    @NotNull(message = "Vyplňte uživatelské heslo")
    @Size(min = 6, message = "Heslo musí mít alespoň 6 znaků")
    private String confirmPassword;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    private InsuredDTO insuredDTO;

    public InsuredDTO getInsuredDTO() {
        return insuredDTO;
    }

    public void setInsuredDTO(InsuredDTO insuredDTO) {
        this.insuredDTO = insuredDTO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}


