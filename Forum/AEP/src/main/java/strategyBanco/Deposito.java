package strategyBanco;

import java.math.BigDecimal;

public class Deposito implements Operacao {

	@Override
	public BigDecimal executar(BigDecimal valor, BigDecimal saldo) {
		saldo = saldo.add(valor);		
		return saldo;
	}
	
}
