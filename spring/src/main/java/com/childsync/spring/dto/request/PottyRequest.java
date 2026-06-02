package com.childsync.spring.dto.request;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

public record PottyRequest(@NotNull LocalDateTime dateTime,
                           @NotNull String description,
                           @NotNull Integer userId) { }
