package dev.arkondata.events.core;

public enum MessageError {
    DUPLICATED_EVENT("DUPLICATED_EVENT"),
    EVENT_NOT_FOUND("EVENT_NOT_FOUND"),
    START_EVENT_MUST_NOT_BE_BEFORE_TODAY("START_EVENT_MUST_NOT_BE_BEFORE_TODAY"),
    END_EVENT_MUST_NOT_BE_BEFORE_START_EVENT("END_EVENT_MUST_NOT_BE_BEFORE_START_EVENT"),
    EVENT_UPDATE_NOT_ALLOWED_MAX_TICKETS_SOLD("EVENT_UPDATE_NOT_ALLOWED_MAX_TICKETS_SOLD"),
    EVENT_DELETE_NOT_ALLOWED_TICKETS_ALREADY_SOLD("EVENT_DELETE_NOT_ALLOWED_TICKETS_ALREADY_SOLD"),
    DELETE_EVENT_NOT_ALLOWED_NOT_FINISH_YET("DELETE_EVENT_NOT_ALLOWED_NOT_FINISH_YET"),
    MAX_TICKETS_LIMIT_REACHED("MAX_TICKETS_LIMIT_REACHED"),
    TICKET_NOT_FOUND("TICKET_NOT_FOUND"),
    UNKOWN_ERROR("UNKOWN_ERROR");


    MessageError(String value) {
    }

    public static MessageError getMessageError(String value) {
        try {
            return MessageError.valueOf(value);
        } catch (IllegalArgumentException e) {
            // Handle the case where the constant does not exist
            System.out.println("No enum constant with name " + value);
            return UNKOWN_ERROR;
        }
    }
}
