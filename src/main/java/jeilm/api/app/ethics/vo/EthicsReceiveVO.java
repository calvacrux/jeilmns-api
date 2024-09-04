package jeilm.api.app.ethics.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class EthicsReceiveVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String receive_sn;		// 수신 번호
	private String receive_nm;		// 수신 이름
	private String receive_mail;	// 수신 메일
	
}
