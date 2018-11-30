package br.com.fernando.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.com.fernando.entidades.MonitoriaEntidade;

public interface MonitoriaRepositorio extends CrudRepository<MonitoriaEntidade, Long> {

}
