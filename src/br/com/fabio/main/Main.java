package br.com.fabio.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import br.com.fabio.controlador.ControladorTwitter;
import br.com.fabio.util.Mensagem;
import br.com.fabio.util.TiposTweet;
import twitter4j.TwitterException;

public class Main {
	public static void main(String[] args) {
		try {
			ControladorTwitter controladorTwitter = new ControladorTwitter(); 

			Map<LocalDate, Long> map = controladorTwitter.getUltimaSemana(TiposTweet.TWEETS);
			map.forEach((x, y) -> System.out.println("Data: " + x + "  Tweets: " + y));	
			
			map = controladorTwitter.getUltimaSemana(TiposTweet.RETWEETS);
			map.forEach((x, y) -> System.out.println("Data: " + x	 + "  Retweets: " + y));

			map = controladorTwitter.getUltimaSemana(TiposTweet.FAVORITACAO);
			map.forEach((x, y) -> System.out.println("Data: " + x + "  Favoritações: " + y));

			ArrayList<Mensagem> msg = controladorTwitter.getMensagemPorNome(controladorTwitter.getMensagens());
			if (msg.size() > 0) {
				System.out.println("Primeiro nome: "	+ msg.get(0).getNome());
				System.out.println("Último nome: " + msg.get(msg.size() - 1).getNome());
			}

			msg = controladorTwitter.getMensagemDataInversa(controladorTwitter.getMensagens());
			if (msg.size() > 0) {
				System.out.println("Mais recente: "	+ msg.get(0).getData());
				System.out.println("Menos recente: "	+ msg.get(msg.size() - 1).getData());
			}	
			//Enviando um tweet.
			controladorTwitter.enviaMensagem("Nova mensagem enviada novamente o professor @michelpf");
			
		}catch (TwitterException e) {
			System.out.println("Problema na API Twitter4j: " + e.getMessage());
		}catch(Exception e){
			System.out.println("Erro : " + e);
		}
	}
}
