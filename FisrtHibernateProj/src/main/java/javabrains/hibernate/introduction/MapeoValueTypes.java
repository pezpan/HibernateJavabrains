package javabrains.hibernate.introduction;

import java.util.Date;

import org.hibernate.Session;

import javabrains.hibernate.introduction.entidades.User;
import javabrains.hibernate.introduction.utils.HibernateUtilities;
import javabrains.hibernate.introduction.values.Address;
import javabrains.hibernate.introduction.values.Coche;

/**
 * Probamos la insercion de usuarios en la tabla
 *
 */
public class MapeoValueTypes 
{
    public static void main( String[] args )
    {
        User usuario = new User();
        Address casa = new Address();
        Address oficina = new Address();
        
        casa.setCalle("Abadia 7");
        casa.setCiudad("Cuenca");
        casa.setCodigo_postal("26594");
        casa.setComunidad("Castilla");
        
        oficina.setCalle("Via Lusitana");
        oficina.setCiudad("Roma");
        oficina.setCodigo_postal("26626");
        oficina.setComunidad("Piamonte");
        
        usuario.setNombre("Adelmo");
        usuario.setHome_address(casa);
        usuario.setOffice_address(oficina);
        usuario.setDate(new Date());
        usuario.setDescription("Primer usuario embebido");
        usuario.setAge(25);
        
        // Generamos una coleccion y la añadimos al usuario
        Coche utilitario = new Coche();
        utilitario.setAños(8);
        utilitario.setColor("rojo");
        utilitario.setMarca("Ford");
        Coche deportivo = new Coche();
        deportivo.setAños(2);
        deportivo.setColor("negro");
        deportivo.setMarca("Ferrari");
        usuario.getListaCoches().add(utilitario);
        usuario.getListaCoches().add(deportivo);        
        
        Session session = HibernateUtilities.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(usuario);
        session.getTransaction().commit();
        session.close();
        
        // Vamos a comprobar los tipos de inicizializaciones de objetos que tiene hibernate, eager y lazy
        usuario = null;
        session = HibernateUtilities.getSessionFactory().openSession();
        // Obtenemos un usuario, indicando el identificador del usuario que queremos obtener. El 3 tiene coches
        usuario = (User)session.get(User.class, 1);
        // La opcion por defecto es inicializacion (fetch) lazy, lo que significa que cuando llamamos al metodo get
        // para obtener una instancia de un objeto, no llamamos a la base de datos, sino que hibernate genera un objeto
        // proxy que es una subclase del objeto que queremos obtener, que contiene las variables de primer nivel de dicho
        // objeto. Este objeto proxy está asociado a la sesion, y permite obtener una instancia del objeto sin obtener
        // toda la informacion del objeto de la base de datos, que sería muy costoso. Cuando intentamos acceder a las variables
        // del objeto mediante los metodos getter, por ejemplo, es cuando se llama a la base de datos para conseguir dichos datos,
        // pero no tenemos que conseguir todos.

        // Si mostramos el numero de coches del usuario, es entonces cuando hibernate llama a la base de datos para obtener
        // la lista de coches del usuario
        // Si antes de eso, cerramos la sesion, el objeto proxy es destruido, y se produce una excepcion LazyInitializacionException,
        // ya que intenta acceder al proxy, pero este no existe. 
        // Podemos decirle a hibernate que obtenga el campo de la base de datos sin usar el proxy, modficando el campo de
        // la clase User, indicando el ls lista de coches que use el tipo de fetch "eager". Así, la primera vez que acceda 
        // al objeto para generarlo, creará la lista de coches obteniendola de la base de datos.
        session.close();
        System.out.println(usuario.getListaCoches().size());
        
        
    }
}
