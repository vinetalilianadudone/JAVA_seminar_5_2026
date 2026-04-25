package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Product;
import lv.venta.service.IProductCRUDService;

@Controller
@RequestMapping("/product/crud")
public class ProductCRUDController {
	
	@Autowired
	private IProductCRUDService prodService;
	
	@GetMapping("/all")//localhost:9000/product/crud/all
	public String getAllProducts(Model model) {
		try
		{
			model.addAttribute("package", prodService.retrieveAll());
			return "show-all-products-page";//tiks paradita show-all-products-page.html lapa
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/one")	//localhost:9000/product/crud/one?id=2 - id = 1, 2, 3
	public String getOneProductById(@RequestParam(name = "id") long id, Model model) {
		try
		{
			Product prodFromDB = prodService.retrieveById(id);
			model.addAttribute("package", prodFromDB);
			return "show-one-product-page";//tiks paradita show-one-product-page.html lapa
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/all/{id}")	//localhost:9000/product/crud/all/2
	public String getOneProductById2(@PathVariable(name = "id") long id, Model model) {
		try
		{
			Product prodFromDB = prodService.retrieveById(id);
			model.addAttribute("package", prodFromDB);
			return "show-one-product-page";//tiks paradita show-one-product-page.html lapa
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/add")//localhost:9000/product/crud/add
	public String getAddNewProduct(Model model) {
		//lai iveidotu jaunu produktu, iedodam noklsueto proukdu, kuru pec tam vares aizpildit html puse
		model.addAttribute("product", new Product());
		return "add-new-product-page";//tiks paradita add-new-product-page.html lapa
	}
	
	@PostMapping("/add")
	public String postAddNewProduct(@Valid Product product, BindingResult result,  Model model) {
		if(result.hasErrors()) {
			return "add-new-product-page";
		}	
		try 
		{
			prodService.create(product.getTitle(), product.getPrice(), product.getQuantity(), 
				product.getDescription(), product.getProductType());
			return "redirect:/product/crud/all";
	}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";

		}
	}
	
	@GetMapping("/update/{id}")//localhost:9000/product/crud/update/2
	public String getUpdateProductById(@PathVariable(name = "id") long id, Model model) {
		try
		{
			Product prodFromDB = prodService.retrieveById(id);
			model.addAttribute("product", prodFromDB);
			return "update-product-page";//tiks paradita show-one-product-page.html lapa
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	@PostMapping("/update/{id}")
	public String postUpdateProductById(@PathVariable(name = "id") long id, Product product, Model model) {
		try 
		{
			prodService.updateById(id, product.getTitle(), product.getPrice(), product.getQuantity(), 
				product.getDescription(), product.getProductType());
			return "redirect:/product/crud/all";
	}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";

		}
	}
	
	@GetMapping("/delete/{id}")
	public String getDeleteProductById(@PathVariable(name = "id") long id, Model model) {
		try
		{
			prodService.deleteById(id);
			model.addAttribute("package", prodService.retrieveAll());
			return "show-all-products-page";
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	
}