package com.childsync.spring.dto.request;

import jakarta.validation.constraints.NotNull;

public record UserRequest(@NotNull String name,
                          @NotNull String email,
                          @NotNull String password) { }
