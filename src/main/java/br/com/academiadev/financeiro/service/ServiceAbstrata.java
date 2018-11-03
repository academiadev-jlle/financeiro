package br.com.academiadev.financeiro.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ServiceAbstrata<T, ID> extends JpaRepository<T, ID> {


}
