package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by dennisgelfart on 01.09.16.
 */
public class TableMetal {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty criteria;
    private final SimpleStringProperty metal;


    public TableMetal(int id, String criteria, String metal) {

        this.id = new SimpleIntegerProperty(id);
        this.criteria = new SimpleStringProperty(criteria);
        this.metal = new SimpleStringProperty(metal);}

    //getters and setters
    public Integer getROWID() {
        return id.get();
    }

    public void setROWID(Integer v) {
        id.set(v);

    }

    public String getCRITERIA() {
        return criteria.get();
    }

    public void setCRITERIA(String v) {
        criteria.set(v);

    }

    public String getMETAL() {
        return metal.get();
    }

    public void setMETAL(String v) {
        metal.set(v);
    }

}
