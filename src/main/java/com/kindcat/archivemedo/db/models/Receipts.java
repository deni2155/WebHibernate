package com.kindcat.archivemedo.db.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author dreamer
 * @version 1.0.4.51 Модель таблицы квитанций
 */
@Entity
@Table(name = "receipts")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "Receipts")
public class Receipts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receipt",length = 20)
    private int idReceipt;

    @Column(name = "id_schema", insertable = false, updatable = false,length = 20)
    private Short idSchema;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_schema")
    private SchemaXml schemaXml;

    @Column(name = "uid_pkg",length = 20)
    private String uidPkg;

    @Column(name = "for_uid",length = 20)
    private String forUid;

    @Column(name = "id_in_out",length = 20)
    private Short idInOut;

    @Column(name = "id_sender",length = 20)
    private Integer idSender;

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sender", insertable = false, updatable = false,nullable=true)
    private Senders sendersReceipt;

    @Column(name = "id_recipient",length = 20)
    private Integer idRecipient;

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recipient", insertable = false, updatable = false,nullable=true)
    private Recipients recipientsReceipt;

    @Column(name = "when_create",length = 20)
    private LocalDateTime whenCreate;

    @Column(name = "link",length = 20)
    private String link;

    @Column(name = "delivery",length = 20)
    private boolean deliv;

    @Column(name = "comment",length = 20)
    private String comment;

    public int getIdReceipt() {
        return idReceipt;
    }

    public void setIdReceipt(int idReceipt) {
        this.idReceipt = idReceipt;
    }

    public Short getIdSchema() {
        return idSchema;
    }

    public void setIdSchema(Short idSchema) {
        this.idSchema = idSchema;
    }

    public SchemaXml getSchemaXml() {
        return schemaXml;
    }

    public void setSchemaXml(SchemaXml schemaXml) {
        this.schemaXml = schemaXml;
    }

    public String getUidPkg() {
        return uidPkg;
    }

    public void setUidPkg(String uidPkg) {
        this.uidPkg = uidPkg;
    }

    public String getForUid() {
        return forUid;
    }

    public void setForUid(String forUid) {
        this.forUid = forUid;
    }

    public Short getIdInOut() {
        return idInOut;
    }

    public void setIdInOut(Short idInOut) {
        this.idInOut = idInOut;
    }

    public Integer getIdSender() {
        return idSender;
    }

    public void setIdSender(Integer idSender) {
        this.idSender = idSender;
    }

    public Senders getSendersReceipt() {
        return sendersReceipt;
    }

    public void setSendersReceipt(Senders sendersReceipt) {
        this.sendersReceipt = sendersReceipt;
    }

    public Integer getIdRecipient() {
        return idRecipient;
    }

    public void setIdRecipient(Integer idRecipient) {
        this.idRecipient = idRecipient;
    }

    public Recipients getRecipientsReceipt() {
        return recipientsReceipt;
    }

    public void setRecipientsReceipt(Recipients recipientsReceipt) {
        this.recipientsReceipt = recipientsReceipt;
    }

    public LocalDateTime getWhenCreate() {
        return whenCreate;
    }

    public void setWhenCreate(LocalDateTime whenCreate) {
        this.whenCreate = whenCreate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isDeliv() {
        return deliv;
    }

    public void setDeliv(boolean deliv) {
        this.deliv = deliv;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
