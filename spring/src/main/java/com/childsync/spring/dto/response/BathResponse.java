package com.childsync.spring.dto.response;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

public record BathResponse(@NotNull Integer id,
                           @NotNull LocalDateTime dateTime,
                           @NotNull String userName) { }
