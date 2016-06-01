package br.com.fabio.comparadores;

import java.util.Comparator;

import br.com.fabio.util.Mensagem;

public class CompData  implements Comparator<Mensagem>{
	@Override
	public int compare(Mensagem o1, Mensagem o2) {
		return o1.getData().compareTo(o2.getData());
	}
}


