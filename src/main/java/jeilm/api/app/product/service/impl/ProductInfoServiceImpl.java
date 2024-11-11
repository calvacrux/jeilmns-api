package jeilm.api.app.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.product.service.ProductInfoService;
import jeilm.api.app.product.service.mapper.ProductInfoMapper;
import jeilm.api.app.product.vo.ProductInfoDetailVO;
import jeilm.api.app.product.vo.ProductInfoListVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductInfoServiceImpl implements ProductInfoService {
	
	private final ProductInfoMapper productInfoMapper;

	@Override
	public ProductInfoDetailVO selectProductInfo(ProductInfoDetailVO productInfoDetailVO) throws Exception {
		return productInfoMapper.selectProductInfo(productInfoDetailVO);
	}

	@Override
	public List<ProductInfoListVO> selectProductInfoList(ProductInfoListVO productInfoListVO) throws Exception {
		return productInfoMapper.selectProductInfoList(productInfoListVO);
	}

}
