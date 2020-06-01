package com.sszz.CBT.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEST_HOECHA_TB")
public class HoechaVO {

    @Id
    String hoechaId;
    String name;
    String doDate;

    public HoechaVO() {
    }

    public HoechaVO(String hoechaId, String name, String doDate) {
        this.hoechaId = hoechaId;
        this.name = name;
        this.doDate = doDate;
    }

    public String getHoechaId() {
        return hoechaId;
    }

    public void setHoechaId(String hoechaId) {
        this.hoechaId = hoechaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoDate() {
        return doDate;
    }

    public void setDoDate(String doDate) {
        this.doDate = doDate;
    }

}
