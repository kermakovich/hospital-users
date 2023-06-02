package solvd.laba.ermakovich.hu.web.config.graphql;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import graphql.validation.rules.OnValidationErrorStrategy;
import graphql.validation.rules.ValidationRules;
import graphql.validation.schemawiring.ValidationSchemaWiring;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

/**
 * @author Ermakovich Kseniya
 */
@Configuration
@RequiredArgsConstructor
public class GraphqlConfig {

    private final Map<String, Coercing> coercingHashMap;

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(
            final ValidationSchemaWiring validationSchemaWiring
    ) {
        return builder -> {
            coercingHashMap.forEach((name, coercing) ->
                    builder.scalar(GraphQLScalarType.newScalar()
                            .name(name)
                            .coercing(coercingHashMap.get(name))
                            .build()
                    ));
            builder.directiveWiring(validationSchemaWiring)
                    .build();
        };
    }

    @Bean
    public ValidationSchemaWiring validationSchemaWiring() {
        ValidationRules validationRules = ValidationRules.newValidationRules()
                .onValidationErrorStrategy(
                        OnValidationErrorStrategy.RETURN_NULL
                )
                .build();
        return new ValidationSchemaWiring(validationRules);
    }

}
