package br.com.fernando.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fernando.entidades.MonitoriaEntidade;
import br.com.fernando.ping.Ping;
import br.com.fernando.ping.WrapperException;
import br.com.fernando.repositorio.MonitoriaRepositorio;

@RestController
@RequestMapping("/monitorias")
public class MonitoriaController {

	@Autowired
	public MonitoriaRepositorio monitoriaRepositorio;

	@GetMapping
	public Iterable<MonitoriaEntidade> resposta() throws UnknownHostException, IOException, InterruptedException {
		Iterable<MonitoriaEntidade> findAll = monitoriaRepositorio.findAll();

		try {
			
			StreamSupport.stream(findAll.spliterator(), true).forEach(monitoria -> {

				try {
					
					if (Ping.ping(monitoria.getIp()))
						monitoria.setStatus(true);
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			});
		} catch (WrapperException e) {
			e.throwWrappedException();
		}

		return findAll;

	}

}
