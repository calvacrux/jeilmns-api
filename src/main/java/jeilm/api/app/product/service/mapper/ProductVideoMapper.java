package jeilm.api.app.product.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.product.vo.ProductVideoVO;

@Mapper
public interface ProductVideoMapper {
	
	/**
	 * 비디오 리스트
	 * @param productVideoVO
	 * @return
	 * @throws Exception
	 */
	List<ProductVideoVO> selectProductVideoList(ProductVideoVO productVideoVO) throws Exception;
	
}
