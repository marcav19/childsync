package com.childsync.spring.dto.request;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

public record ActivityRequest(@NotNull LocalDateTime datetime,
                              @NotNull String name,
                              @NotNull Integer userid) { }
