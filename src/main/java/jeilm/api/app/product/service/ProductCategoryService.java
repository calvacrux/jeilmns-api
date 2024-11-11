package jeilm.api.app.product.service;

import java.util.List;

import jeilm.api.app.product.vo.ProductCategoryVO;

public interface ProductCategoryService {

	/**
	 * 카테고리 리스트 조회
	 * @param productCategoryVO
	 * @return
	 * @throws Exception
	 */
	List<ProductCategoryVO> selectProductCategoryList(ProductCategoryVO productCategoryVO)  throws Exception;
	
}
