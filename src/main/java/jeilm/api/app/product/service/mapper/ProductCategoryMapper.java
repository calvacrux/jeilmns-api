package jeilm.api.app.product.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.product.vo.ProductCategoryVO;

@Mapper
public interface ProductCategoryMapper {
	
	/**
	 * 카테고리 리스트 조회
	 * @param productCategoryVO
	 * @return
	 * @throws Exception
	 */
	List<ProductCategoryVO> selectProductCategoryList(ProductCategoryVO productCategoryVO)  throws Exception;
	
}
