package pe.edu.upc.serviceinterface;

import java.util.List;


import pe.edu.upc.entity.CategoryProduct;

public interface ICategoryProductService {

	public void insert(CategoryProduct categoryProduct);
	List<CategoryProduct> list();
}