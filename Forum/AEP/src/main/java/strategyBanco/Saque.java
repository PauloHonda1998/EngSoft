package strategyBanco;

import java.math.BigDecimal;

public class Saque implements Operacao {

	@Override
	public BigDecimal executar(BigDecimal valor, BigDecimal saldo) {
		saldo = saldo.subtract(valor);
		return valor;
	}

}
