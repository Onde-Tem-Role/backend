package org.generation.ondetemrole.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_postagem")
public class Postagem {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotBlank
		@Size(min = 5, max = 100, message = "O campo título deve conter no mínimo 5 e no máximo 100 caracteres.")
		private String local;

		@NotBlank
		@Size(min = 5, max = 10000, message = "O campo texto deve conter no mínimo 5 e no máximo 10000 caracteres.")
		private String texto;
		
		@UpdateTimestamp
		private LocalDateTime data;
		
		private String foto;
		
		@NotNull
		private Long contato;
		
		@NotNull
		private double valor;
		
		@NotNull
		private String tipoTurismo;
		
		@ManyToOne
		@JsonIgnoreProperties("postagem")
		private Usuario usuario;
		
		@ManyToOne
		@JsonIgnoreProperties("postagem")
		private Tema tema;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getLocal() {
			return local;
		}

		public void setLocal(String local) {
			this.local = local;
		}

		public String getTexto() {
			return texto;
		}

		public void setTexto(String texto) {
			this.texto = texto;
		}

		public LocalDateTime getData() {
			return data;
		}

		public void setData(LocalDateTime data) {
			this.data = data;
		}

		public String getFoto() {
			return foto;
		}

		public void setFoto(String foto) {
			this.foto = foto;
		}

		public Long getContato() {
			return contato;
		}

		public void setContato(Long contato) {
			this.contato = contato;
		}

		public double getValor() {
			return valor;
		}

		public void setValor(double valor) {
			this.valor = valor;
		}

		public String getTipoTurismo() {
			return tipoTurismo;
		}

		public void setTipoTurismo(String tipoTurismo) {
			this.tipoTurismo = tipoTurismo;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		public Tema getTema() {
			return tema;
		}

		public void setTema(Tema tema) {
			this.tema = tema;
		}
}	