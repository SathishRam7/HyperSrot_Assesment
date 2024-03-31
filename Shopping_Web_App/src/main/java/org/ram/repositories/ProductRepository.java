package org.ram.repositories;

import org.ram.dto.InventoryDTO;
import org.ram.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT new org.ram.dto.InventoryDTO(c.ordered,c.price,c.available) FROM Product c")
	public InventoryDTO getInventory();

	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.available = :newAvailability")
	public void setAvailability(@Param("newAvailability") Integer newAvailability);

	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.ordered = :newOrdered")
	public void setOrdered(@Param("newOrdered") Integer newOrdered);

	
	
	@Query("SELECT e.ordered FROM Product e ")
	public Integer getOrdered();
	
	@Query("SELECT e.available FROM Product e ")
	public Integer getAvailability();
	
}
