package com.childsync.spring.dto.response;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

public record AppointmentResponse(@NotNull Integer id,
                                  @NotNull LocalDateTime dateTime,
                                  @NotNull String reason,
                                  @NotNull String result,
                                  @NotNull String userName) { }
