package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Documents;
import net.javaguides.springboot.repository.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentsService {
    @Autowired
    private DocumentRepo documentRepo;

    public Documents createDocument (Documents document){
        return documentRepo.save(document);
    }
    public Optional<Documents> findById(Long id){
       return documentRepo.findById(id);
    }

    public List<Documents>  findByUser(String name){
        return documentRepo.findByUser(name);
    }

    public Documents findByPlotNum(String plot){
        return documentRepo.findByPlotNumber(plot);
    }
}
