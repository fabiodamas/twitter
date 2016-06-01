package br.com.fabio.comparadores;

import java.util.Comparator;

import br.com.fabio.util.Mensagem;

public class CompNome implements Comparator<Mensagem>{
	@Override
	public int compare(Mensagem o1, Mensagem o2) {
		return o1.getNome().compareTo(o2.getNome());
	}
}


