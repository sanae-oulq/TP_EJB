import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.IDaoRemote;
import entities.Hotel;

public class TestHotel {
	public static IDaoRemote<Hotel> lookUpEmployeRemote() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);

		return (IDaoRemote<Hotel>) context.lookup("ejb:ISICEJBEAR/ISICEJBServer/Laili!dao.IDaoRemote");

	}
	public static void main(String[] args) {
		
		try {
			IDaoRemote<Hotel> dao = lookUpEmployeRemote();
			dao.create(new Hotel("EL JADIDA"));
			dao.create(new Hotel("Marrakech"));
			dao.create(new Hotel("Casablanca"));
			
			for(Hotel v : dao.findAll()) {
				System.out.println(v.getNom());
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("An error occurred: " + e.getMessage());
	        e.printStackTrace();
		}
		

	}
}
