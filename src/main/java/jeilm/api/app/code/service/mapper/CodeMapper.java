package jeilm.api.app.code.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.code.vo.CodeVO;

@Mapper
public interface CodeMapper {

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
