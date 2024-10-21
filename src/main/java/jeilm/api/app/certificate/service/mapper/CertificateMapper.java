package jeilm.api.app.certificate.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.certificate.vo.CertificateVO;

@Mapper
public interface CertificateMapper {

	/**
	 * 인증서 리스트 조회
	 * @param certificateVO
	 * @return
	 * @throws Exception
	 */
	List<CertificateVO> selectCertificateList(CertificateVO certificateVO) throws Exception;
	
}
