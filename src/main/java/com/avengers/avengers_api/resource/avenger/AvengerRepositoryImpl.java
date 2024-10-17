package com.avengers.avengers_api.resource.avenger;

import com.avengers.avengers_api.domain.avenger.Avenger;
import com.avengers.avengers_api.domain.avenger.AvengerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AvengerRepositoryImpl implements AvengerRepository {
    private final AvengerEntityRepository repository;

    public AvengerRepositoryImpl(AvengerEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Avenger getDetail(Long id) {
        AvengerEntity avenger = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi encontrado nenhum avenger"));
        return AvengerEntity.toAvenger(avenger);
    }

    @Override
    public List<Avenger> getAvengers() {
        List<AvengerEntity> avenger = repository.findAll();
        return avenger.stream().map(AvengerEntity::toAvenger).toList();
    }

    @Override
    public Avenger create(Avenger avenger) {
        AvengerEntity savedAvenger = repository.save(AvengerEntity.toAvengerEntity(avenger));
        return AvengerEntity.toAvenger(savedAvenger);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Avenger update(Avenger avenger) {
        AvengerEntity savedAvenger = repository.save(AvengerEntity.toAvengerEntity(avenger));
        return AvengerEntity.toAvenger(savedAvenger);
    }
}
