package com.nl.trace.dataseeder.bank.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nl.trace.dataseeder.bank.entity.KafkaProducerFileMetadata;

@Repository
public interface KafkaProducerFileMetadataRepository extends CrudRepository<KafkaProducerFileMetadata, UUID> {

}
