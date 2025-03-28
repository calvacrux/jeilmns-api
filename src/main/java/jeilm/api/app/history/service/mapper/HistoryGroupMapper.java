package jeilm.api.app.history.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.history.vo.HistoryGroupVO;

@Mapper
public interface HistoryGroupMapper {

	/**
	 * 연대기 리스트 조회 - Ko
	 * @param historyVO
	 * @return
	 * @throws Exception
	 */
	List<HistoryGroupVO> selectHistoryGroupListKo(HistoryGroupVO historyGroupVO) throws Exception;
	
	/**
	 * 연대기 리스트 조회 - En
	 * @param historyVO
	 * @return
	 * @throws Exception
	 */
	List<HistoryGroupVO> selectHistoryGroupListEn(HistoryGroupVO historyGroupVO) throws Exception;
	
	/**
	 * 연대기 리스트 조회 - Cn
	 * @param historyVO
	 * @return
	 * @throws Exception
	 */
	List<HistoryGroupVO> selectHistoryGroupListCn(HistoryGroupVO historyGroupVO) throws Exception;
	
}
