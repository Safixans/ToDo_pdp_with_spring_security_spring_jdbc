package uz.pdp.project.model;

import lombok.*;
import java.time.LocalDateTime;





@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Todo {
private Integer id;
private String title;
private String priority;
private LocalDateTime created_at;

}
