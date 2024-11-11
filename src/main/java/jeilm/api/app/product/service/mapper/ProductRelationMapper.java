package jeilm.api.app.product.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.product.vo.ProductRelationVO;

@Mapper
public interface ProductRelationMapper {

	/**
	 * 관련제품 리스트
	 * @param productRelationVO
	 * @return
	 * @throws Exception
	 */
	List<ProductRelationVO> selectProductRelationList(ProductRelationVO productRelationVO) throws Exception;
	
}
