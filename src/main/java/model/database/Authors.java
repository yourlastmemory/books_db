package model.database;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Authors implements Savable{
    private Short authId;
    private String authFname;
    private String authLname;
    private String authGender;
    private String authCountry;
    private String authBirth;
    private Collection<Books> booksByAuthId;

    public Authors(String authFname, String authLname, String authGender, String authCountry, String authBirth) {
        this.authFname = authFname;
        this.authLname = authLname;
        this.authGender = authGender;
        this.authCountry = authCountry;
        this.authBirth = authBirth;
    }

    public Authors() {
    }

    @Id
    @Column(name = "AUTH_ID", nullable = false)
    public Short getAuthId() {
        return authId;
    }

    public void setAuthId(Short authId) {
        this.authId = authId;
    }

    @Basic
    @Column(name = "AUTH_FNAME", nullable = false, length = 16)
    public String getAuthFname() {
        return authFname;
    }

    public void setAuthFname(String authFname) {
        this.authFname = authFname;
    }

    @Basic
    @Column(name = "AUTH_LNAME", nullable = false, length = 13)
    public String getAuthLname() {
        return authLname;
    }

    public void setAuthLname(String authLname) {
        this.authLname = authLname;
    }

    @Basic
    @Column(name = "AUTH_GENDER", nullable = false, length = 1)
    public String getAuthGender() {
        return authGender;
    }

    public void setAuthGender(String authGender) {
        this.authGender = authGender;
    }

    @Basic
    @Column(name = "AUTH_COUNTRY", nullable = true, length = 50)
    public String getAuthCountry() {
        return authCountry;
    }

    public void setAuthCountry(String authCountry) {
        this.authCountry = authCountry;
    }

    @Basic
    @Column(name = "AUTH_BIRTH", nullable = true, length = 10)
    public String getAuthBirth() {
        return authBirth;
    }

    public void setAuthBirth(String authBirth) {
        this.authBirth = authBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authors authors = (Authors) o;

        if (authId != null ? !authId.equals(authors.authId) : authors.authId != null) return false;
        if (authFname != null ? !authFname.equals(authors.authFname) : authors.authFname != null) return false;
        if (authLname != null ? !authLname.equals(authors.authLname) : authors.authLname != null) return false;
        if (authGender != null ? !authGender.equals(authors.authGender) : authors.authGender != null) return false;
        if (authCountry != null ? !authCountry.equals(authors.authCountry) : authors.authCountry != null) return false;
        if (authBirth != null ? !authBirth.equals(authors.authBirth) : authors.authBirth != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authId != null ? authId.hashCode() : 0;
        result = 31 * result + (authFname != null ? authFname.hashCode() : 0);
        result = 31 * result + (authLname != null ? authLname.hashCode() : 0);
        result = 31 * result + (authGender != null ? authGender.hashCode() : 0);
        result = 31 * result + (authCountry != null ? authCountry.hashCode() : 0);
        result = 31 * result + (authBirth != null ? authBirth.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "authorsByBAuthor")
    public Collection<Books> getBooksByAuthId() {
        return booksByAuthId;
    }

    public void setBooksByAuthId(Collection<Books> booksByAuthId) {
        this.booksByAuthId = booksByAuthId;
    }
}
