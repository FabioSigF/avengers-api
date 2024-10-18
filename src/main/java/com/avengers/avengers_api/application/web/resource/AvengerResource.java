package com.avengers.avengers_api.application.web.resource;

import com.avengers.avengers_api.application.web.resource.request.AvengerRequestDto;
import com.avengers.avengers_api.application.web.resource.response.AvengerResponseDto;
import com.avengers.avengers_api.domain.avenger.Avenger;
import com.avengers.avengers_api.domain.avenger.AvengerRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class AvengerResource {

    private static final String API_PATH = "/api";

    private final AvengerRepository avengerRepository;

    public AvengerResource(AvengerRepository avengerRepository) {
        this.avengerRepository = avengerRepository;
    }

    @GetMapping
    public ResponseEntity<List<AvengerResponseDto>> getAllAvengers() {
        List<Avenger> avengersList = avengerRepository.getAvengers();
        return ResponseEntity.ok().body(avengersList.stream().map(AvengerResponseDto::convertToAvengerResponse).toList());
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<AvengerResponseDto> getAvengerDetailsById(@PathVariable Long id) {
        Avenger avenger = avengerRepository.getDetail(id);
        return ResponseEntity.ok().body(AvengerResponseDto.convertToAvengerResponse(avenger));
    }

    @PostMapping()
    public ResponseEntity<Avenger> createAvenger(@Valid @RequestBody AvengerRequestDto avengerReq) {
        Avenger avenger = avengerRepository.create(AvengerRequestDto.toAvenger(avengerReq));

        URI location = URI.create(API_PATH + "/" + avenger.id());

        return ResponseEntity.created(location).body(avenger);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avenger> updateAvenger(@PathVariable Long id, @RequestBody @Valid AvengerRequestDto req) {
        Avenger avenger = avengerRepository.getDetail(id);
        if (avenger == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Avenger existingAvenger = avengerRepository.getByNick(req.nick());
        if (existingAvenger != null && !existingAvenger.id().equals(id)) {
            // Retorna erro 409 (Conflito) se o 'nick' já estiver sendo usado por outro Avenger
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        Avenger updatedAvenger = new Avenger(avenger.id(), req.nick(), req.person(), req.description(), req.history());
        avengerRepository.update(updatedAvenger);

        return ResponseEntity.ok().body(updatedAvenger);
    }


    @DeleteMapping("/{id}")
    public void deleteAvenger(@PathVariable Long id) {
        avengerRepository.delete(id);
        ResponseEntity.accepted().build();
    }
}
