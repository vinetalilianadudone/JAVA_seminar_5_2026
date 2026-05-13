package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Product;
import lv.venta.model.ProductType;

public interface IProductFilterService {

	ArrayList<Product> filterByPriceLessThan(float threshold) throws Exception;

	ArrayList<Product> filterByQuantityGreaterThan(int threshold) throws Exception;

	ArrayList<Product> filterByTitle(String title) throws Exception;

	ArrayList<Product> filterByType(ProductType type) throws Exception;

	ArrayList<Product> filterByKeyword(String keyword) throws Exception;

	float calculateAvgPrice() throws Exception;

}