package solvd.laba.ermakovich.hu.web.config.graphql.converter;

import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.springframework.stereotype.Component;

/**
 * @author Ermakovich Kseniya
 */
@Component("Void")
public class VoidConverter implements Coercing<Void, String> {


    @Override
    public String serialize(final Object dataFetcherResult)
            throws CoercingSerializeException {
        return null;
    }

    @Override
    public Void parseValue(final Object input)
            throws CoercingParseValueException {
        return null;
    }

    @Override
    public Void parseLiteral(final Object input)
            throws CoercingParseLiteralException {
        return null;
    }
}
