package org.generation.ondetemrole.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tb_postagem")
public class Postagem {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long idPost;

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
		private int contato;
		
		@NotNull
		private double valor;
		
		@NotNull
		private String tipoTurismo;

		public long getIdPost() {
			return idPost;
		}

		public void setIdPost(long idPost) {
			this.idPost = idPost;
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

		public int getContato() {
			return contato;
		}

		public void setContato(int contato) {
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
	
}
