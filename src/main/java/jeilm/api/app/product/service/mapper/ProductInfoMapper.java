package jeilm.api.app.product.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.product.vo.ProductInfoDetailVO;
import jeilm.api.app.product.vo.ProductInfoListVO;

@Mapper
public interface ProductInfoMapper {
	
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
