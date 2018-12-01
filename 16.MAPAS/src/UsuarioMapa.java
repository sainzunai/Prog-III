
public class UsuarioMapa {
	private String nomUsuario;
	private String passwd;
	
	public UsuarioMapa(String nombre, String passwd) {
		this.nomUsuario = nombre;
		this.passwd = passwd;
	}
	
	
	
	@Override
	public String toString() {
		return "UsuarioMapa [nomUsuario=" + nomUsuario + ", passwd=" + passwd + "]";
	}



	public static void main(String[] args) {
		UsuarioMapa u1 = new UsuarioMapa("Unai", "hooooola");
		System.out.println(u1.toString());
		
	}
}

