package com.example.stocks.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.stocks.models.Stock;
import com.example.stocks.repositories.StockRepository;
import com.example.stocks.service.StockService;

@RestController
@RequestMapping(path="/stock")
public class StockResource {

	@Autowired
	private StockService stockService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Stock create(@RequestBody @Validated Stock stock) {
		return stockService.createStock(stock);
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(required = false, defaultValue = "") String name) {
		if(name.isEmpty() || name.equals("")) {
			return stockService.readAllStocks();
		}
		return stockService.readOneStock(name);
	}
	
	@PatchMapping(value = "/{stock_name}")
	public Stock updateStock(@PathVariable("stock_name") String stockName, @RequestBody Stock stockPartialUpdate) {
		return stockService.updateStock(stockName, stockPartialUpdate);
	}
		
	@DeleteMapping("/{stock_name}")
	public void deleteStock(@PathVariable("stock_name") String stockName) {
		stockService.deleteStock(stockName);
	}
	
}
