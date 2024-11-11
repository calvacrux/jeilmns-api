package jeilm.api.app.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.product.service.ProductFeatureService;
import jeilm.api.app.product.service.mapper.ProductFeatureMapper;
import jeilm.api.app.product.vo.ProductFeatureVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductFeatureServiceImpl implements ProductFeatureService {
	
	private final ProductFeatureMapper productFeatureMapper;

	@Override
	public List<ProductFeatureVO> selectProductFeatureList(ProductFeatureVO productFeatureVO) throws Exception {
		return productFeatureMapper.selectProductFeatureList(productFeatureVO);
	}

}
