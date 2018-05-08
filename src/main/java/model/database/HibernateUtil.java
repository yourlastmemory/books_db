package model.database;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static Configuration configuration=new Configuration();
    private static StandardServiceRegistry registry=new StandardServiceRegistryBuilder()
            .configure().build();

    private static SessionFactory sessionFactory;
    private static Session session;

    protected static SessionFactory buildSessionFactory() {
        // A SessionFactory is set up once for an application!
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            new MetadataSources(registry);
        }
        catch (Exception e) {
            (new Alert(Alert.AlertType.ERROR,"Неверный логин или пароль",ButtonType.OK))
                    .showAndWait();
            StandardServiceRegistryBuilder.destroy( registry );

            e.printStackTrace();
        }
        return sessionFactory;
    }

    static {

        configuration.setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");
        configuration.setProperty("hibernate.connection.url", "jdbc:sqlite:Library.sqlite");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
        configuration.addPackage("model.database");
        configuration.addPackage("com.concretepage.persistence");
        configuration.addAnnotatedClass(Books.class);
        configuration.addAnnotatedClass(Orders.class);
        configuration.addAnnotatedClass(Publishers.class);
        configuration.addAnnotatedClass(Authors.class);new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory();
    }

    private static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static Session getSession(){
        if (session==null)
            session=sessionFactory.openSession();
        return session;
    }

    public static void shutdown() {

        getSessionFactory().close();
    }

    public static void saveObject(Savable object){
        getSession().clear();
        getSession().getTransaction().begin();
        getSession().saveOrUpdate(object);
        getSession().getTransaction().commit();
    }

    public static void deleteObject(Savable object){
        getSession().clear();
        getSession().getTransaction().begin();
        getSession().delete(object);
        getSession().getTransaction().commit();
    }

}