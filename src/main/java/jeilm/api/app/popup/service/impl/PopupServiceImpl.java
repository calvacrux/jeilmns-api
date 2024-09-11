package jeilm.api.app.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.popup.service.PopupService;
import jeilm.api.app.popup.service.mapper.PopupMapper;
import jeilm.api.app.popup.vo.PopupVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PopupServiceImpl implements PopupService {
	
	private final PopupMapper popupMapper;

	@Override
	public List<PopupVO> selectPopupList(PopupVO popupVO) throws Exception {
		return popupMapper.selectPopupList(popupVO);
	}

}
