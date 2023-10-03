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
 * @version 1.0.3.39 Класс для описания модели таблицы с типом пакета (входящий
 * и исходящий)
 */
@Entity
@Table(name = "in_out")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "TypePkg")
public class TypePkg implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_in_out")
    private Short idTypePkg;

    @Column(name = "name_in_out")
    private String nameTypePkg;

    public Short getIdTypePkg() {
        return idTypePkg;
    }

    public void setIdTypePkg(Short idTypePkg) {
        this.idTypePkg = idTypePkg;
    }

    public String getNameTypePkg() {
        return nameTypePkg;
    }

    public void setNameTypePkg(String nameTypePkg) {
        this.nameTypePkg = nameTypePkg;
    }
}
