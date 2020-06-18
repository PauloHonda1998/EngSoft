package observer.cofre;

import java.util.Date;

public class CofreListenerImpl implements CofreListenerAberto, CofreListenerFechado, CofreListenerSenha {

	@Override
	public void cofreFoiAberto() {
		System.out.println("Cofre aberto. Hora: " + new Date().toLocaleString());
	}

	@Override
	public void cofreFoiFechado() {
		System.out.println("Cofre fechado. Hora: " + new Date().toLocaleString());
	}

	@Override
	public void senhaIncorreta() {
		System.out.println("Senha incorreta, tente novamente!");
	}

}
