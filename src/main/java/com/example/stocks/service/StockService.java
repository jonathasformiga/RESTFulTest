package com.example.stocks.service;

import com.example.stocks.models.Stock;
import com.example.stocks.repositories.StockRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;



public class StockService {
	
	private Stock stock;
	
	@Autowired
	private StockRepository stockRepository;

	public Stock createStock(Stock stock) {
		stock.setName(stock.getName().toUpperCase());
		return stockRepository.save(stock);
	}

	public ResponseEntity<?> readAllStocks() {
		return ResponseEntity.ok().body(stockRepository.findAll());
	}
	
	public ResponseEntity<?> readOneStock(String name) {
		return ResponseEntity.ok().body(this.getStockById(name.toUpperCase()).get());
	}
		
	public Stock updateStock(String name, Stock stockPartialUpdate) {
		this.stock = this.getStockById(name).get();
		List<Float> stockQuotes = this.stock.getQuotes();
		stockQuotes.addAll(stockPartialUpdate.getQuotes());
		this.stock.setName(this.stock.getName().toUpperCase());
		this.stock.setQuotes(stockQuotes);
		return stockRepository.save(this.stock);
	}
	
	private Optional<Stock> getStockById(String name) {
		name = name.toUpperCase();
		Optional<Stock> returnedStock = stockRepository.findById(name);
		if (!returnedStock.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return returnedStock;
	}

	public void deleteStock(String name) {

		this.stock = this.getStockById(name.toUpperCase()).get();
		stockRepository.deleteById(this.stock.getName());
	}
}

