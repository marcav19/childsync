package com.childsync.spring.dto.response;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

public record SleepResponse(@NotNull Integer id,
                            @NotNull LocalDateTime start,
                            @NotNull LocalDateTime end,
                            @NotNull String userName) { }
