package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by dennisgelfart on 16.08.16.
 */
public class TablePlayer {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty birth;
    private final SimpleStringProperty sport;
    private final SimpleStringProperty country;
    private final SimpleStringProperty metal;



    public TablePlayer(int id, String name, String birth, String sport, String country, String metal) {

        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.birth = new SimpleStringProperty(birth);
        this.sport = new SimpleStringProperty(sport);
        this.country = new SimpleStringProperty(country);
        this.metal = new SimpleStringProperty(metal);}

    //getters and setters
    public Integer getROWID() {
        return id.get();
    }

    public void setROWID(Integer v) {
        id.set(v);

    }
    public String getNAME() {
        return name.get();
    }

    public void setNAME(String v) {
        name.set(v);

    }
    public String getBIRTH() {
        return birth.get();
    }

    public void setBIRTH(String v) {
        birth.set(v);

    }

    public String getSPORT() {
        return sport.get();
    }

    public void setSPORT(String v) {
        sport.set(v);

    }

    public String getCOUNTRY() {
        return country.get();
    }

    public void setCOUNTRY(String v) {
        country.set(v);

    }

    public String getMETAL() {
        return metal.get();
    }

    public void setMETAL(String v) {
        metal.set(v);

    }


}
