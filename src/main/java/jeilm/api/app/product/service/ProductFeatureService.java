package jeilm.api.app.product.service;

import java.util.List;

import jeilm.api.app.product.vo.ProductFeatureVO;

public interface ProductFeatureService {

	/**
	 * 특징 리스트
	 * @param productFeatureVO
	 * @return
	 * @throws Exception
	 */
	List<ProductFeatureVO> selectProductFeatureList(ProductFeatureVO productFeatureVO) throws Exception;
	
}
