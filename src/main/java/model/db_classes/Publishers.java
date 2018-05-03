package model.db_classes;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Publishers {
    private Short pubId;
    private String pubName;
    private String pubCountry;
    private String pubAddress;

    @Id
    @Column(name = "PUB_ID", nullable = false)
    public Short getPubId() {
        return pubId;
    }

    public void setPubId(Short pubId) {
        this.pubId = pubId;
    }

    @Basic
    @Column(name = "PUB_NAME", nullable = false, length = 50)
    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    @Basic
    @Column(name = "PUB_COUNTRY", nullable = true, length = 70)
    public String getPubCountry() {
        return pubCountry;
    }

    public void setPubCountry(String pubCountry) {
        this.pubCountry = pubCountry;
    }

    @Basic
    @Column(name = "PUB_ADDRESS", nullable = false, length = 120)
    public String getPubAddress() {
        return pubAddress;
    }

    public void setPubAddress(String pubAddress) {
        this.pubAddress = pubAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publishers that = (Publishers) o;

        if (pubId != null ? !pubId.equals(that.pubId) : that.pubId != null) return false;
        if (pubName != null ? !pubName.equals(that.pubName) : that.pubName != null) return false;
        if (pubCountry != null ? !pubCountry.equals(that.pubCountry) : that.pubCountry != null) return false;
        if (pubAddress != null ? !pubAddress.equals(that.pubAddress) : that.pubAddress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pubId != null ? pubId.hashCode() : 0;
        result = 31 * result + (pubName != null ? pubName.hashCode() : 0);
        result = 31 * result + (pubCountry != null ? pubCountry.hashCode() : 0);
        result = 31 * result + (pubAddress != null ? pubAddress.hashCode() : 0);
        return result;
    }
}
