package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.*;

public interface IProductCRUDService {
	
	// C - create
	public abstract void create(String title, float price, int quantity, String description, ProductType type)
			throws Exception;
	
	// R - retrieve all
	public abstract ArrayList<Product> retrieveAll() 
    		throws Exception;
	
	// R - retrieve by Id
	public abstract Product retrieveById(long id)
			throws Exception;

    // U - update
	public abstract void updateById(long id, String title, float price, int quantity, String description, ProductType type) 
    		throws Exception;

    // D - delete
	public abstract void deleteById(long id) 
    		throws Exception;

}
