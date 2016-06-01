package br.com.fabio.controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import br.com.fabio.comparadores.CompData;
import br.com.fabio.comparadores.CompNome;
import br.com.fabio.util.Mensagem;
import br.com.fabio.util.TiposTweet;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class ControladorTwitter {
	private static Twitter twitter;
	private ArrayList<Mensagem> listMensagem = null;
	private String desde = "";
	private String assunto = "#java";


	public ControladorTwitter() throws IOException {
		super();
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey("rF4JV1qnj5qIMjZY5cswNKmy5");
		builder.setOAuthConsumerSecret("w5Id1clBwmPfCPfHM1seV307dV4tGfbd0mT2KmTjelbFcn7zyF");

		Configuration configuration = builder.build();
		TwitterFactory factory = new TwitterFactory(configuration);
		twitter = factory.getInstance();
		AccessToken accessToken = loadAccessToken();
		twitter.setOAuthAccessToken(accessToken);
	}
	

	private static AccessToken loadAccessToken() {
		String token = "26575371-HUhRhLE9pEFYtZhJcdmJ2tRgBGiHmUzxaHhY7rn3e";
		String tokenSecret = "araOlzHQJhygDApiWlgnYRzioSBXpXkQr9AGgHmxc8dfu";
		return new AccessToken(token, tokenSecret);
	}

	public HashMap<LocalDate, Long> getUltimaSemana(TiposTweet lancamento) throws TwitterException {
		HashMap<LocalDate, Long> retorno = new HashMap<>();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate data = LocalDate.now();
		desde = data.minusDays(7).format(formatador);
		ArrayList<Mensagem> twitters = getMensagemPorData(getMensagens());

		for (Mensagem t : twitters) {
			long tmp = retorno.get(t.getData()) == null ? 0 : retorno.get(t.getData());
			if (lancamento == TiposTweet.TWEETS) {
				tmp++;
			} else if (lancamento == TiposTweet.RETWEETS) {
				tmp += t.getQtdTweet();
			} else if (lancamento == TiposTweet.FAVORITACAO) {
				tmp += t.getFavoritado();
			}
			retorno.remove(t.getData());
			retorno.put(t.getData(), tmp);
		}
		return retorno;
	}


	public ArrayList<Mensagem> getMensagens() throws TwitterException {
		listMensagem = new ArrayList<>();
		Query query = new Query();
		query.setSince(desde);
		query.setQuery(assunto);

		QueryResult result;

		result = twitter.search(query);

		while (result.hasNext()) {
			query = result.nextQuery();
			for (Status status : result.getTweets()) {
				listMensagem.add(new Mensagem(status.getCreatedAt(), status.getUser().getFavouritesCount(),
						status.getUser().getScreenName(), status.getText(), status.getUser().getName(),
						status.getRetweetCount()));
			}
			result = twitter.search(query);
		}

		return listMensagem;
	}

	public ArrayList<Mensagem> getMensagemPorNome(ArrayList<Mensagem> mensagens) throws TwitterException {
		Collections.sort(mensagens, new CompNome());
		return mensagens;
	}

	public ArrayList<Mensagem> getMensagemPorData(ArrayList<Mensagem> mensagens) throws TwitterException {
		Collections.sort(mensagens, new CompData());
		return mensagens;
	}
	
	public void enviaMensagem(String mensagem) throws TwitterException {
		Status status = twitter.updateStatus(mensagem);
		System.out.println("Tweet postado:  " + status.getText() );
	}

	public ArrayList<Mensagem> getMensagemDataInversa(ArrayList<Mensagem> mensagens) throws TwitterException {
		mensagens = getMensagemPorData(mensagens);
		Collections.reverse(mensagens);
		return mensagens;
	}

}