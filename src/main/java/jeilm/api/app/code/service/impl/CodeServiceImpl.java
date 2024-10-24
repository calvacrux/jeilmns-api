package jeilm.api.app.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.code.service.CodeService;
import jeilm.api.app.code.service.mapper.CodeMapper;
import jeilm.api.app.code.vo.CodeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {

	private final CodeMapper codeMapper;
	
	@Override
	public CodeVO selectCode(CodeVO codeVO) throws Exception {
		return codeMapper.selectCode(codeVO);
	}
	
	@Override
	public List<CodeVO> selectCodeList(CodeVO codeVO) throws Exception {
		return codeMapper.selectCodeList(codeVO);
	}

}
