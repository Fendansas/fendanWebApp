package by.grodno.pvt.site.webappsample.dto;

import lombok.Data;

@Data
public class PostDTO {

    private Integer id;
    private String title;
    private String full_text;
    private int views;


}
