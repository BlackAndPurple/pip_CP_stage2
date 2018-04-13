package models;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "STAFF_GROUP", schema = "s225128")
public class StaffGroup {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "IdSeq6")
    @SequenceGenerator(name="IdSeq6",sequenceName="staff_group_ids", allocationSize=1)
    private long id;

    @Temporal(TemporalType.DATE)
    private Date date_of_beginning;

    @Temporal(TemporalType.DATE)
    private Date date_of_end;

    @ManyToOne
    @JoinColumn(name="STAFF_ID", referencedColumnName = "STAFF_id", insertable = false)
    private Staff staff;

    @ManyToOne
    @JoinColumn(name="GROUP_ID", referencedColumnName = "GROUP_ID", insertable = false)
    private Group group;

    @Override
    public String toString() {
        return id + " | " + staff.getStaff_id() + " | " + group.getName() + " | " +
                date_of_beginning + " - " + date_of_end;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate_of_beginning() {
        return date_of_beginning;
    }

    public void setDate_of_beginning(Date date_of_beginning) {
        this.date_of_beginning = date_of_beginning;
    }

    public Date getDate_of_end() {
        return date_of_end;
    }

    public void setDate_of_end(Date date_of_end) {
        this.date_of_end = date_of_end;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
