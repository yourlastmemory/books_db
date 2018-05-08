package model.database;

import javax.persistence.*;

@Entity
public class Orders implements Savable{
    private Short oId;
    private Short oBook;
    private String oCustomerPhone;
    private String oCustomerName;
    private String oDate;
    private Books booksByOBook;

    @Basic
    @Column(name = "O_ID", nullable = false)
    public Short getoId() {
        return oId;
    }

    public void setoId(Short oId) {
        this.oId = oId;
    }

    @Id
    @Column(name = "O_BOOK", nullable = false)
    public Short getoBook() {
        return oBook;
    }

    public void setoBook(Short oBook) {
        this.oBook = oBook;
    }

    @Basic
    @Column(name = "O_CUSTOMER_PHONE", nullable = false, length = 18)
    public String getoCustomerPhone() {
        return oCustomerPhone;
    }

    public void setoCustomerPhone(String oCustomerPhone) {
        this.oCustomerPhone = oCustomerPhone;
    }

    @Basic
    @Column(name = "O_CUSTOMER_NAME", nullable = false, length = 70)
    public String getoCustomerName() {
        return oCustomerName;
    }

    public void setoCustomerName(String oCustomerName) {
        this.oCustomerName = oCustomerName;
    }

    @Basic
    @Column(name = "O_DATE", nullable = true, length = 10)
    public String getoDate() {
        return oDate;
    }

    public void setoDate(String oDate) {
        this.oDate = oDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (oId != null ? !oId.equals(orders.oId) : orders.oId != null) return false;
        if (oBook != null ? !oBook.equals(orders.oBook) : orders.oBook != null) return false;
        if (oCustomerPhone != null ? !oCustomerPhone.equals(orders.oCustomerPhone) : orders.oCustomerPhone != null)
            return false;
        if (oCustomerName != null ? !oCustomerName.equals(orders.oCustomerName) : orders.oCustomerName != null)
            return false;
        if (oDate != null ? !oDate.equals(orders.oDate) : orders.oDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = oId != null ? oId.hashCode() : 0;
        result = 31 * result + (oBook != null ? oBook.hashCode() : 0);
        result = 31 * result + (oCustomerPhone != null ? oCustomerPhone.hashCode() : 0);
        result = 31 * result + (oCustomerName != null ? oCustomerName.hashCode() : 0);
        result = 31 * result + (oDate != null ? oDate.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "O_BOOK", referencedColumnName = "B_ID", nullable = false)
    public Books getBooksByOBook() {
        return booksByOBook;
    }

    public void setBooksByOBook(Books booksByOBook) {
        this.booksByOBook = booksByOBook;
    }
}
