package com.childsync.spring.dto.request;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

public record SleepRequest(@NotNull LocalDateTime start,
                           @NotNull LocalDateTime end,
                           @NotNull Integer userId) { }
