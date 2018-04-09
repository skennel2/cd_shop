package org.almansa.app.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@AttributeOverride(column = @Column(name = "artist_name"), name = "name") // 슈퍼클래스의 name 속성을 artist_name으로 재정의
public abstract class PersonBase extends NamedEntityBase {

    @Temporal(TemporalType.DATE)
    @Column(name = "born_date")
    private Date bornDate;

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public void setBornDate(int year, int month, int date) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month + 1, date);

        this.bornDate = cal.getTime();
    }

    @Override
    public String toString() {
        return "PersonBase [bornDate=" + bornDate + "]";
    }
}