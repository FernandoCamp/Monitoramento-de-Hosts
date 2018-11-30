package br.com.fernando.ping;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fernando.entidades.MonitoriaEntidade;
import br.com.fernando.repositorio.MonitoriaRepositorio;

public class Ping {

	@Autowired
	public static MonitoriaRepositorio monitoriaRepositorio;

	public static void pingando() throws IOException, InterruptedException {
		for (MonitoriaEntidade monitoriaEntidade : monitoriaRepositorio.findAll()) {

			try {
				if (ping(monitoriaEntidade.getIp()))
					monitoriaEntidade.setStatus(true);
			} catch (IOException e) {
				throw new WrapperException(e);
			}
		}
	}

	public static boolean ping(String ip) throws IOException, InterruptedException {
		Process p1 = java.lang.Runtime.getRuntime().exec("fping -t 15 " + ip);
		return p1.waitFor() == 0;
	}

}
