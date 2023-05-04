package solvd.laba.ermakovich.hu.web;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Ermakovich Kseniya
 */
@Configuration
public final class WebConfig {

    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
    private static final String TIME_FORMAT = "HH:mm";


    @Bean
    public Jackson2ObjectMapperBuilderCustomizer mapper() {
        return builder -> builder.simpleDateFormat(DATE_TIME_FORMAT)
                .serializerByType(LocalDate.class,
                        new LocalDateSerializer(
                                DateTimeFormatter.ofPattern(DATE_FORMAT)
                        )
                )
                .deserializerByType(LocalDate.class,
                        new LocalDateDeserializer(
                                DateTimeFormatter.ofPattern(DATE_FORMAT)
                        )
                )
                .serializerByType(LocalDateTime.class,
                        new LocalDateTimeSerializer(
                                DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)
                        )
                )
                .deserializerByType(LocalDateTime.class,
                        new LocalDateTimeDeserializer(
                                DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)
                        )
                )
                .serializerByType(LocalTime.class,
                        new LocalTimeSerializer(
                                DateTimeFormatter.ofPattern(TIME_FORMAT)
                        )
                )
                .deserializerByType(LocalTime.class,
                        new LocalTimeDeserializer(
                                DateTimeFormatter.ofPattern(TIME_FORMAT)
                        )
                );
    }

    @Bean
    public Formatter<LocalDate> localDateFormatter() {
        return new Formatter<>() {

            @Override
            public LocalDate parse(@NonNull final String text,
                                   @NonNull final Locale locale) {
                return LocalDate.parse(text,
                        DateTimeFormatter.ofPattern(DATE_FORMAT)
                );
            }

            @Override
            public String print(final LocalDate object, final Locale locale) {
                return DateTimeFormatter.ofPattern(DATE_FORMAT)
                        .format(object);
            }
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
