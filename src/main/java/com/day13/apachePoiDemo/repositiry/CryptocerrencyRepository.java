package com.day13.apachePoiDemo.repositiry;

import com.day13.apachePoiDemo.model.Cryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptocerrencyRepository extends JpaRepository<Cryptocurrency,Long> {

}
