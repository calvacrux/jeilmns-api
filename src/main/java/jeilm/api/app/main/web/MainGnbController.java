package jeilm.api.app.main.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.main.service.MainGnbService;
import jeilm.api.app.main.vo.MainGnbJsonVO;
import jeilm.api.app.main.vo.MainGnbListVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainGnbController {
	
	private final MainGnbService mainGnbService;
	
	@PostMapping("/vi/main/gnb/list")
	public ResponseEntity<?> getMainGnbList(@RequestBody MainGnbListVO mainGnbListVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 전체 메뉴 리스트
		List<MainGnbListVO> resultList = mainGnbService.selectMainGnbList(mainGnbListVO);
		
		List<MainGnbJsonVO> newsList = new ArrayList<>();
		List<MainGnbJsonVO> certList = new ArrayList<>();
		List<MainGnbJsonVO> productCategoryList = new ArrayList<>();
		
		for (MainGnbListVO vo : resultList) {
			String type = vo.getMenu_type().substring(0, vo.getMenu_type().length() - 3);
			
			if (type.equals("code.news")) {
				MainGnbJsonVO newsInfo = new MainGnbJsonVO();
				newsInfo.setLang(vo.getLang());
				newsInfo.setMenu_id(vo.getMenu_id());
				newsInfo.setMenu_nm(vo.getMenu_nm());
				newsList.add(newsInfo);
			} else if (type.equals("code.cert")) {
				MainGnbJsonVO certInfo = new MainGnbJsonVO();
				certInfo.setLang(vo.getLang());
				certInfo.setMenu_id(vo.getMenu_id());
				certInfo.setMenu_nm(vo.getMenu_nm());
				certList.add(certInfo);
			} else if (type.equals("category")) {
				MainGnbJsonVO productCategoryInfo = new MainGnbJsonVO();
				productCategoryInfo.setLang(vo.getLang());
				productCategoryInfo.setMenu_id(vo.getMenu_id());
				productCategoryInfo.setMenu_nm(vo.getMenu_nm());
				productCategoryList.add(productCategoryInfo);
			}
		}
		
		map.put("news_list", newsList);
		map.put("cert_list", certList);
		map.put("product_category_list", productCategoryList);				
		
		return JsonResult.success(map);	
	}

}
