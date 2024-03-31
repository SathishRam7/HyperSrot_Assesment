package org.ram.services;

import org.ram.dto.InventoryDTO;
import org.ram.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public InventoryDTO getInventory() {

		return productRepository.getInventory();
	}
}
