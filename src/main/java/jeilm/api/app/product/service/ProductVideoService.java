package jeilm.api.app.product.service;

import java.util.List;

import jeilm.api.app.product.vo.ProductVideoVO;

public interface ProductVideoService {
	
	/**
	 * 비디오 리스트
	 * @param productVideoVO
	 * @return
	 * @throws Exception
	 */
	List<ProductVideoVO> selectProductVideoList(ProductVideoVO productVideoVO) throws Exception;
	
}
