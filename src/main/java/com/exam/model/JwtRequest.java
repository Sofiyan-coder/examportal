package com.exam.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class JwtRequest {

    @JsonProperty(value="username")
    String username;
    @JsonProperty(value="password")
    String password;
}

