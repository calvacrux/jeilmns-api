package jeilm.api.app.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.product.service.ProductRelationService;
import jeilm.api.app.product.service.mapper.ProductRelationMapper;
import jeilm.api.app.product.vo.ProductRelationVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductRelationServiceImpl implements ProductRelationService {
	
	private final ProductRelationMapper productRelationMapper;

	@Override
	public List<ProductRelationVO> selectProductRelationList(ProductRelationVO productRelationVO) throws Exception {
		return productRelationMapper.selectProductRelationList(productRelationVO);
	}

}
