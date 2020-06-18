package strategyBanco;

import java.math.BigDecimal;

public class AppCaixaEletronico {

	public static void main(String[] args) {
		BigDecimal saldo = BigDecimal.valueOf(1000);
		BigDecimal valorSaque = BigDecimal.valueOf(300);
		BigDecimal valorDeposito = BigDecimal.valueOf(100);
		BigDecimal valorPagamento = BigDecimal.valueOf(50);
		
		CaixaEletronico sacar = new CaixaEletronico(new Saque());
		CaixaEletronico deposito = new CaixaEletronico(new Deposito());
		CaixaEletronico pagamento = new CaixaEletronico(new Pagamento());
		
		System.out.println(sacar.executar(valorSaque, saldo));	
		System.out.println(deposito.executar(valorDeposito, saldo));
		System.out.println(pagamento.executar(valorPagamento, saldo));
	}
}
