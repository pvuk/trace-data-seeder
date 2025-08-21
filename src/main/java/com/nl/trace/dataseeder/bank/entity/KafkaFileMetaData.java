package com.nl.trace.dataseeder.bank.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class KafkaFileMetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FILE_ID")
    private UUID fileId;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "SOURCE_FILE_PATH")
    private String sourceFilePath;
    
    @Column(name = "TARGET_FILE_PATH")
    private String targetFilePath;

    @Column(name = "FILE_SIZE_BYTES")
    private Long fileSizeBytes;

    @Column(name = "RECORD_COUNT")
    private Integer recordCount;

    @Column(name = "COLUMN_COUNT")
    private Integer columnCount;

    @Column(name = "SCHEMA", columnDefinition = "TEXT")
    private String schema;//Column names and types (if known)

    @Column(name = "INGESTION_TIMESTAMP")
    private LocalDateTime ingestionTimestamp;

    @Column(name = "TOPIC_ID")
    private String topicID;
    
    @Column(name = "GROUP_ID")
    private String groupID;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ERROR_MESSAGE", columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "SOURCE_SYSTEM")
    private String sourceSystem;//Origin of the file (e.g., system name or external source) 

//    @Column(name = "CHECKSUM")
//    private String checksum;
}
