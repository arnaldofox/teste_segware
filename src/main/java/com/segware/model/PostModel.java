package com.segware.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

/**
 * Classe modelo PostModel que presenta uma Entidade na base dados
 *
 * @author Arnaldo
 */
@Entity
@Table(name = "post")
public class PostModel implements Serializable {

    private static final long serialVersionUID = 677374038431016053L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Descrição é obrigatório")
    @Column(columnDefinition = "text")
    private String descricao;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
    @Column(name = "dt_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;

    @Column(length = 50)
    private String ip;

//    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
//    private List<Upvotes> upvotes;

    @Transient
    private Long upVote;

    @Transient
    private Long downVote;

    public PostModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

//    public List<Upvotes> getUpvotes() {
//        return upvotes;
//    }
//
//    public void setUpvotes(List<Upvotes> upvotes) {
//        this.upvotes = upvotes;
//    }

    public Long getUpVote() {
        return upVote;
    }

    public void setUpVote(Long upVote) {
        this.upVote = upVote;
    }

    public Long getDownVote() {
        return downVote;
    }

    public void setDownVote(Long downVote) {
        this.downVote = downVote;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final PostModel other = (PostModel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
