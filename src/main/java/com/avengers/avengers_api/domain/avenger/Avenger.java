package com.avengers.avengers_api.domain.avenger;

public record Avenger(
        Long id,
        String nick,
        String person,
        String description,
        String history) {
}
