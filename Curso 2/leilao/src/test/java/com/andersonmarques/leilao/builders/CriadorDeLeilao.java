package com.andersonmarques.leilao.builders;

import com.andersonmarques.leilao.models.Lance;
import com.andersonmarques.leilao.models.Leilao;
import com.andersonmarques.leilao.models.Usuario;
/**
 * Test data builder -> builder para ajudar na criação de testes.
 */
public class CriadorDeLeilao {
	
	
	private Leilao leilao;

	public CriadorDeLeilao criarLeilaoDe(String descricao) {
		leilao = new Leilao(descricao);
		return this;
	}

	public CriadorDeLeilao propor(Usuario usuario, double valor) {
		leilao.propoe(new Lance(usuario, valor));
		return this;
	}

	public Leilao construir() {
		return this.leilao;
	}
}