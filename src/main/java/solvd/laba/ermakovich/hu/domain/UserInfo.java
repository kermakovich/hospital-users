package solvd.laba.ermakovich.hu.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;

import static org.springframework.data.elasticsearch.annotations.FieldType.Date;

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
    @Field(type = Date, format = DateFormat.basic_date)
    private LocalDate birthday;
    private String email;
    private String password;
    private UserRole role;

}
