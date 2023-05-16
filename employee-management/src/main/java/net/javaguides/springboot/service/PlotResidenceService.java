package net.javaguides.springboot.service;

import net.javaguides.springboot.model.PlotRegistration;
import net.javaguides.springboot.repository.PlotResidenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlotResidenceService {

    @Autowired
    private PlotResidenceRepo residenceRepo;

    public PlotRegistration createPlot(PlotRegistration registration){
        return residenceRepo.save(registration);
    }

    public List<PlotRegistration> findByUser(String name){
        return residenceRepo.findByUserName(name);
    }
    public Optional<PlotRegistration> findById(Long id){
        return residenceRepo.findById(id);
    }

    public List<PlotRegistration> findAll(){
        return residenceRepo.findAll();
    }

    public List<PlotRegistration> searchByName(String searchName) {

        String plot = searchName;
        List<PlotRegistration> allArtists = residenceRepo.searchByName(plot);
      return allArtists;

    }
    public Page<PlotRegistration> getPaginatedSearch(String name,Pageable pageable) {
        List<PlotRegistration> plots= searchByName(name);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), plots.size());

        return new PageImpl<>(plots.subList(start, end), pageable, plots.size());
    }


    public Page<PlotRegistration> getPaginatedPlots(Pageable pageable) {
        List<PlotRegistration> plots= findAll();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), plots.size());

        return new PageImpl<>(plots.subList(start, end), pageable, plots.size());
    }


}
