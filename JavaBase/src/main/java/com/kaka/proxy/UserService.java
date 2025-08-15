package com.kaka.proxy;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.io.IOException;
import java.sql.SQLException;

public interface UserService {

    @Retryable(
            value = {IOException.class, SQLException.class},
            maxAttempts = 10,
            backoff = @Backoff(delay = 2000,multiplier = 2, maxDelay = 30000)
    )
    void add() throws IOException;

}
