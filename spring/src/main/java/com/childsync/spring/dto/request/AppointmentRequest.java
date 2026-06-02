package com.childsync.spring.dto.request;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

public record AppointmentRequest(@NotNull LocalDateTime dateTime,
                                 @NotNull String reason,
                                 @NotNull String result,
                                 @NotNull Integer userId) { }
