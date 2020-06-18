package strategyBanco;

import java.math.BigDecimal;

public interface Operacao {

	BigDecimal executar(BigDecimal valor, BigDecimal saldo);
}
