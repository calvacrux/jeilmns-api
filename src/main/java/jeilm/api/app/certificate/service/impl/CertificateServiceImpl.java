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
	public List<CertificateVO> selectCertificateList(CertificateVO certificateVO) throws Exception {
		return certificateMapper.selectCertificateList(certificateVO);
	}

}
