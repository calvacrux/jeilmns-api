package jeilm.api.app.esg.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.esg.vo.EsgAuditVO;

@Mapper
public interface EsgAuditMapper {

	/**
	 * 외부감사 리스트 조회
	 * @param esgAuditVO
	 * @return
	 * @throws Exception
	 */
	List<EsgAuditVO> selectEsgAuditList(EsgAuditVO esgAuditVO)  throws Exception;
	
	
}
