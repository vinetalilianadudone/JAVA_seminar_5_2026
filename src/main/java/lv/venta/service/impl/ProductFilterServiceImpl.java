package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.model.ProductType;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductFilterService;

@Service
public class ProductFilterServiceImpl implements IProductFilterService {

	@Autowired
	private IProductRepo prodRepo;

	@Override
	public ArrayList<Product> filterByPriceLessThan(float threshold) throws Exception {

		if (threshold <= 0) {
			throw new Exception("Ievadītajai cenai jābūt pozitīvai");
		}

		ArrayList<Product> result = prodRepo.findByPriceLessThan(threshold);

		if (result.isEmpty()) {
			throw new Exception("Nav produktu ar cenu zem " + threshold);
		}

		return result;
	}

	@Override
	public ArrayList<Product> filterByQuantityGreaterThan(int threshold) throws Exception {

		if (threshold < 0) {
			throw new Exception("Daudzumam jābūt pozitīvam");
		}

		ArrayList<Product> result = prodRepo.findByQuantityGreaterThan(threshold);

		if (result.isEmpty()) {
			throw new Exception("Nav produktu ar daudzumu virs " + threshold);
		}

		return result;
	}

	@Override
	public ArrayList<Product> filterByTitle(String title) throws Exception {

		if (title == null || title.isEmpty()) {
			throw new Exception("Nosaukums nav ievadīts");
		}

		ArrayList<Product> result = prodRepo.findByTitle(title);

		if (result.isEmpty()) {
			throw new Exception("Nav produktu ar nosaukumu " + title);
		}

		return result;
	}

	@Override
	public float calculateAvgPrice() throws Exception {

		if (prodRepo.count() == 0) {
			throw new Exception("DB nav produktu");
		}

		return prodRepo.calculateAvgPriceFromDB();
	}

	@Override
	public ArrayList<Product> filterByType(ProductType type) throws Exception {

		if (type == null) {
			throw new Exception("Nav padots produkta tips");
		}

		ArrayList<Product> result = prodRepo.findByProductType(type);

		if (result.isEmpty()) {
			throw new Exception("Nav produktu ar tipu: " + type);
		}

		return result;
	}

	@Override
	public ArrayList<Product> filterByKeyword(String keyword) throws Exception {

		if (keyword == null || keyword.isEmpty()) {
			throw new Exception("Nav padots atslēgvārds");
		}

		ArrayList<Product> result =
				prodRepo.findByTitleContainingOrDescriptionContaining(keyword, keyword);

		if (result.isEmpty()) {
			throw new Exception("Nav produktu, kas satur: " + keyword);
		}

		return result;
	}
}