package com.kindcat.archivemedo.db.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author dreamer
 * @version 1.0.3.42 Класс для работы со списком документов
 */
@Entity
@Table(name = "documents")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "Documents")
public class Documents implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doc")
    private int idDoc;

    @Column(name = "id_schema", insertable = false, updatable = false)
    private Short idSchema;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_schema")
    private SchemaXml schemaXml;

    @Column(name = "uid_pkg")
    private String uidPkg;

    @Column(name = "uid_doc")
    private String uidDoc;

    @Column(name = "id_in_out")
    private Short idInOut;

    @Column(name = "id_sender")
    private Integer idSender;

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sender", insertable = false, updatable = false, nullable = true)
    private Senders sendersDoc;

    @Column(name = "id_recipient")
    private Integer idRecipient;

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recipient", insertable = false, updatable = false, nullable = true)
    private Recipients recipientsDoc;

    @Column(name = "in_num")
    private String inNum;

    @Column(name = "in_date")
    @Temporal(TemporalType.DATE)
    private Date inDate;
//    private LocalDate inDate;

    @Column(name = "ex_num")
    private String exNum;

    @Column(name = "ex_date")
    @Temporal(TemporalType.DATE)
    private Date exDate;
//    private LocalDate exDate;

    @Column(name = "dsp")
    private boolean dsp;

    @Column(name = "when_create")
    private LocalDateTime whenCreate;
//    private Date whenCreate;

    @Column(name = "link")
    private String link;

    public int getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
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

    public String getUidDoc() {
        return uidDoc;
    }

    public void setUidDoc(String uidDoc) {
        this.uidDoc = uidDoc;
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

    public Senders getSendersDoc() {
        return sendersDoc;
    }

    public void setSendersDoc(Senders sendersDoc) {
        this.sendersDoc = sendersDoc;
    }

    public Integer getIdRecipient() {
        return idRecipient;
    }

    public void setIdRecipient(Integer idRecipient) {
        this.idRecipient = idRecipient;
    }

    public Recipients getRecipientsDoc() {
        return recipientsDoc;
    }

    public void setRecipientsDoc(Recipients recipientsDoc) {
        this.recipientsDoc = recipientsDoc;
    }

    public String getInNum() {
        return inNum;
    }

    public void setInNum(String inNum) {
        this.inNum = inNum;
    }

//    public LocalDate getInDate() {
    public Date getInDate() {
        return inDate;
    }

//    public void setInDate(LocalDate inDate) {
    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getExNum() {
        return exNum;
    }

    public void setExNum(String exNum) {
        this.exNum = exNum;
    }

//    public LocalDate getExDate() {
    public Date getExDate() {
        return exDate;
    }

//    public void setExDate(LocalDate exDate) {
    public void setExDate(Date exDate) {
        this.exDate = exDate;
    }

    public boolean isDsp() {
        return dsp;
    }

    public void setDsp(boolean dsp) {
        this.dsp = dsp;
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
}
