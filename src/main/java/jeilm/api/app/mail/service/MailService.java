package jeilm.api.app.mail.service;

import jeilm.api.app.mail.vo.MailVO;

public interface MailService {

	/**
	 * 메일 발송
	 * @param mailVO
	 * @throws Exception
	 */
	void sendMail(MailVO mailVO) throws Exception;
}
