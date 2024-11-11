package jeilm.api.app.product.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.product.vo.ProductFeatureVO;

@Mapper
public interface ProductFeatureMapper {
	
	/**
	 * 특징 리스트
	 * @param productFeatureVO
	 * @return
	 * @throws Exception
	 */
	List<ProductFeatureVO> selectProductFeatureList(ProductFeatureVO productFeatureVO) throws Exception;
	
}
