package com.bonc.rdpe.security.repository;
/** 
 * @author 作者: jxw 
 * @date 创建时间: 2017年1月17日 下午2:32:47 
 * @version 版本: 1.0 
*/
public class SecurityRepositoryFactory {
	
	private static ISecurityRepository securityRepository;

	public static ISecurityRepository getRepository(){
		if(securityRepository == null){
			securityRepository = new SessionSecurityRepository();
		}
		return securityRepository;
	}
}

