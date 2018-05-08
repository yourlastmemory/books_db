package controller;

import java.awt.print.Book;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.database.*;
import org.hibernate.query.Query;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Books> lvBooks;

    @FXML
    private TextField tfBookName;

    @FXML
    private TextField tfGenre;

    @FXML
    private TextField tfPrice;

    @FXML
    private ChoiceBox<Authors> cbAuthor;

    @FXML
    private ChoiceBox<Publishers> cbPublisher;

    @FXML
    private ListView<Authors> lvAuthors;

    @FXML
    private TextField tfAuthorLastname;

    @FXML
    private TextField tfAuthorFirstname;

    @FXML
    private TextField tfAuthorCountry;

    @FXML
    private ChoiceBox<String> cbGender;

    @FXML
    private DatePicker dpAuthorBirth;

    @FXML
    private ListView<Publishers> lvPublishers;

    @FXML
    private TextField tfPublisherName;

    @FXML
    private TextField tfPublisherCountry;

    @FXML
    private TextField tfPublisherAddress;

    @FXML
    private ListView<Orders> lvOrders;

    @FXML
    private TextField tfCustomerName;

    @FXML
    private TextField tfCustomerPhone;

    @FXML
    private ChoiceBox<Books> cbBook;

    @FXML
    void btnAddAuthor() {
        if (!authorsFieldsEmpty()) {
            HibernateUtil.saveObject(new Authors(tfAuthorFirstname.getText(), tfAuthorLastname.getText(),
                    cbGender.getValue(), tfAuthorCountry.getText(), dpAuthorBirth.getEditor().getText()));
            refreshAuthors();
        }
    }

    @FXML
    void btnAddBook() {
        if (!booksFieldsEmpty()){
            HibernateUtil.saveObject(new Books(tfBookName.getText(),tfGenre.getText(),
                    Double.valueOf(tfPrice.getText()),cbAuthor.getValue(),cbPublisher.getValue()));
            refreshBooks();
        }

    }

    @FXML
    void btnAddOrder() {
        if (!ordersFieldsEmpty()){
            HibernateUtil.saveObject(new Orders(tfCustomerPhone.getText(),tfCustomerName.getText(),
                    new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()),cbBook.getValue()));
            refreshOrders();
        }

    }

    @FXML
    void btnAddPublisher() {
        if (!publishersFieldsEmpty()){
            HibernateUtil.saveObject(new Publishers(tfPublisherName.getText(),
                    tfPublisherCountry.getText(),tfPublisherAddress.getText()));
            refreshPublishers();
        }

    }

    @FXML
    void btnRemoveAuthor() {
        Authors removing=lvAuthors.getSelectionModel().getSelectedItem();
        Query q=HibernateUtil.getSession().createQuery("from Books where authorsByBAuthor=:auth");
        q.setParameter("auth",removing);
        removingBooksList(q);
        HibernateUtil.deleteObject(removing);
        configureControls();
    }

    @FXML
    void btnRemoveBook() {

        Books removing=lvBooks.getSelectionModel().getSelectedItem();
        Query q=HibernateUtil.getSession().createQuery("from Books where bId=:pub");
        q.setParameter("pub",removing.getbId());
        removingBooksList(q);
        configureControls();

    }

    @FXML
    void btnRemoveOrder() {
        HibernateUtil.deleteObject(lvOrders.getSelectionModel().getSelectedItem());
        configureControls();
    }

    @FXML
    void btnRemovePublisher() {
        Publishers removing=lvPublishers.getSelectionModel().getSelectedItem();
        Query q=HibernateUtil.getSession().createQuery("from Books where publishersByBPublisher=:pub");
        q.setParameter("pub",removing);
        removingBooksList(q);
        HibernateUtil.deleteObject(removing);
        configureControls();

    }

    private void removingBooksList(Query q) {
        for (Object book:q.list()
                ) {
            Query booksRemove=HibernateUtil.getSession().createQuery(
                    "from Orders where booksByOBook=:b");
            booksRemove.setParameter("b",(Books)book);
            for (Object order:booksRemove.list()
                    ) {
                HibernateUtil.deleteObject((Orders)order);
            }
            HibernateUtil.deleteObject((Books)book);
        }
    }

    @FXML
    void initialize() {
        assert lvBooks != null : "fx:id=\"lvBooks\" was not injected: check your FXML file 'Books.fxml'.";
        assert tfBookName != null : "fx:id=\"tfBookName\" was not injected: check your FXML file 'Books.fxml'.";
        assert tfGenre != null : "fx:id=\"tfGenre\" was not injected: check your FXML file 'Books.fxml'.";
        assert tfPrice != null : "fx:id=\"tfPrice\" was not injected: check your FXML file 'Books.fxml'.";
        assert cbAuthor != null : "fx:id=\"cbAuthor\" was not injected: check your FXML file 'Books.fxml'.";
        assert cbPublisher != null : "fx:id=\"cbPublisher\" was not injected: check your FXML file 'Books.fxml'.";
        assert lvAuthors != null : "fx:id=\"lvAuthors\" was not injected: check your FXML file 'Books.fxml'.";
        assert tfAuthorLastname != null : "fx:id=\"tfAuthorLastname\" was not injected: check your FXML file 'Books.fxml'.";
        assert tfAuthorFirstname != null : "fx:id=\"tfAuthorFirstname\" was not injected: check your FXML file 'Books.fxml'.";
        assert tfAuthorCountry != null : "fx:id=\"tfAuthorCountry\" was not injected: check your FXML file 'Books.fxml'.";
        assert cbGender != null : "fx:id=\"cbGender\" was not injected: check your FXML file 'Books.fxml'.";
        assert dpAuthorBirth != null : "fx:id=\"dpAuthorBirth\" was not injected: check your FXML file 'Books.fxml'.";
        assert lvPublishers != null : "fx:id=\"lvPublishers\" was not injected: check your FXML file 'Books.fxml'.";
        assert tfPublisherName != null : "fx:id=\"tfPublisherName\" was not injected: check your FXML file 'Books.fxml'.";
        assert tfPublisherCountry != null : "fx:id=\"tfPublisherCountry\" was not injected: check your FXML file 'Books.fxml'.";
        assert tfPublisherAddress != null : "fx:id=\"tfPublisherAddress\" was not injected: check your FXML file 'Books.fxml'.";
        assert lvOrders != null : "fx:id=\"lvOrders\" was not injected: check your FXML file 'Books.fxml'.";
        assert tfCustomerName != null : "fx:id=\"tfCustomerName\" was not injected: check your FXML file 'Books.fxml'.";
        assert tfCustomerPhone != null : "fx:id=\"tfCustomerPhone\" was not injected: check your FXML file 'Books.fxml'.";
        assert cbBook != null : "fx:id=\"cbBook\" was not injected: check your FXML file 'Books.fxml'.";
        configureControls();
    }

    private void configureControls() {
        refreshPublishers();
        refreshBooks();
        refreshAuthors();
        refreshOrders();
        cbGender.getItems().addAll("Male","Female");
    }

    private boolean authorsFieldsEmpty(){
        return tfAuthorFirstname.getText().length()==0
                ||tfAuthorLastname.getText().length()==0
                ||tfAuthorCountry.getText().length()==0
                ||cbGender.getValue().length()==0
                ||dpAuthorBirth.getEditor().getText().length()==0;
    }

    private boolean booksFieldsEmpty(){
        return tfBookName.getText().length()==0
                ||tfGenre.getText().length()==0
                ||tfPrice.getText().length()==0
                ||cbAuthor.getValue()==null
                ||cbPublisher.getValue()==null;
    }

    private boolean publishersFieldsEmpty(){
        return tfPublisherName.getText().length()==0
                ||tfPublisherCountry.getText().length()==0
                ||tfPublisherAddress.getText().length()==0;
    }

    private boolean ordersFieldsEmpty(){
        return tfCustomerName.getText().length()==0
                ||tfCustomerPhone.getText().length()==0
                ||cbBook.getValue()==null;
    }

    private void refreshBooks(){
        lvBooks.getItems().clear();
        cbBook.getItems().clear();
        for (Object book:(HibernateUtil.getSession().createQuery("from Books order by bName").list())
             ) {
            lvBooks.getItems().addAll((Books)book);
            cbBook.getItems().addAll((Books)book);
        }
    }

    private void refreshAuthors(){
        lvAuthors.getItems().clear();
        cbAuthor.getItems().clear();
        for (Object author:(HibernateUtil.getSession().createQuery(
                "from Authors order by authLname,authFname").list())
             ) {
            lvAuthors.getItems().addAll((Authors) author);
            cbAuthor.getItems().addAll((Authors) author);
        }
    }

    private void refreshPublishers(){
        lvPublishers.getItems().clear();
        cbPublisher.getItems().clear();
        for (Object publisher:(HibernateUtil.getSession().createQuery(
                "from Publishers").list())
             ) {
            lvPublishers.getItems().addAll((Publishers) publisher);
            cbPublisher.getItems().addAll((Publishers) publisher);
        }
    }

    private void refreshOrders(){
        lvOrders.getItems().clear();
        for (Object order:(HibernateUtil.getSession().createQuery(
                "from Orders  order by oDate").list())
             ) {
            lvOrders.getItems().addAll((Orders) order);
        }
    }
}
