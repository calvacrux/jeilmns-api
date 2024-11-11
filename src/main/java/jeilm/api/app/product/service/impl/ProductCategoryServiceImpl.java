package jeilm.api.app.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.product.service.ProductCategoryService;
import jeilm.api.app.product.service.mapper.ProductCategoryMapper;
import jeilm.api.app.product.vo.ProductCategoryVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
	private final ProductCategoryMapper productCategoryMapper;

	@Override
	public List<ProductCategoryVO> selectProductCategoryList(ProductCategoryVO productCategoryVO) throws Exception {
		return productCategoryMapper.selectProductCategoryList(productCategoryVO);
	}

}
