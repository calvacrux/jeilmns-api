package jeilm.api.app.certificate.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.certificate.service.CertificateService;
import jeilm.api.app.certificate.service.mapper.CertificateMapper;
import jeilm.api.app.certificate.vo.CertificateVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
	
	private final CertificateMapper certificateMapper;

	@Override
	public List<CertificateVO> selectCertificateListKo(CertificateVO certificateVO) throws Exception {
		return certificateMapper.selectCertificateListKo(certificateVO);
	}

	@Override
	public List<CertificateVO> selectCertificateListEn(CertificateVO certificateVO) throws Exception {
		return certificateMapper.selectCertificateListEn(certificateVO);
	}

	@Override
	public List<CertificateVO> selectCertificateListCn(CertificateVO certificateVO) throws Exception {
		return certificateMapper.selectCertificateListCn(certificateVO);
	}

}
