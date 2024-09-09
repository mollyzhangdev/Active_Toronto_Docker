package dev.mollyzhang.activeto.data.entity.main;

import dev.mollyzhang.activeto.data.entity.BaseEntity;
import javax.persistence.*;

import dev.mollyzhang.activeto.data.entity.translation.Translation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Table(name = "facility")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Facility extends BaseEntity {

    @Column
    private String phone;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ADDRESS_ID")
    private Address address;

    @OneToOne
    @JoinColumn(name = "TITLE_TRANSLATION_ID")
    private Translation titleTranslation;

    @Column
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CITY_ID")
    private City city;

    @OneToMany(mappedBy = "facility")
    private List<FacilityActivity> facilityActivities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Facility facility = (Facility) o;
        return Objects.equals(phone, facility.phone) && Objects.equals(address, facility.address) && Objects.equals(titleTranslation, facility.titleTranslation) && Objects.equals(url, facility.url) && Objects.equals(city, facility.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phone, titleTranslation, url, address);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    

    public Translation getTitleTranslation() {
        return titleTranslation;
    }

    public void setTitleTranslation(Translation titleTranslation) {
        this.titleTranslation = titleTranslation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    

    @Override
    public String toString() {
        return "Facility{" +
                "phone='" + phone + '\'' +
                ", titleTranslation=" + titleTranslation +
                ", url=" + url +
                ", address=" + address +
                '}';
    }

    public List<FacilityActivity> getFacilityActivities() {
        return facilityActivities;
    }

    public void setFacilityActivities(List<FacilityActivity> facilityActivities) {
        this.facilityActivities = facilityActivities;
    }
}
