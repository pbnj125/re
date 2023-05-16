package net.javaguides.springboot.controller;

import net.javaguides.springboot.config.MyUserDetails;
import net.javaguides.springboot.model.Documents;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.PlotRegistration;
import net.javaguides.springboot.repository.PlotResidenceRepo;
import net.javaguides.springboot.service.DocumentsService;
import net.javaguides.springboot.service.PlotResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlotRegistrationController {
    @Autowired
    private PlotResidenceService plotResidenceService;

    @Autowired
    private DocumentsService documentsService;

    @PostMapping("/savePlot")
    public String saveEmployee(@ModelAttribute("plotRegistration")PlotRegistration plot, @AuthenticationPrincipal MyUserDetails userDetails) {
        // save employee to database
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        plot.setUserName(currentPrincipalName);
        plotResidenceService.createPlot(plot);
        return "redirect:/showNewPlot?success";
    }


    @GetMapping("/home/{id}")
    public String gethome(@PathVariable (value = "id")  String id,Model model){
        List<PlotRegistration> plot= plotResidenceService.findByUser(id);
        List<Documents> docu=documentsService.findByUser(id);
        model.addAttribute("listplot", plot);
        model.addAttribute("listdocu", docu);
        return "home";

    }

    @GetMapping("/showNewPlot")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
        PlotRegistration plot= new PlotRegistration();
        plot.setUserName(currentPrincipalName);
//		employee.setFirstName(currentPrincipalName);
        model.addAttribute("plotRegistration", plot);
        return "new_employee";
    }

    @GetMapping("/search")
    public String SearchArtist(@RequestParam("search") String name, Model model, @RequestParam(defaultValue = "0") int page) {
//        List<PlotRegistration> plots=plotResidenceService.searchByName(name);
//        model.addAttribute("listplot", plots);
        int pageSize = 10; // Number of rows to display per page
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<PlotRegistration> artistsPage = plotResidenceService.getPaginatedSearch(name,pageable);
        List<PlotRegistration> artists = artistsPage.getContent();

        model.addAttribute("listplot", artists);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", artistsPage.getTotalPages());

        return "index";

    }

    @GetMapping("/")
    public String viewHomePage(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 5; // Number of rows to display per page
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<PlotRegistration> artistsPage = plotResidenceService.getPaginatedPlots(pageable);
        List<PlotRegistration> artists = artistsPage.getContent();

        model.addAttribute("listplot", artists);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", artistsPage.getTotalPages());

        return "index";
//        List<PlotRegistration> plots=plotResidenceService.findAll();
//        model.addAttribute("listplot", plots);
//
//        return "index" ;
    }
}
