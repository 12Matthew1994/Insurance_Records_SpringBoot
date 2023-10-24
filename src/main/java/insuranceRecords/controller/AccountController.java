package insuranceRecords.controller;

import insuranceRecords.data.entities.InsuredEntity;
import insuranceRecords.data.entities.UserEntity;
import insuranceRecords.models.dto.InsuredDTO;
import insuranceRecords.models.dto.UserDTO;
import insuranceRecords.models.exceptions.DuplicateEmailException;
import insuranceRecords.models.exceptions.PasswordsDoNotEqualException;
import insuranceRecords.models.services.InsuredService;
import insuranceRecords.models.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private InsuredService insuredService;

    @GetMapping("login")
    public String renderLogin() {
        return "/pages/account/login.html";
    }

    @GetMapping("register")
    public String renderRegister(Model model) {
        UserDTO userDTO = new UserDTO();
        InsuredDTO insuredDTO = new InsuredDTO();
        userDTO.setInsuredDTO(insuredDTO);
        model.addAttribute("userDTO", userDTO);
        return "/pages/account/register";
    }

    @PostMapping("register")
    public String register(
            @Valid @ModelAttribute UserDTO userDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return "/pages/account/register";
        }

        try {
            userService.create(userDTO, false);
        } catch (DuplicateEmailException e) {
            result.rejectValue("email", "error", "Email je již používán.");
            return "/pages/account/register";
        } catch (PasswordsDoNotEqualException e) {
            result.rejectValue("password", "error", "Hesla se nerovnají.");
            result.rejectValue("confirmPassword", "error", "Hesla se nerovnají.");
            return "/pages/account/register";
        }

        redirectAttributes.addFlashAttribute("success", "Uživatel zaregistrován.");
        return "redirect:/account/login";
    }
}


