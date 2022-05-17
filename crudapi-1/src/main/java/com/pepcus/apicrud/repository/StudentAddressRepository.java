package com.pepcus.apicrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pepcus.apicrud.model.Address;

public interface StudentAddressRepository extends JpaRepository<Address,Long> {
	 
}
