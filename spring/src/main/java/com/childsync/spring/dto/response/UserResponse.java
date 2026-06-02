package com.childsync.spring.dto.response;

import jakarta.validation.constraints.NotNull;

public record UserResponse(@NotNull Integer id,
                           @NotNull String name) { }
