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


@RestController
@RequestMapping("/avenger")
public class AvengerResource {

    private static final String API_PATH = "/avenger";

    private final AvengerRepository avengerRepository;

    public AvengerResource(AvengerRepository avengerRepository) {
        this.avengerRepository = avengerRepository;
    }

    @GetMapping
    public ResponseEntity<List<AvengerResponseDto>> getAllAvengers() {
        List<Avenger> avengersList = avengerRepository.getAvengers();
        return ResponseEntity.ok().body(avengersList.stream().map(AvengerResponseDto::convertToAvengerResponse).toList());
    }

    @GetMapping("/{id}")
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
    public ResponseEntity<Avenger> updateAvenger(@Valid @PathVariable Long id, @RequestBody AvengerRequestDto req) {
        Avenger updatedAvenger = new Avenger(id, req.nick(), req.person(), req.description(), req.history());

        avengerRepository.update(updatedAvenger);

        return ResponseEntity.ok().body(updatedAvenger);
    }

    @DeleteMapping("/{id}")
    public void deleteAvenger(@PathVariable Long id) {
        avengerRepository.delete(id);
        ResponseEntity.accepted().build();
    }
}
