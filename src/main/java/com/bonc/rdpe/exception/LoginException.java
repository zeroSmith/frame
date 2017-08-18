package com.bonc.rdpe.exception;

public class LoginException extends Exception{
	
	public enum ExceptionType { 
		//用户不存在
		notExist
        //密码错误
        ,pwdFalse
        //用户被锁定
        ,isLocked
        //用户名为空
        ,isEmpty
    }
	
	public LoginException(ExceptionType type){
		super(type.toString());  
	}  
}
