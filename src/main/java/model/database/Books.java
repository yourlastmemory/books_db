package model.database;

import javax.persistence.*;

@Entity
public class Books implements Savable{
    private Short bId;
    private String bName;
    private String bGenre;
    private Short bAuthor;
    private Double bPrice;
    private Short bPublisher;
    private Authors authorsByBAuthor;
    private Publishers publishersByBPublisher;
    private Orders ordersByBId;

    @Id
    @Column(name = "B_ID", nullable = false)
    public Short getbId() {
        return bId;
    }

    public void setbId(Short bId) {
        this.bId = bId;
    }

    @Basic
    @Column(name = "B_NAME", nullable = false, length = 125)
    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    @Basic
    @Column(name = "B_GENRE", nullable = false, length = 40)
    public String getbGenre() {
        return bGenre;
    }

    public void setbGenre(String bGenre) {
        this.bGenre = bGenre;
    }

    @Basic
    @Column(name = "B_AUTHOR", nullable = false)
    public Short getbAuthor() {
        return bAuthor;
    }

    public void setbAuthor(Short bAuthor) {
        this.bAuthor = bAuthor;
    }

    @Basic
    @Column(name = "B_PRICE", nullable = false, precision = 0)
    public Double getbPrice() {
        return bPrice;
    }

    public void setbPrice(Double bPrice) {
        this.bPrice = bPrice;
    }

    @Basic
    @Column(name = "B_PUBLISHER", nullable = false)
    public Short getbPublisher() {
        return bPublisher;
    }

    public void setbPublisher(Short bPublisher) {
        this.bPublisher = bPublisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Books books = (Books) o;

        if (bId != null ? !bId.equals(books.bId) : books.bId != null) return false;
        if (bName != null ? !bName.equals(books.bName) : books.bName != null) return false;
        if (bGenre != null ? !bGenre.equals(books.bGenre) : books.bGenre != null) return false;
        if (bAuthor != null ? !bAuthor.equals(books.bAuthor) : books.bAuthor != null) return false;
        if (bPrice != null ? !bPrice.equals(books.bPrice) : books.bPrice != null) return false;
        if (bPublisher != null ? !bPublisher.equals(books.bPublisher) : books.bPublisher != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bId != null ? bId.hashCode() : 0;
        result = 31 * result + (bName != null ? bName.hashCode() : 0);
        result = 31 * result + (bGenre != null ? bGenre.hashCode() : 0);
        result = 31 * result + (bAuthor != null ? bAuthor.hashCode() : 0);
        result = 31 * result + (bPrice != null ? bPrice.hashCode() : 0);
        result = 31 * result + (bPublisher != null ? bPublisher.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "B_AUTHOR", referencedColumnName = "AUTH_ID", nullable = false)
    public Authors getAuthorsByBAuthor() {
        return authorsByBAuthor;
    }

    public void setAuthorsByBAuthor(Authors authorsByBAuthor) {
        this.authorsByBAuthor = authorsByBAuthor;
    }

    @ManyToOne
    @JoinColumn(name = "B_PUBLISHER", referencedColumnName = "PUB_ID", nullable = false)
    public Publishers getPublishersByBPublisher() {
        return publishersByBPublisher;
    }

    public void setPublishersByBPublisher(Publishers publishersByBPublisher) {
        this.publishersByBPublisher = publishersByBPublisher;
    }

    @OneToOne(mappedBy = "booksByOBook")
    public Orders getOrdersByBId() {
        return ordersByBId;
    }

    public void setOrdersByBId(Orders ordersByBId) {
        this.ordersByBId = ordersByBId;
    }
}
