package models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "KID_ACCOUNT", schema = "s225128")
public class KidAccount {

    @Id
    private long account_id;

    @ManyToOne
    @JoinColumn(name="KID_ID", referencedColumnName = "KID_id", insertable = false)
    private Kid kid;

    @ManyToOne
    @JoinColumn(name="GROUP_ID", referencedColumnName = "GROUP_id", insertable = false)
    private Group group;

    @Temporal(TemporalType.DATE)
    private Date date_of_creating;

    @Temporal(TemporalType.DATE)
    private Date date_of_leaving;

    @Override
    public String toString() {
        return  account_id + " | " + kid.toString() + " | " + group.getName() + " | " + new SimpleDateFormat("dd-MM-yyyy").format(date_of_creating) + "-"+ date_of_leaving;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getDate_of_creating() {
        return date_of_creating;
    }

    public void setDate_of_creating(Date date_of_creating) {
        this.date_of_creating = date_of_creating;
    }

    public Date getDate_of_leaving() {
        return date_of_leaving;
    }

    public void setDate_of_leaving(Date date_of_leaving) {
        this.date_of_leaving = date_of_leaving;
    }
}
