package br.com.fabio.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Mensagem {
 	private long favoritado;
 	private long qtdTweet;
 	private LocalDate data;
 	private String usuario;
 	private String nome;
 	private String mensagem;
	
 	public Mensagem(Date data, long favoritado, String usuario, String mensagem,String nome,long retweetCount) {
		super();
		this.data = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.qtdTweet= retweetCount;
		this.favoritado = favoritado;
		this.nome = nome;
		this.usuario = usuario;
		this.mensagem = mensagem;
	}

	public long getFavoritado() {
		return favoritado;
	}

	public void setFavoritado(long favoritado) {
		this.favoritado = favoritado;
	}

	public long getQtdTweet() {
		return qtdTweet;
	}

	public void setQtdTweet(long qtdTweet) {
		this.qtdTweet = qtdTweet;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
 	

 }