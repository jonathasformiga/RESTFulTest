package com.example.stocks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.stocks.models.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

}
