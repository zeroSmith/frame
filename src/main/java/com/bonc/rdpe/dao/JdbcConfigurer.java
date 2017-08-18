package com.bonc.rdpe.dao;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.bonc.rdpe.util.AesUtil;


public class JdbcConfigurer extends PropertyPlaceholderConfigurer {

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {

		String username = props.getProperty("jdbc.username");
		if (username != null) {
			props.setProperty("jdbc.username", AesUtil.decrypt(username));
		}

		String password = props.getProperty("jdbc.password");
		if (password != null) {
			props.setProperty("jdbc.password", AesUtil.decrypt(password));
		}

		String url = props.getProperty("jdbc.url");
		if (url != null) {
			props.setProperty("jdbc.url", AesUtil.decrypt(url));
		}

		String driverClassName = props.getProperty("jdbc.driverClassName");
		if (driverClassName != null) {
			props.setProperty("jdbc.driverClassName", AesUtil.decrypt(driverClassName));
		}

		super.processProperties(beanFactoryToProcess, props);
	}

}
