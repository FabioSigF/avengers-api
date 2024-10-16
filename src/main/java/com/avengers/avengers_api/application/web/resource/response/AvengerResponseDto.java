package com.avengers.avengers_api.application.web.resource.response;

import com.avengers.avengers_api.domain.avenger.Avenger;

public record AvengerResponseDto(
        Long id,
        String nick,
        String person,
        String description,
        String history) {

    public static AvengerResponseDto convertToAvengerResponse(Avenger avenger) {
        return new AvengerResponseDto(
                avenger.id(),
                avenger.nick(),
                avenger.person(),
                avenger.history(),
                avenger.history()
        );
    }
}
