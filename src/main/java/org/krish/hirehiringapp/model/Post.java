package org.krish.hirehiringapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="JobPost")
public class Post {
    @Id
    private Integer id;
    private String desc;
    private String profile;
    private String exp;
    private String techs;

}
