package observer.cofre;

import java.util.ArrayList;
import java.util.List;

public class Cofre {

	private boolean aberto;
	private int senha;
	private List<CofreListener> listeners = new ArrayList<>();
	
	public Cofre(int senha) {
		this.senha = senha;
		this.aberto = true;
	}
	
	public void abrir(int senha) throws SenhaIncorretaException {
		
		if(this.senha == senha) {
			this.aberto = true;
			this.listeners.stream()
            	.filter(cofreListener -> cofreListener instanceof CofreListenerAberto)
            	.map(cofreListener -> (CofreListenerAberto) cofreListener)
            	.forEach(CofreListenerAberto::cofreFoiAberto);
			
			return;
		}	
		
		this.listeners.stream()
        	.filter(cofreListener -> cofreListener instanceof CofreListenerSenha)
        	.map(cofreListener -> (CofreListenerSenha) cofreListener)
        	.forEach(listener -> listener.senhaIncorreta(senha));
		throw new SenhaIncorretaException("A senha " + senha + " é incorreta.");
				
	}
	
	public void fechar() {
		this.aberto = false;
		this.listeners.stream()
        	.filter(cofreListener -> cofreListener instanceof CofreListenerFechado)
        	.map(cofreListener -> (CofreListenerFechado) cofreListener)
        	.forEach(CofreListenerFechado::cofreFoiFechado);
	}
	
	public Boolean isAberto() {
		return this.aberto;
	}
	
	public void addListener(CofreListener listener) {
        this.listeners.add(listener);
    }
}
