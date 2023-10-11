package com.kindcat.archivemedo.db.models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author dreamer
 * @version 1.0.4.45 Класс для работы с получателями пакетов
 */
@Entity
@Table(name = "recipients")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Recipients")
public class Recipients implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipient")
    private int idRecipient;

    @OneToOne(mappedBy = "recipients", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Documents documentsRecipient;

    @OneToOne(mappedBy = "recipients", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Documents notifRecipient;

    @OneToOne(mappedBy = "recipients", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Documents receiptRecipient;

    @Column(name = "id_member")
    private Short idMember;

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_member", insertable = false, updatable = false)
    private Members membersRecipients;

    public int getIdRecipient() {
        return idRecipient;
    }

    public void setIdRecipient(int idRecipient) {
        this.idRecipient = idRecipient;
    }

    public Documents getDocumentsRecipient() {
        return documentsRecipient;
    }

    public void setDocumentsRecipient(Documents documentsRecipient) {
        this.documentsRecipient = documentsRecipient;
    }

    public Documents getNotifRecipient() {
        return notifRecipient;
    }

    public void setNotifRecipient(Documents notifRecipient) {
        this.notifRecipient = notifRecipient;
    }

    public Documents getReceiptRecipient() {
        return receiptRecipient;
    }

    public void setReceiptRecipient(Documents receiptRecipient) {
        this.receiptRecipient = receiptRecipient;
    }

    public Short getIdMember() {
        return idMember;
    }

    public void setIdMember(Short idMember) {
        this.idMember = idMember;
    }

    public Members getMembersRecipients() {
        return membersRecipients;
    }

    public void setMembersRecipients(Members membersRecipients) {
        this.membersRecipients = membersRecipients;
    }
}
