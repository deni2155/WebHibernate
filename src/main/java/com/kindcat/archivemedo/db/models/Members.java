package com.kindcat.archivemedo.db.models;

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
 * @version 1.0.0.18 Модель для работы с участниками МЭДО
 */
@Entity
@Table(name = "Members")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Members")
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_member")
    private Short idMembers;

    @Column(name = "name_org")
    private String nameOrg;

    @Column(name = "guid")
    private String guid;

    @Column(name = "addressee")
    private String addr;

    public Short getIdMembers() {
        return idMembers;
    }

    public void setIdMembers(Short idMembers) {
        this.idMembers = idMembers;
    }

    public String getNameOrg() {
        return nameOrg;
    }

    public void setNameOrg(String nameOrg) {
        this.nameOrg = nameOrg;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
