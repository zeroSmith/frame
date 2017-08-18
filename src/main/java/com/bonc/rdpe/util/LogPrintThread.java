package com.bonc.rdpe.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class LogPrintThread extends Thread {

	private static final Logger LOG = Logger.getLogger(LogPrintThread.class);

	private String name;
	
	private Session session;

	public LogPrintThread(String name, Session session) {
		this.name = name;
		this.session = session;
	}

	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(new StreamGobbler(session.getStdout())));
		while (true) {
			try {
				String line = br.readLine();
				if (line == null) {
					LOG.info(name + "-->日志打印完毕");
					br.close();
					session.close();
					break;
				} else {
					LOG.info(name + "-->" + line);
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
				break;
			}
		}
	}

}
