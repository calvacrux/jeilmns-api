package jeilm.api.app.product.service;

import java.util.List;

import jeilm.api.app.product.vo.ProductInfoDetailVO;
import jeilm.api.app.product.vo.ProductInfoListVO;

public interface ProductInfoService {

	/**
	 * 제품정보 조회
	 * @param productInfoVO
	 * @return
	 * @throws Exception
	 */
	ProductInfoDetailVO selectProductInfo(ProductInfoDetailVO productInfoDetailVO) throws Exception;
	
	/**
	 * 제품정보 리스트
	 * @param productInfoVO
	 * @return
	 * @throws Exception
	 */
	List<ProductInfoListVO> selectProductInfoList(ProductInfoListVO productInfoListVO) throws Exception;
	
}
