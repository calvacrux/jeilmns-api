package jeilm.api.app.history.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.history.service.HistoryService;
import jeilm.api.app.history.service.mapper.HistoryMapper;
import jeilm.api.app.history.vo.HistoryVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
	
	private final HistoryMapper historyMapper;

	@Override
	public List<HistoryVO> selectHistoryListKo(HistoryVO historyVO) throws Exception {
		return historyMapper.selectHistoryListKo(historyVO);
	}

	@Override
	public List<HistoryVO> selectHistoryListEn(HistoryVO historyVO) throws Exception {
		return historyMapper.selectHistoryListEn(historyVO);
	}

	@Override
	public List<HistoryVO> selectHistoryListCn(HistoryVO historyVO) throws Exception {
		return historyMapper.selectHistoryListCn(historyVO);
	}

}
