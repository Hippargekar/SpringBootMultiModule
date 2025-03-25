package org.root.stream;

public final class Failure<T> extends Try<T> {
    private final Exception exception;

    public Failure(Exception exception) {
        this.exception = exception;
    }

    public Exception value() {
        return exception;
    }


}
