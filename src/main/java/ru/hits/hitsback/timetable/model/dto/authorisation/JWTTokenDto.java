package ru.hits.hitsback.timetable.model.dto.authorisation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class JWTTokenDto {
    @NotNull
    @JsonProperty("id")
    private UUID accountId;
    @NotNull
    @JsonProperty("sec")
    private String secret;
    @NotNull
    @JsonProperty("cre")
    private Date dateCreated;
    @NotNull
    @JsonProperty("exp")
    private Date dateExp;
    @NotNull
    @JsonProperty("role")
    private String role;
}
