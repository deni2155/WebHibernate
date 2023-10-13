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
 * @version 1.0.4.44 Класс для работы
 */
@Entity
@Table(name = "senders")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "Senders")
public class Senders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sender")
    private int idSender;

    @OneToOne(mappedBy = "sendersDoc", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Documents documentsSenders;

    @OneToOne(mappedBy = "sendersNotif", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Notifs notifsSenders;

    @OneToOne(mappedBy = "sendersReceipt", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @BatchSize(size = 20)
    private Receipts receiptSenders;

    @Column(name = "id_member")
    private Short idMemeber;

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @BatchSize(size = 20)
    @JoinColumn(name = "id_member", insertable = false, updatable = false)
    private Members membersSenders;

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public Documents getDocumentsSenders() {
        return documentsSenders;
    }

    public void setDocumentsSenders(Documents documentsSenders) {
        this.documentsSenders = documentsSenders;
    }

    public Notifs getNotifsSenders() {
        return notifsSenders;
    }

    public void setNotifsSenders(Notifs notifsSenders) {
        this.notifsSenders = notifsSenders;
    }

    public Receipts getReceiptSenders() {
        return receiptSenders;
    }

    public void setReceiptSenders(Receipts receiptSenders) {
        this.receiptSenders = receiptSenders;
    }

    public Short getIdMemeber() {
        return idMemeber;
    }

    public void setIdMemeber(Short idMemeber) {
        this.idMemeber = idMemeber;
    }

    public Members getMembersSenders() {
        return membersSenders;
    }

    public void setMembersSenders(Members membersSenders) {
        this.membersSenders = membersSenders;
    }
}
