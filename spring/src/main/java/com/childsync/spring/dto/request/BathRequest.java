package com.childsync.spring.dto.request;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

public record BathRequest(@NotNull LocalDateTime dateTime,
                          @NotNull Integer userId) { }
