package jeilm.api.app.popup.service;

import java.util.List;

import jeilm.api.app.popup.vo.PopupVO;

public interface PopupService {

	/**
	 * 팝업 리스트
	 * @param popupVO
	 * @return
	 * @throws Exception
	 */
	List<PopupVO> selectPopupList(PopupVO popupVO) throws Exception;
	
}
