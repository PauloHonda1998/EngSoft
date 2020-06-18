package strategyBanco;

import java.math.BigDecimal;

public class CaixaEletronico {
	
	private Operacao operacao;
	
	public CaixaEletronico(Saque saque) {
		
	}
	
	public CaixaEletronico(Deposito deposito) {
		
	}

	public CaixaEletronico(Pagamento pagamento) {
		
	}
	
	public BigDecimal executar(BigDecimal valor, BigDecimal saldo) {
		return this.operacao.executar(valor, saldo);
	}
	
}
