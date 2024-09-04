package jeilm.api.app.ethics.service;

import java.util.List;

import jeilm.api.app.ethics.vo.EthicsReceiveVO;

public interface EthicsReceiveService {

	/**
	 * 윤리경영 수신메일 리스트 조회
	 * @param ethicsReceiveVO
	 * @return
	 * @throws Exception
	 */
	List<EthicsReceiveVO> selectEthicsReceiveList(EthicsReceiveVO ethicsReceiveVO) throws Exception;
	
}
