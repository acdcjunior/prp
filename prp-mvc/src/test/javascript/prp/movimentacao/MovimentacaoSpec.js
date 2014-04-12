define(['prp/movimentacao/Movimentacao'], function() {

	describe("prp.movimentacao.Movimentacao", function() {
		
		var jsonMovimentacao = 
		{ 
		    id: 5976,
		    data: "2012-11-22",
		    numeroDocumento: "900001",
		    descricao1: "JUROS",
		    descricao2: "hah",
		    descricao3: "yo",
		    valor: -14.01,
		    origem: {
		        id: 1,
		        nome: "Conta-Corrente BANCO",
		        alias: "contacorrente"
		    },
		    saldo: -362.80,
		    ordemSaldo: 181,
		    realiza: {
		        id: 281,
		        data: "2011-01-31",
		        descricao: "TAXAS BANCARIAS 2011-01",
		        valor: 20.00,
		        categoria: {
		            id: 7,
		            nome: "Despesas:Taxas Bancárias",
		            revisado: ""
		        },
		        realizada: false
		    }
		};
		var movimentacao = 'movimentacao nao carregada';
		
		beforeEach(function() {
			movimentacao = new prp.movimentacao.Movimentacao(jsonMovimentacao);
		});
		
		it("construtor deve ler json e setar propriedades corretamente", function() {
			// given
			// json acima
			// when
			// movimentacao construida acima
			// then
			expect(movimentacao.id).toEqual(5976);
			expect(movimentacao.data).toEqual("2012-11-22");
			expect(movimentacao.numeroDocumento).toEqual("900001");
			expect(movimentacao.descricao1).toEqual("JUROS");
			expect(movimentacao.descricao2).toEqual("hah");
			expect(movimentacao.descricao3).toEqual("yo");
			expect(movimentacao.valor).toEqual(-14.01);
			expect(movimentacao.saldo).toEqual(-362.80);
			expect(movimentacao.ordemSaldo).toEqual(181);
			
//			expect(movimentacao.origem).toEqual();
//			expect(movimentacao.realiza).toEqual();
		});
		
		it("getMes() deve trazer o mes", function() {
			// given
			// json acima
			// when
			// movimentacao construida acima
			// then
			expect(movimentacao.getMes()).toEqual(11);
		});
		
		it("getAno() deve trazer o ano", function() {
			// given
			// json acima
			// when
			// movimentacao construida acima
			// then
			expect(movimentacao.getAno()).toEqual(2012);
		});
		
	});
});