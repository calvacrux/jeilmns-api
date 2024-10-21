package jeilm.api.app.certificate.service;

import java.util.List;

import jeilm.api.app.certificate.vo.CertificateVO;

public interface CertificateService {

	/**
	 * 인증서 리스트 조회
	 * @param certificateVO
	 * @return
	 * @throws Exception
	 */
	List<CertificateVO> selectCertificateList(CertificateVO certificateVO) throws Exception;
	
}
