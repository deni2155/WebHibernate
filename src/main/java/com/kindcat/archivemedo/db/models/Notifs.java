package com.kindcat.archivemedo.db.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author dreamer
 * @version 1.0.4.49 Модель для работы со списком уведомлений
 */
@Entity
@Table(name = "notifications")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "Notifs")
public class Notifs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notif")
    private int idNotif;

    @Column(name = "id_schema", insertable = false, updatable = false)
    private Short idSchema;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_schema")
    private SchemaXml schemaXml;

    @Column(name = "uid_pkg")
    private String uidPkg;

    @Column(name = "for_uid")
    private String forUid;

    @Column(name = "id_type", insertable = false, updatable = false)
    private Short idTypeNotif;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_type")
    private TypeNotif notifType;

    @Column(name = "id_in_out")
    private Short idInOut;

    @Column(name = "id_sender")
    private Integer idSender;

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_sender", insertable = false, updatable = false)
    private Senders sendersNotif;

    @Column(name = "id_recipient")
    private Integer idRecipient;

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_recipient", insertable = false, updatable = false)
    private Recipients recipientsNotif;

    @Column(name = "ex_num")
    private String exNum;

    @Column(name = "ex_date")
    @Temporal(TemporalType.DATE)
    private Date exDate;

    @Column(name = "in_num")
    private String inNum;

    @Column(name = "in_date")
    @Temporal(TemporalType.DATE)
    private Date inDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "comment")
    private String comment;

    @Column(name = "link")
    private String link;

    @Column(name = "when_create")
    private LocalDateTime whenCreate;

    public int getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(int idNotif) {
        this.idNotif = idNotif;
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

    public Short getIdTypeNotif() {
        return idTypeNotif;
    }

    public void setIdTypeNotif(Short idTypeNotif) {
        this.idTypeNotif = idTypeNotif;
    }

    public TypeNotif getNotifType() {
        return notifType;
    }

    public void setNotifType(TypeNotif notifType) {
        this.notifType = notifType;
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

    public Senders getSendersNotif() {
        return sendersNotif;
    }

    public void setSendersNotif(Senders sendersNotif) {
        this.sendersNotif = sendersNotif;
    }

    public Integer getIdRecipient() {
        return idRecipient;
    }

    public void setIdRecipient(Integer idRecipient) {
        this.idRecipient = idRecipient;
    }

    public Recipients getRecipientsNotif() {
        return recipientsNotif;
    }

    public void setRecipientsNotif(Recipients recipientsNotif) {
        this.recipientsNotif = recipientsNotif;
    }

    public String getExNum() {
        return exNum;
    }

    public void setExNum(String exNum) {
        this.exNum = exNum;
    }

    public Date getExDate() {
        return exDate;
    }

    public void setExDate(Date exDate) {
        this.exDate = exDate;
    }

    public String getInNum() {
        return inNum;
    }

    public void setInNum(String inNum) {
        this.inNum = inNum;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getWhenCreate() {
        return whenCreate;
    }

    public void setWhenCreate(LocalDateTime whenCreate) {
        this.whenCreate = whenCreate;
    }
}
