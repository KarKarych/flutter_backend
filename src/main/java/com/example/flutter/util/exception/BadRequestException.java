package com.example.flutter.util.exception;

import lombok.Getter;

import java.text.MessageFormat;

@Getter
public class BadRequestException extends RuntimeException {

    protected final Code code;

    protected BadRequestException(Code code, String msg) {
        this(code, null, msg);
    }

    protected BadRequestException(Code code, Throwable e, String msg) {
        super(msg, e);
        this.code = code;
    }

    @Getter
    public enum Code {

        PASSWORDS_DO_NOT_MATCH("Passwords don't match"),
        INSUFFICIENT_BALANCE("Insufficient balance"),
        BUCKET_IS_EMPTY("The bucket is empty"),
        ;

        /**
         * Pattern {0} - code description, {1} - message
         */
        private static final String EXCEPTION_MESSAGE_PATTERN = "{0}: {1}";

        private final String description;

        Code(String description) {
            this.description = "%s (%s)".formatted(name(), description);
        }

        public BadRequestException get() {
            return new BadRequestException(this, description);
        }

        public BadRequestException get(String additionalInfo) {
            String errorMessage = MessageFormat.format(EXCEPTION_MESSAGE_PATTERN, this.description, additionalInfo);
            return new BadRequestException(this, errorMessage);
        }

        public BadRequestException get(Throwable e) {
            String errorMessage = MessageFormat.format(EXCEPTION_MESSAGE_PATTERN, this.description, e.getMessage());
            return new BadRequestException(this, e, errorMessage);
        }

        public BadRequestException get(Throwable e, String msg) {
            String errorMessage = MessageFormat.format(EXCEPTION_MESSAGE_PATTERN, this.description, msg);
            return new BadRequestException(this, e, errorMessage);
        }
    }
}
