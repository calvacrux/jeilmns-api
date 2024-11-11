package jeilm.api.app.product.service;

import java.util.List;

import jeilm.api.app.product.vo.ProductRelationVO;

public interface ProductRelationService {

	/**
	 * 관련제품 리스트
	 * @param productRelationVO
	 * @return
	 * @throws Exception
	 */
	List<ProductRelationVO> selectProductRelationList(ProductRelationVO productRelationVO) throws Exception;
	
}
