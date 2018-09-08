package imitori.entity.neo4j;

import java.util.ArrayList;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import imitori.entity.neo4j.rel.SourceOfRelNeoEntity;

@NodeEntity(label = "Dicionary")
public class DicNeoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    /* dictype: To detect which type of this dic
        NEO_JAVI_DIC: JA-VI dict
        NEO_JAEN_DIC: JA-EN dict
        NEO_ENVI_DIC: EN-VI dict
        NEO_ENJA_DIC: EN-JA dict
        NEO_VIJA_DIC: VI-JA dict
        NEO_VIEN_DIC; VI-EN dict
    */
    public Integer dictype;

    /* The id of Mongo Collection */
    public Integer mongoId;

    @Relationship(type = "SOURCE_OF", direction = Relationship.OUTGOING)
    private ArrayList<SourceOfRelNeoEntity> sourceOf = new ArrayList<>();

}