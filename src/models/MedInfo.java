package models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "MED_INFO", schema = "s225128")
public class MedInfo {

    @Id
    private long med_id;

    @ManyToOne
    @JoinColumn(name="KID_ID", referencedColumnName = "KID_id", insertable = false)
    private Kid kid;

    @Temporal(TemporalType.DATE)
    private Date date_of_creating;

    private int height;
    private float weight;
    private String inoculations;

    @Column(name = "CURRENT_DESEASES")
    private String currentDeseases;

    @Override
    public String toString() {
        return med_id + " | " + getKid().getKid_id()+" | " + new SimpleDateFormat("dd-MM-yyyy").format(date_of_creating)+
                " | " + height + " | " + weight + " | " + inoculations+ " | " + currentDeseases;
    }

    public long getMed_id() {
        return med_id;
    }

    public void setMed_id(long med_id) {
        this.med_id = med_id;
    }

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }

    public Date getDate_of_creating() {
        return date_of_creating;
    }

    public void setDate_of_creating(Date date_of_creating) {
        this.date_of_creating = date_of_creating;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getInoculations() {
        return inoculations;
    }

    public void setInoculations(String inoculations) {
        this.inoculations = inoculations;
    }

    public String getCurrentDeseases() {
        return currentDeseases;
    }

    public void setCurrentDeseases(String currentDeseases) {
        this.currentDeseases = currentDeseases;
    }
}
