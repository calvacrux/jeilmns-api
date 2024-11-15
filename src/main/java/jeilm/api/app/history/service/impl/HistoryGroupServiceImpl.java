package jeilm.api.app.history.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.history.service.HistoryGroupService;
import jeilm.api.app.history.service.mapper.HistoryGroupMapper;
import jeilm.api.app.history.vo.HistoryGroupVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryGroupServiceImpl implements HistoryGroupService {
	
	private final HistoryGroupMapper historyGroupMapper;

	@Override
	public List<HistoryGroupVO> selectHistoryGroupListKo(HistoryGroupVO historyGroupVO) throws Exception {
		return historyGroupMapper.selectHistoryGroupListKo(historyGroupVO);
	}

	@Override
	public List<HistoryGroupVO> selectHistoryGroupListEn(HistoryGroupVO historyGroupVO) throws Exception {
		return historyGroupMapper.selectHistoryGroupListEn(historyGroupVO);
	}

	@Override
	public List<HistoryGroupVO> selectHistoryGroupListCn(HistoryGroupVO historyGroupVO) throws Exception {
		return historyGroupMapper.selectHistoryGroupListCn(historyGroupVO);
	}

}
