package solvd.laba.ermakovich.hu.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {

    private UUID externalId;
    private String name;
    private String surname;
    private String fatherhood;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthday;
    private String email;
    private String password;
    private UserRole role;

}
