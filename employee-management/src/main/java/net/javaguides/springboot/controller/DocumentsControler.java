package net.javaguides.springboot.controller;

import net.javaguides.springboot.config.MyUserDetails;
import net.javaguides.springboot.model.Documents;
import net.javaguides.springboot.model.PlotRegistration;
import net.javaguides.springboot.repository.PlotResidenceRepo;
import net.javaguides.springboot.service.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class DocumentsControler {

    @Autowired
    private DocumentsService documentsService;

    @Autowired
    private PlotResidenceRepo residenceRepo;

    @PostMapping("/upload/{id}")
    public String fileUpload(@PathVariable Long id, @RequestParam("file") MultipartFile file, Model model, @AuthenticationPrincipal MyUserDetails userDetails) throws IOException {
        PlotRegistration plot=residenceRepo.findById(id).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
if(file.getSize()!=0) {
    Documents documents = new Documents();
    String currentPrincipalName = authentication.getName();
    String fileName = file.getOriginalFilename();
    documents.setUser(currentPrincipalName);
    documents.setProfile(fileName);
    documents.setContent(file.getBytes());
    documents.setSize(file.getSize());
    documents.setPlotNumber(plot.getPlotNumber());
    documentsService.createDocument(documents);
    plot.setUploded(Boolean.TRUE);
    residenceRepo.save(plot);
    model.addAttribute("success", "file uploaded successfully");
}
            return "redirect:/home/"+ authentication.getName();

    }

    @GetMapping("/download")
    public void downloadFile (@Param("id") Long id, Model model , HttpServletResponse response) throws IOException {
        Optional<Documents> temp= documentsService.findById(id);
        if(temp!=null){
            Documents docu=temp.get();
            response.setContentType("application/octet-stream");
            String headerKey ="Content-Disposition";
            String headerValue="attachment; filename ="+ docu.getProfile();
            response.setHeader(headerKey, headerValue);
            ServletOutputStream outputStream=response.getOutputStream();
            outputStream.write(docu.getContent());
            outputStream.close();
        }
    }
    @GetMapping("/download2")
    public void downloadFile (@Param("id") String plot, Model model , HttpServletResponse response) throws IOException {

        Documents temp = documentsService.findByPlotNum(plot);
        if (temp != null) {
            Documents docu = temp;
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename =" + docu.getProfile();
            response.setHeader(headerKey, headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(docu.getContent());
            outputStream.close();
        }
    }
}
