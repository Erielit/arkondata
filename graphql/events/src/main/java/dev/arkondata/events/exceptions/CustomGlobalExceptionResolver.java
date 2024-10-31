package dev.arkondata.events.exceptions;

import dev.arkondata.events.core.MessageError;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class CustomGlobalExceptionResolver extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        return switch (MessageError.getMessageError(ex.getMessage())) {
            case DUPLICATED_EVENT ->
                    prepareGraphQLErrorResponse(MessageError.DUPLICATED_EVENT.name(), ErrorType.BAD_REQUEST, env, Map.of("statusCode", HttpStatusCode.valueOf(400)));
            case EVENT_NOT_FOUND ->
                    prepareGraphQLErrorResponse(MessageError.EVENT_NOT_FOUND.name(), ErrorType.BAD_REQUEST, env, Map.of("statusCode", HttpStatusCode.valueOf(400)));
            case START_EVENT_MUST_NOT_BE_BEFORE_TODAY ->
                    prepareGraphQLErrorResponse(MessageError.START_EVENT_MUST_NOT_BE_BEFORE_TODAY.name(), ErrorType.BAD_REQUEST, env, Map.of("statusCode", HttpStatusCode.valueOf(400)));
            case END_EVENT_MUST_NOT_BE_BEFORE_START_EVENT ->
                    prepareGraphQLErrorResponse(MessageError.END_EVENT_MUST_NOT_BE_BEFORE_START_EVENT.name(), ErrorType.BAD_REQUEST, env, Map.of("statusCode", HttpStatusCode.valueOf(400)));
            case EVENT_UPDATE_NOT_ALLOWED_MAX_TICKETS_SOLD ->
                    prepareGraphQLErrorResponse(MessageError.EVENT_UPDATE_NOT_ALLOWED_MAX_TICKETS_SOLD.name(), ErrorType.BAD_REQUEST, env, Map.of("statusCode", HttpStatusCode.valueOf(400)));
            case EVENT_DELETE_NOT_ALLOWED_TICKETS_ALREADY_SOLD ->
                    prepareGraphQLErrorResponse(MessageError.EVENT_DELETE_NOT_ALLOWED_TICKETS_ALREADY_SOLD.name(), ErrorType.BAD_REQUEST, env, Map.of("statusCode", HttpStatusCode.valueOf(400)));
            case MAX_TICKETS_LIMIT_REACHED ->
                    prepareGraphQLErrorResponse(MessageError.MAX_TICKETS_LIMIT_REACHED.name(), ErrorType.BAD_REQUEST, env, Map.of("statusCode", HttpStatusCode.valueOf(400)));
            case TICKET_NOT_FOUND ->
                    prepareGraphQLErrorResponse(MessageError.TICKET_NOT_FOUND.name(), ErrorType.BAD_REQUEST, env, Map.of("statusCode", HttpStatusCode.valueOf(400)));
            default -> prepareGraphQLErrorResponse(
                    MessageError.UNKOWN_ERROR.name(),
                    ErrorType.INTERNAL_ERROR,
                    env,
                    Map.of("statusCode", HttpStatusCode.valueOf(500)));
        };
    }

    private GraphQLError prepareGraphQLErrorResponse(
            String message,
            ErrorType errorType,
            DataFetchingEnvironment env,
            Map<String, Object> extensions) {
        return GraphqlErrorBuilder.newError()
                .errorType(errorType)
                .message(message)
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .extensions(extensions)
                .build();
    }
}
