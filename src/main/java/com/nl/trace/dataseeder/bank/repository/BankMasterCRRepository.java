package com.nl.trace.dataseeder.bank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nl.trace.dataseeder.bank.entity.BankMasterCR;

@Repository
public interface BankMasterCRRepository extends CrudRepository<BankMasterCR, Long> {

}
