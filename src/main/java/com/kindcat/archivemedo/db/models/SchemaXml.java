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
 * @version 1.0.4.40 Класс для работы с таблицей, содержащей форматы обмена МЭДО
 * (схема xml)
 */
@Entity
@Table(name = "schema_xml")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "SchemaXml")
public class SchemaXml implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schema")
    private Short idSchema;

    @Column(name = "name_schema")
    private String nameSchema;

//    @OneToMany(mappedBy = "schemaXml", fetch=FetchType.EAGER,cascade={CascadeType.PERSIST, CascadeType.MERGE})
//    private List<Documents> docSchema = new ArrayList<>();

    public Short getIdSchema() {
        return idSchema;
    }

    public void setIdSchema(Short idSchema) {
        this.idSchema = idSchema;
    }

    public String getNameSchema() {
        return nameSchema;
    }

    public void setNameSchema(String nameSchema) {
        this.nameSchema = nameSchema;
    }

//    public List<Documents> getDocSchema() {
//        return docSchema;
//    }
//
//    public void setDocSchema(List<Documents> docSchema) {
//        this.docSchema = docSchema;
//    }
}
