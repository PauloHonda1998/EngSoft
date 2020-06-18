package strategyBanco;

import java.math.BigDecimal;

public class Pagamento implements Operacao {

	@Override
	public BigDecimal executar(BigDecimal valor, BigDecimal saldo) {
		saldo = saldo.subtract(valor);
		return saldo;
	}

}
