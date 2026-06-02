package com.childsync.spring.dto.request;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

public record MealRequest(@NotNull LocalDateTime dateTime,
                          @NotNull String name,
                          @NotNull String comment,
                          @NotNull Integer userId) { }
