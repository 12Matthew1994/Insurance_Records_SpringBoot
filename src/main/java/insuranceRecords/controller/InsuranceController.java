package insuranceRecords.controller;


import insuranceRecords.models.dto.InsuranceDTO;
import insuranceRecords.models.dto.InsuredDTO;
import insuranceRecords.models.dto.mapper.InsuranceMapper;
import insuranceRecords.models.exceptions.InsuranceNotFoundException;
import insuranceRecords.models.services.InsuranceService;
import insuranceRecords.models.services.InsuredService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private InsuranceMapper insuranceMapper;

    @Autowired
    private InsuredService insuredService;


    @GetMapping
    public String renderIndex(Model model){
        List<InsuranceDTO> insurance = insuranceService.getALL();
        model.addAttribute("insurance", insurance);
        return "pages/insurance/index";
    }

    @GetMapping("create")
    public String renderCreateForm (@ModelAttribute InsuranceDTO insurance) {
        return "pages/insurance/create";
    }


    @PostMapping("create")
    public String createInsurance(@Valid @ModelAttribute InsuranceDTO insurance,
                                  BindingResult result,
                                  RedirectAttributes redirectAttributes){
        if (result.hasErrors())
            return renderCreateForm(insurance);

        insuranceService.createInsurance(insurance);
        redirectAttributes.addFlashAttribute("success", "Děkujeme za sjednání pojištění. Brzy vás budeme kontaktovat");

        return "redirect:/insurance";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("detail/{insuranceId}")
    public String renderDetail(@PathVariable long insuranceId,
                               Model model) {
        InsuranceDTO insurance = insuranceService.getById(insuranceId);
        InsuredDTO insured = insuredService.getByInsuranceId(insuranceId);
        model.addAttribute("insurance", insurance);
        model.addAttribute("insured", insured);

        return "pages/insurance/detail";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("edit/{insuranceId}")
    public String renderEditForm(@PathVariable long insuranceId,
                                 InsuranceDTO insurance) {
        InsuranceDTO fetchedInsurance= insuranceService.getById(insuranceId);
        insuranceMapper.updateInsuranceDTO(fetchedInsurance, insurance);

        return "pages/insurance/edit";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("edit/{insuranceId}")
    public String editInsurance(@PathVariable long insuranceId,
                                @Valid InsuranceDTO insurance,
                                BindingResult result,
                                RedirectAttributes redirectAttributes){
        if (result.hasErrors())
            return renderEditForm(insuranceId, insurance);

        insurance.setInsuranceId(insuranceId);
        insuranceService.edit(insurance);
        redirectAttributes.addFlashAttribute("success", "Pojištění upraveno");

        return "redirect:/insurance";
    }


    @GetMapping("delete/{insuranceId}")
    public String deleteInsurance(@PathVariable long insuranceId,
                                  RedirectAttributes redirectAttributes){
        insuranceService.remove(insuranceId);
        redirectAttributes.addFlashAttribute("success", "Pojištění smazáno");

        return "redirect:/insurance";
    }

    @ExceptionHandler({InsuranceNotFoundException.class})
    public String handleInsuranceNotFoundException(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("error", "Pojištění nenalezeno.");
        return "redirect:/insurance";
    }
}
