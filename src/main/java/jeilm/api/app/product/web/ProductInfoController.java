package jeilm.api.app.product.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.product.service.ProductFeatureService;
import jeilm.api.app.product.service.ProductInfoService;
import jeilm.api.app.product.service.ProductRelationService;
import jeilm.api.app.product.service.ProductVideoService;
import jeilm.api.app.product.vo.ProductFeatureVO;
import jeilm.api.app.product.vo.ProductInfoDetailVO;
import jeilm.api.app.product.vo.ProductInfoListVO;
import jeilm.api.app.product.vo.ProductRelationVO;
import jeilm.api.app.product.vo.ProductVideoVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductInfoController {
	
	private final ProductInfoService productInfoService;
	private final ProductFeatureService productFeatureService;
	private final ProductVideoService productVideoService;
	private final ProductRelationService productRelationService;
	
	/**
	 * 제품 리스트
	 * @param productCategoryVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/product/list")
	public ResponseEntity<?> getProductList(@RequestBody ProductInfoListVO productInfoListVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 아이디
		if (productInfoListVO.getLang().equals("ko")) {
			productInfoListVO.setCategory_id("category.ko");
		} else if (productInfoListVO.getLang().equals("en")) {
			productInfoListVO.setCategory_id("category.en");
		} else if (productInfoListVO.getLang().equals("cn")) {
			productInfoListVO.setCategory_id("category.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		List<ProductInfoListVO> resultList = productInfoService.selectProductInfoList(productInfoListVO);
		
		map.put("product_list", resultList);
		
		return JsonResult.success(map);	
	}
	
	/**
	 * 제품 상세
	 * @param productCategoryVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/product/view")
	public ResponseEntity<?> getProductDetail(@RequestBody ProductInfoDetailVO productInfoDetailVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 아이디
		if (productInfoDetailVO.getLang().equals("ko")) {
			productInfoDetailVO.setCategory_id("category.ko");
		} else if (productInfoDetailVO.getLang().equals("en")) {
			productInfoDetailVO.setCategory_id("category.en");
		} else if (productInfoDetailVO.getLang().equals("cn")) {
			productInfoDetailVO.setCategory_id("category.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		// 제품 상세
		ProductInfoDetailVO resultInfo = productInfoService.selectProductInfo(productInfoDetailVO);
		
		// 제품 특징 리스트
		ProductFeatureVO productFeatureVO = new ProductFeatureVO();
		productFeatureVO.setInfo_sn(productInfoDetailVO.getInfo_sn());
		List<ProductFeatureVO> resultFeatureList = productFeatureService.selectProductFeatureList(productFeatureVO);
		
		// 제품 비디오 리스트
		ProductVideoVO productVideoVO = new ProductVideoVO();
		productVideoVO.setInfo_sn(productInfoDetailVO.getInfo_sn());
		List<ProductVideoVO> resultVideoList = productVideoService.selectProductVideoList(productVideoVO);
		
		// 관련 제품 리스트
		ProductRelationVO productRelationVO = new ProductRelationVO();
		productRelationVO.setInfo_sn_org(productInfoDetailVO.getInfo_sn());
		List<ProductRelationVO> resultRelationList = productRelationService.selectProductRelationList(productRelationVO);
		
		map.put("product_info", resultInfo);
		map.put("feature_list", resultFeatureList);
		map.put("video_list", resultVideoList);
		map.put("relation_list", resultRelationList);
		
		return JsonResult.success(map);	
	}
	
}
