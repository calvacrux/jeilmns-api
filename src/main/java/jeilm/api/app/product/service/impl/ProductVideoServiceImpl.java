package jeilm.api.app.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.product.service.ProductVideoService;
import jeilm.api.app.product.service.mapper.ProductVideoMapper;
import jeilm.api.app.product.vo.ProductVideoVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductVideoServiceImpl implements ProductVideoService {
	
	private final ProductVideoMapper productVideoMapper;

	@Override
	public List<ProductVideoVO> selectProductVideoList(ProductVideoVO productVideoVO) throws Exception {
		return productVideoMapper.selectProductVideoList(productVideoVO);
	}

}
