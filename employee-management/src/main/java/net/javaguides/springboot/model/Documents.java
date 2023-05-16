package net.javaguides.springboot.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="documents")
@Setter
@Getter
@NoArgsConstructor
public class Documents {
    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    private Long id;
    private String user;
    private String profile;
    private long size;
    private String plotNumber;
    @Lob
    @Column(name = "content", columnDefinition = "LONGBLOB")
    private byte[] content;
}
