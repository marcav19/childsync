package com.childsync.spring.dto.request;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

public record MedicineRequest(@NotNull LocalDateTime dateTime,
                              @NotNull String name,
                              @NotNull String dosage,
                              @NotNull Integer userId) { }
