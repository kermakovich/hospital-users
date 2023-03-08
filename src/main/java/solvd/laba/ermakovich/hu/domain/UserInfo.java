package solvd.laba.ermakovich.hu.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Id
    private Long id;
    private UUID externalId;
    private String name;
    private String surname;
    private String fatherhood;
    private LocalDate birthday;
    private String email;
    private String password;
    private UserRole role;

}
