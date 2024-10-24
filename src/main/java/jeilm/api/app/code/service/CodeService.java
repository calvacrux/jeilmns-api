package jeilm.api.app.code.service;

import java.util.List;

import jeilm.api.app.code.vo.CodeVO;

public interface CodeService {
	
	/**
	 * 코드 조회
	 * @param codeVO
	 * @return
	 * @throws Exception
	 */
	CodeVO selectCode(CodeVO codeVO) throws Exception;
	
	/**
	 * 코드 리스트
	 * @param codeVO
	 * @return
	 * @throws Exception
	 */
	List<CodeVO> selectCodeList(CodeVO codeVO) throws Exception;

}
