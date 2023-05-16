package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.PlotRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlotResidenceRepo extends JpaRepository<PlotRegistration, Long> {

    List<PlotRegistration>  findByUserName(String name);

    @Query("SELECT m FROM PlotRegistration m WHERE m.userName LIKE ?1%")
    List<PlotRegistration> searchByName(String searchName);


}
