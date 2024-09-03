package jeilm.api.app.history.service;

import java.util.List;

import jeilm.api.app.history.vo.HistoryVO;

public interface HistoryService {
	
	/**
	 * 연혁 리스트 조회 - Ko
	 * @param historyVO
	 * @return
	 * @throws Exception
	 */
	List<HistoryVO> selectHistoryListKo(HistoryVO historyVO) throws Exception;
	
	/**
	 * 연혁 리스트 조회 - En
	 * @param historyVO
	 * @return
	 * @throws Exception
	 */
	List<HistoryVO> selectHistoryListEn(HistoryVO historyVO) throws Exception;
	
	/**
	 * 연혁 리스트 조회 - Cn
	 * @param historyVO
	 * @return
	 * @throws Exception
	 */
	List<HistoryVO> selectHistoryListCn(HistoryVO historyVO) throws Exception;

}
