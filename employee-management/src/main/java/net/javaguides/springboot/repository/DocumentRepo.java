package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Documents;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.PlotRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepo extends JpaRepository<Documents, Long> {

    List<Documents> findByUser(String name);

    Documents findByPlotNumber(String plot);
}
