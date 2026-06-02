package com.childsync.spring.dto.request;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

public record ActivityRequest(@NotNull LocalDateTime dateTime,
                              @NotNull String name,
                              @NotNull Integer userId) { }
