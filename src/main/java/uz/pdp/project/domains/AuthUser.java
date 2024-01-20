package uz.pdp.project.domains;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class AuthUser {
    private Long id;
    private String username;
    private String password;
    private String role;


}
