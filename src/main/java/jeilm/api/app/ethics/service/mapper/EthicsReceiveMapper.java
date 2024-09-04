package jeilm.api.app.ethics.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.ethics.vo.EthicsReceiveVO;

@Mapper
public interface EthicsReceiveMapper {

	/**
	 * 윤리경영 수신메일 리스트 조회
	 * @param ethicsReceiveVO
	 * @return
	 * @throws Exception
	 */
	List<EthicsReceiveVO> selectEthicsReceiveList(EthicsReceiveVO ethicsReceiveVO) throws Exception;
	
}
