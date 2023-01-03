package com.ldapauth.common.dto;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlType(propOrder = { "status", "message", "data" })
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResponseDto {

	private String status;
	
	@Builder.Default
	private String message = "Successfull.";
	
	private Object data;
	private String errorMessage;
	private Integer errorCode;	
	private Integer statusCode;	
	
	private String token;
	private String refreshToken;	
	private String userId;
	private String type;
	private String userRole;
	
	public ResponseDto(Object data){
		this.data=data;
	}	
	
}