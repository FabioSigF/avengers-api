package com.avengers.avengers_api.application.web.resource.request;

import com.avengers.avengers_api.domain.avenger.Avenger;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AvengerRequestDto(
        @NotNull @NotBlank @NotEmpty String nick,
        @NotNull @NotBlank @NotEmpty String person,
        String description,
        String history) {

    public static Avenger toAvenger(AvengerRequestDto req) {
        return new Avenger(
                null,
                req.nick,
                req.person,
                req.description,
                req.history
        );
    }

}
