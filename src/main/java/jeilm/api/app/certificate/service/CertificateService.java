package jeilm.api.app.certificate.service;

import java.util.List;

import jeilm.api.app.certificate.vo.CertificateVO;

public interface CertificateService {

	/**
	 * 인증서 리스트 조회 - Ko
	 * @param certificateVO
	 * @return
	 * @throws Exception
	 */
	List<CertificateVO> selectCertificateListKo(CertificateVO certificateVO) throws Exception;
	
	/**
	 * 인증서 리스트 조회 - En
	 * @param certificateVO
	 * @return
	 * @throws Exception
	 */
	List<CertificateVO> selectCertificateListEn(CertificateVO certificateVO) throws Exception;
	
	/**
	 * 인증서 리스트 조회 - Cn
	 * @param certificateVO
	 * @return
	 * @throws Exception
	 */
	List<CertificateVO> selectCertificateListCn(CertificateVO certificateVO) throws Exception;
	
	
}
