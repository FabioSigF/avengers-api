package com.avengers.avengers_api.domain.avenger;

import java.util.List;

public interface AvengerRepository {
    Avenger getDetail(Long id);
    List<Avenger> getAvengers();
    Avenger create(Avenger avenger);
    void delete(Long id);
    Avenger update(Avenger avenger);
    Avenger getByNick(String nick);
}
