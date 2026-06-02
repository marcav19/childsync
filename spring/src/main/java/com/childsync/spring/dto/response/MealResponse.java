package com.childsync.spring.dto.response;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

public record MealResponse(@NotNull Integer id,
                           @NotNull LocalDateTime dateTime,
                           @NotNull String name,
                           @NotNull String comment,
                           @NotNull String userName) { }
