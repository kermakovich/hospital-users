package solvd.laba.ermakovich.hu.web.config.graphql.converter;

import graphql.language.FloatValue;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

/**
 * @author Ermakovich Kseniya
 */
@Component("BigDecimal")
public class BigDecimalConverter implements Coercing<BigDecimal, String> {

    @Override
    public String serialize(final Object dataFetcherResult)
            throws CoercingSerializeException {
        return dataFetcherResult.toString();
    }

    @Override
    public BigDecimal parseValue(final Object input)
            throws CoercingParseValueException {
        return new BigDecimal(((StringValue) input).getValue());
    }

    @Override
    public BigDecimal parseLiteral(final Object input)
            throws CoercingParseLiteralException {
        if (input instanceof StringValue stringValue) {
            return new BigDecimal(stringValue.getValue());
        } else {
            return ((FloatValue) input).getValue();
        }
    }

}
