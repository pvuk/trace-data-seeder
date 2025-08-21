package com.nl.trace.dataseeder.service.kafka.producer;

import com.nl.trace.dataseeder.bank.entity.KafkaProducerFileMetadata;

public interface KafkaProducerService {

	void producer_save_FileMetadata(KafkaProducerFileMetadata metadata);

}
