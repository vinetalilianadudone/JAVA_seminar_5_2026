package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;
import lv.venta.model.ProductType;

public interface IProductRepo extends CrudRepository<Product, Long> {
	//TODO papildinat pec nepieciesamaibas citas funkcijas
	
	boolean existsByTitleAndPriceAndDescriptionAndProductType(String title, float price, String description, ProductType type);

	Product findByexistsByTitleAndPriceAndDescriptionAndProductType(String title, float price, int quantity,
			String description, ProductType type);
	
}
