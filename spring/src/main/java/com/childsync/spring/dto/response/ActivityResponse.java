package com.childsync.spring.dto.response;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record ActivityResponse(@NotNull Integer id,
                               @NotNull LocalDateTime datetime,
                               @NotNull String name,
                               @NotNull Integer userid) { }
