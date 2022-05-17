package com.pepcus.apicrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pepcus.apicrud.model.Shelve;
@Repository
public interface ShelveRepository extends   JpaRepository<Shelve, Integer>{

}
