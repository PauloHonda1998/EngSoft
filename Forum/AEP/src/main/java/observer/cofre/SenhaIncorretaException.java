package observer.cofre;

import java.util.ArrayList;
import java.util.List;

public class SenhaIncorretaException extends Exception {

	public SenhaIncorretaException(String error){
		super(error);
	}
}
