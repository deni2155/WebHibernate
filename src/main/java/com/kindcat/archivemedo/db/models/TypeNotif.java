package com.kindcat.archivemedo.db.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author dreamer
 * @version 1.0.4.49 Модель таблицы со списком типов уведомлений
 */
@Entity
@Table(name = "type_notif")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "TypeNotif")
public class TypeNotif implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private Short idTypeNotif;

    @Column(name = "name_type")
    private String nameType;

    public Short getIdTypeNotif() {
        return idTypeNotif;
    }

    public void setIdTypeNotif(Short idTypeNotif) {
        this.idTypeNotif = idTypeNotif;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

}
