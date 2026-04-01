package lv.venta.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.model.Product;
import lv.venta.model.ProductType;

@Controller
@RequestMapping("/simple")
public class SimpleController {
	
	@GetMapping("/page")//localhost:9000/simple/page
	public String getShowPage() {
		System.out.println("Mans pirmais kontrolieris ir izsaukts");
		return "show-page";//tiks paradita show-page.html lapa ieks web parluka
	}
	
	@GetMapping("/data")
	public String getDataInPage(Model model) {//localhost:9000/simple/data
		System.out.println("Izpildas datu kontrolieris");
		Random rand = new Random();
		String data = "@Vineta " + rand.nextInt(2010, 2026);
		model.addAttribute("package", data);
		return "show-data-page";//tiks paradita show-data-page.html lapa
	}
	@GetMapping("/product")//localhost:9000/simple/product
	public String getProductInPage(Model model) {
		Product prod = new Product("Ābols", 0.99f, 5, "Garšīgs", ProductType.fruit);
		model.addAttribute("package", prod);
		return "show-one-product-page";//tiks paradita show-one-product-page.html lapa
		
	}

}