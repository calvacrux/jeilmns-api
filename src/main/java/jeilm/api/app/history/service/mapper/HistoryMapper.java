package jeilm.api.app.history.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.history.vo.HistoryVO;

@Mapper
public interface HistoryMapper {

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
