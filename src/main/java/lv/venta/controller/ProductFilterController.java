package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lv.venta.model.Product;
import lv.venta.model.ProductType;
import lv.venta.service.IProductFilterService;

@Controller
@RequestMapping("/product/filter") // localhost:9000/product/filter
public class ProductFilterController {

	@Autowired
	private IProductFilterService prodFilterService;

	// localhost:9000/product/filter/price/{threshold}
	// localhost:9000/product/filter/price/10
	@GetMapping("/price/{threshold}")
	public String getFilterProductByPrice(@PathVariable float threshold, Model model) {
		try {
			ArrayList<Product> productsFromDB = prodFilterService.filterByPriceLessThan(threshold);

			model.addAttribute("package", productsFromDB);
			model.addAttribute("myHeader", "Produkti, kuru cena ir zem " + threshold + " eur");

			return "show-all-products-page";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	// localhost:9000/product/filter/quantity/{threshold}
	// localhost:9000/product/filter/quantity/5
	@GetMapping("/quantity/{threshold}")
	public String getFilterProductByQuantity(@PathVariable int threshold, Model model) {
		try {
			ArrayList<Product> productsFromDB = prodFilterService.filterByQuantityGreaterThan(threshold);

			model.addAttribute("package", productsFromDB);
			model.addAttribute("myHeader", "Produkti, kuru daudzums ir virs " + threshold);

			return "show-all-products-page";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	// localhost:9000/product/filter/title/{title}
	// localhost:9000/product/filter/title/Abols
	@GetMapping("/title/{title}")
	public String getFilterProductByTitle(@PathVariable String title, Model model) {
		try {
			ArrayList<Product> productsFromDB = prodFilterService.filterByTitle(title);

			model.addAttribute("package", productsFromDB);
			model.addAttribute("myHeader", "Produkti ar nosaukumu: " + title);

			return "show-all-products-page";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	// localhost:9000/product/filter/type/{type}
	// localhost:9000/product/filter/type/FRUIT
	@GetMapping("/type/{type}")
	public String getFilterProductByType(@PathVariable String type, Model model) {
		try {

			ProductType enumType = ProductType.valueOf(type.toLowerCase());

			ArrayList<Product> productsFromDB = prodFilterService.filterByType(enumType);

			model.addAttribute("package", productsFromDB);
			model.addAttribute("myHeader", "Produkti pēc tipa: " + type);

			return "show-all-products-page";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	// localhost:9000/product/filter/keyword/{keyword}
	// localhost:9000/product/filter/keyword/abols
	@GetMapping("/keyword/{keyword}")
	public String getFilterProductByKeyword(@PathVariable String keyword, Model model) {
		try {
			ArrayList<Product> productsFromDB = prodFilterService.filterByKeyword(keyword);

			model.addAttribute("package", productsFromDB);
			model.addAttribute("myHeader", "Produkti, kas satur atslēgvārdu: " + keyword);

			return "show-all-products-page";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	// localhost:9000/product/filter/average-price
	@GetMapping("/average-price")
	public String getAveragePrice(Model model) {
		try {
			float avgPrice = prodFilterService.calculateAvgPrice();

			// ziņojumu par cenu padodam caur virsrakstu vai atsevišķu mainīgo.
			model.addAttribute("myHeader", "Visu produktu vidējā cena ir: " + avgPrice + " EUR");
			model.addAttribute("package", new ArrayList<Product>()); 

			return "show-all-products-page";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}


}