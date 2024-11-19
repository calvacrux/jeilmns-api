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
import jeilm.api.app.product.service.ProductCategoryService;
import jeilm.api.app.product.vo.ProductCategoryVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductCategoryController {
	
	private final ProductCategoryService productCategoryService;
	
	/**
	 * 제품 카테고리 리스트
	 * @param productCategoryVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/product/category/list")
	public ResponseEntity<?> getProductCategoryList(@RequestBody ProductCategoryVO productCategoryVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 아이디
		if (productCategoryVO.getLang().equals("ko")) {
			productCategoryVO.setCategory_id("category.ko");
		} else if (productCategoryVO.getLang().equals("en")) {
			productCategoryVO.setCategory_id("category.en");
		} else if (productCategoryVO.getLang().equals("cn")) {
			productCategoryVO.setCategory_id("category.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		List<ProductCategoryVO> resultList = productCategoryService.selectProductCategoryList(productCategoryVO);
		
		map.put("product_category_list", resultList);
		
		return JsonResult.success(map);	
	}

}
