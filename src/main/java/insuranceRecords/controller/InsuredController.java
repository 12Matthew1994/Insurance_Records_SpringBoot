package insuranceRecords.controller;

import insuranceRecords.models.dto.InsuranceDTO;
import insuranceRecords.models.dto.InsuredDTO;
import insuranceRecords.models.dto.mapper.InsuredMapper;
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
@RequestMapping("/insured")
public class InsuredController {


    @Autowired
    private InsuredService insuredService;

    @Autowired
    private InsuredMapper insuredMapper;

    @Autowired
    private InsuranceService insuranceService;


    @GetMapping
    public String renderIndex(Model model) {
        List<InsuredDTO> insured = insuredService.getALL();
        model.addAttribute("insured", insured);
        return "pages/insured/index";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("create")
    public String renderCreateForm(@ModelAttribute InsuredDTO insured) {
        return "pages/insured/create";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("create")
    public String createInsured(@ModelAttribute InsuredDTO insured,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors())
            return renderCreateForm(insured);

        insuredService.create(insured);
        redirectAttributes.addFlashAttribute("success", "Pojistník nahrán.");

        return "redirect:/insured";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("detail/{insuranceId}")
    public String renderDetail(@PathVariable long insuranceId, Model model) {
        List<InsuranceDTO> allInsurances = insuranceService.getALL();
        model.addAttribute("allInsurances", allInsurances);

        return "pages/insured/detail";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("edit/{insuredId}")
    public String renderEditForm(@PathVariable long insuredId,
                                 InsuredDTO insured) {
        InsuredDTO fetchedInsured= insuredService.getById(insuredId);
        insuredMapper.updateInsuredDTO(fetchedInsured, insured);

        return "pages/insured/edit";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("edit/{insuredId}")
    public String editInsured(@PathVariable long insuredId,
                                @Valid InsuredDTO insured,
                                BindingResult result,
                                RedirectAttributes redirectAttributes){
        if (result.hasErrors())
            return renderEditForm(insuredId, insured);

        insured.setInsuredId(insuredId);
        insuredService.edit(insured);
        redirectAttributes.addFlashAttribute("success", "Pojistník upraven");

        return "redirect:/insured";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("delete/{insuredId}")
    public String deleteInsured(@PathVariable long insuredId,
                                  RedirectAttributes redirectAttributes){
        insuredService.remove(insuredId);
        redirectAttributes.addFlashAttribute("success", "Pojistník smazán");

        return "redirect:/insured";
    }

    @ExceptionHandler({InsuranceNotFoundException.class})
    public String handleInsuredNotFoundException(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("error", "Pojistník nenalezen.");
        return "redirect:/insured";
    }
}
