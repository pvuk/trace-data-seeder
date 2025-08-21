package com.nl.trace.dataseeder.service.impl.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nl.trace.dataseeder.bank.entity.KafkaProducerFileMetadata;
import com.nl.trace.dataseeder.bank.repository.KafkaProducerFileMetadataRepository;
import com.nl.trace.dataseeder.constants.Constants;
import com.nl.trace.dataseeder.service.kafka.producer.KafkaProducerService;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
	private static final Logger LOG = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);
	
    private static final String TOPIC_TRACE_BANK = Constants.KAFKA.TOPIC_NAME.TOPIC_TRACE_BANK;

    private final KafkaTemplate<String, KafkaProducerFileMetadata> kafkaTemplate;
//    private final KafkaProducerFileMetadataService kafkaProducerFileMetadataService; 
    private final KafkaProducerFileMetadataRepository kafkaProducerFileMetadataRepository;

	public KafkaProducerServiceImpl(KafkaTemplate<String, KafkaProducerFileMetadata> kafkaTemplate,
//			KafkaProducerFileMetadataService kafkaProducerFileMetadataService,
			KafkaProducerFileMetadataRepository kafkaProducerFileMetadataRepository) {
        this.kafkaTemplate = kafkaTemplate;
//        this.kafkaProducerFileMetadataService = kafkaProducerFileMetadataService;
        this.kafkaProducerFileMetadataRepository = kafkaProducerFileMetadataRepository;
    }
	
	/**
	 * Code Ref: when we have more than one datasource in a project. we need to save data to a particular datasouce use transactionManager
	 */
	@Transactional(transactionManager = "trace_DataSeeder_Parent_Bank_TransactionManager"
//			, propagation = Propagation.REQUIRED
			)
	@Override
    public void producer_save_FileMetadata(KafkaProducerFileMetadata metadata) {
    	metadata.setTopicID(TOPIC_TRACE_BANK);
    	metadata.setGroupID(Constants.KAFKA.CONSUMER.GROUP_ID.KAFKA_GROUP_TRACE_BANK_ID);
    	Message<KafkaProducerFileMetadata> message = MessageBuilder.withPayload(metadata)
    			.setHeader(KafkaHeaders.TOPIC, TOPIC_TRACE_BANK)
    			.build();
    	
    	metadata = kafkaProducerFileMetadataRepository.save(metadata);
    	String infoMetadata = "FileId: "+ metadata.getFileId() +", FileName: "+ metadata.getFileName();
    	
    	kafkaTemplate.send(message);
    	LOG.info(String.format("Sent: trace-data-seeder: Saved Metadata: Info: %s, Total Records: %s", infoMetadata, metadata.getRecordCount()));
    }
}
