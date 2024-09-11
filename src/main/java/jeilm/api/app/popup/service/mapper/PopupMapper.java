package jeilm.api.app.popup.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.popup.vo.PopupVO;

@Mapper
public interface PopupMapper {
	
	/**
	 * 팝업 리스트
	 * @param popupVO
	 * @return
	 * @throws Exception
	 */
	List<PopupVO> selectPopupList(PopupVO popupVO) throws Exception;

}
