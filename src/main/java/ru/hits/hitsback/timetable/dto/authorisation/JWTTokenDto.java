package ru.hits.hitsback.timetable.dto.authorisation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class JWTTokenDto {

    @JsonProperty("id")
    private UUID accountId;

    @JsonProperty("sec")
    private String secret;

    @JsonProperty("cre")
    private Date dateCreated;

    @JsonProperty("exp")
    private Date dateExp;
}
