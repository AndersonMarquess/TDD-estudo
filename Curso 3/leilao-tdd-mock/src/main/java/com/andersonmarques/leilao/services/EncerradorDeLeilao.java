package com.andersonmarques.leilao.services;

import java.util.Calendar;
import java.util.List;

import com.andersonmarques.leilao.infra.dao.RepositorioDeLeiloes;
import com.andersonmarques.leilao.infra.interfaces.EnviadorDeEmail;
import com.andersonmarques.leilao.models.Leilao;

public class EncerradorDeLeilao {

	private int total = 0;
	private RepositorioDeLeiloes dao;
	private EnviadorDeEmail carteiro;

	public EncerradorDeLeilao(RepositorioDeLeiloes dao, EnviadorDeEmail carteiro) {
		this.dao = dao;
		this.carteiro = carteiro;
	}

	public void encerra() {
		List<Leilao> todosLeiloesCorrentes = dao.correntes();

		for (Leilao leilao : todosLeiloesCorrentes) {
			try {
				if (comecouSemanaPassada(leilao)) {
					leilao.encerra();
					total++;
					dao.atualiza(leilao);
					carteiro.envia(leilao);
				}		
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private boolean comecouSemanaPassada(Leilao leilao) {
		return diasEntre(leilao.getData(), Calendar.getInstance()) >= 7;
	}

	private int diasEntre(Calendar inicio, Calendar fim) {
		Calendar data = (Calendar) inicio.clone();
		int diasNoIntervalo = 0;
		while (data.before(fim)) {
			data.add(Calendar.DAY_OF_MONTH, 1);
			diasNoIntervalo++;
		}

		return diasNoIntervalo;
	}

	public int getTotalEncerrados() {
		return total;
	}
}
