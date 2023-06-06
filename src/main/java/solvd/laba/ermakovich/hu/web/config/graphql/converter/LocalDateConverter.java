package solvd.laba.ermakovich.hu.web.config.graphql.converter;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

/**
 * @author Ermakovich Kseniya
 */
@Component("LocalDate")
public class LocalDateConverter implements Coercing<LocalDate, String> {

    @Override
    public String serialize(final Object dataFetcherResult)
            throws CoercingSerializeException {
        return dataFetcherResult.toString();
    }

    @Override
    public LocalDate parseValue(final Object input)
            throws CoercingParseValueException {
        return LocalDate.parse((String) input);
    }

    @Override
    public LocalDate parseLiteral(final Object input)
            throws CoercingParseLiteralException {
        return LocalDate.parse(((StringValue) input).getValue());
    }

}
