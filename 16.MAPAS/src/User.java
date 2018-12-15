import java.util.ArrayList;
import java.util.Map;

public class User {

	Integer id;
	String nick;
	static Map<Integer, User> mapaNombres = new java.util.HashMap<>();

	private User(Integer id, String nick) {
		this.id = id;
		this.nick = nick;
	}

	public static void main(String[] args) {
		User u1 = new User(1, "Unai");
		User u2 = new User(2, "Banana");
		User u3 = new User(3, "Willy");

		ArrayList<User> listaUsers = new ArrayList<>();

		listaUsers.add(u1);
		listaUsers.add(u2);
		listaUsers.add(u3);

		for (User u : listaUsers) {
			if (mapaNombres.containsKey(u.id)) {
				System.err.println("indice ocupado");
			} else {
				mapaNombres.put(u.id, u);
			}
			System.out.println(mapaNombres.values());
		}

	}

}
