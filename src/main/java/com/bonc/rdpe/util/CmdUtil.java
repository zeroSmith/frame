package com.bonc.rdpe.util;

import java.io.IOException;

import com.bonc.rdpe.exception.RdpeException;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import lombok.Data;
import lombok.extern.log4j.Log4j;

/**
 * SSH 工具类
 * 
 * @author bianshen
 *
 */
@Data
@Log4j
public class CmdUtil {

	private Connection connection;

	private Session session;

	public void init(String ip, int port, String name, String pwd) throws RdpeException {
		try {
			//log.info("ssh登录主机" + ip);
			this.setConnection(new Connection(ip, port));
			connection.connect();
			boolean isAuthenticated = connection.authenticateWithPassword(name, pwd);
			if (isAuthenticated) {
				//log.info("用户" + name + "认证成功");
				this.setSession(connection.openSession());
			} else {
				throw new RdpeException("ssh登录主机" + ip + "时认证失败");
			}
		} catch (IOException e) {
			//log.error(e.getMessage(), e);
			throw new RdpeException("初始化ssh登录时发生异常", e);
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * 执行shell命令脚本 多个命令用"&&"分开
	 * 
	 * @param cmds
	 * @param islog
	 * @throws RdpeException
	 */
	public void execCmds(String cmds) throws RdpeException {
		try {
			//log.info("execCmds:" + cmds);
			//log.info("-----execCmds start-----");
			long start = System.currentTimeMillis();
			session.execCommand(cmds);
			long end = System.currentTimeMillis();
			//log.info("-----execCmds end  耗时:" + String.valueOf(end - start) + "-----");
		} catch (Exception e) {
			//log.error(e.getMessage(), e);
			throw new RdpeException("执行命令 " + cmds + " 时发生异常", e);
		} finally {
			destory();
		}
	}

	/**
	 * 执行scp命令
	 * 
	 * @param session
	 * @param localFiles
	 * @param remoteTargetDirectory
	 * @throws RdpeException
	 */
	public void execScp(String[] localFiles, String remoteTargetDirectory) throws RdpeException {
		try {
			// 先创建远程目录
			//log.info("-----execscp start-----");
			long start = System.currentTimeMillis();
			session.execCommand("mkdir -p " + remoteTargetDirectory);
			SCPClient client = new SCPClient(this.getConnection());
			client.put(localFiles, remoteTargetDirectory, "0755");
			long end = System.currentTimeMillis();
			//log.info("-----execscp end  耗时:" + String.valueOf(end - start) + "-----");
		} catch (Exception e) {
			//log.error(e.getMessage(), e);
			throw new RdpeException("执行scp操作时发生异常", e);
		} finally {
			destory();
		}
	}

	/**
	 * 
	 */
	private void destory() {
		if (this.getSession() != null) {
			session.close();
		}
		if (this.getConnection() != null) {
			connection.close();
		}
	}

}
