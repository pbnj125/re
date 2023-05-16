package net.javaguides.springboot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class PowerForm {

    @Id
    private long id;



}
