package com.fullstack.repository;

import com.fullstack.entity.GSTBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GSTBillRepository extends JpaRepository<GSTBill, Long> {


}
