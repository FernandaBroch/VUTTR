package br.com.bossabox.vuttr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String link;

    private String description;

    @ElementCollection
    private List<String> tags;


}
