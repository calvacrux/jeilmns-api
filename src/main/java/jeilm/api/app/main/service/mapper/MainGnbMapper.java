package jeilm.api.app.main.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.main.vo.MainGnbListVO;

@Mapper
public interface MainGnbMapper {

	/**
	 * 메인 GNB 리스트
	 * @param mainGnbListVO
	 * @return
	 * @throws Exception
	 */
	List<MainGnbListVO> selectMainGnbList(MainGnbListVO mainGnbListVO) throws Exception;  
	
}
