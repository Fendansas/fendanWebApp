package by.grodno.pvt.site.webappsample.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String title;

    private String full_text;

    private int views;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User postUser;



}
