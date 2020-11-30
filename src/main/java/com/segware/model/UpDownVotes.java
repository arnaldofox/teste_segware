package com.segware.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Classe modelo UpDownVotes que presenta uma Entidade na base dados
 *
 * @author Arnaldo
 */
@Entity
@Table(name = "upvotes")
public class UpDownVotes implements Serializable {

    private static final long serialVersionUID = 6306074824480922046L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
    @Column(name = "dt_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;

    @Column(length = 50)
    @NotBlank
    private String ip;

    @NotNull
    @ManyToOne
    private PostModel post;

    @Column(name = "tp_upvote")
    @Enumerated(EnumType.STRING)
    private TipoUpvotes tipoUpvotes;

    public UpDownVotes() {
    }

    public UpDownVotes(Date dataHora, String ip, PostModel post, TipoUpvotes tipoUpvotes) {
        this.dataHora = dataHora;
        this.ip = ip;
        this.post = post;
        this.tipoUpvotes = tipoUpvotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }

    public TipoUpvotes getTipoUpvotes() {
        return tipoUpvotes;
    }

    public void setTipoUpvotes(TipoUpvotes tipoUpvotes) {
        this.tipoUpvotes = tipoUpvotes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UpDownVotes other = (UpDownVotes) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
