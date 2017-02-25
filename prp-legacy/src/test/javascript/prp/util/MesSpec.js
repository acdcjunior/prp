define(['prp/util/Mes'], function() {
	
	describe("prp.util.Mes", function() {
		
		it("construtor deve aceitar strings", function() {
			// given
			var argumento = "2013-11-07";
			// when
			var mes = new prp.util.Mes(argumento);
			// then
			expect(mes.getMes()).toEqual(11);
		});
		
		it("construtor deve aceitar Date", function() {
			// given
			var argumento = new Date(2013, 0);
			// when
			var mes = new prp.util.Mes(argumento);
			// then
			expect(mes.getMes()).toEqual(1);
		});
		
		it("construtor deve aceitar ano/mes", function() {
			// given
			var argAno = 2099;
			var argMes = 4;
			// when
			var mes = new prp.util.Mes(argAno, argMes);
			// then
			expect(mes.getMes()).toEqual(4);
		});
		
		it("getProximoMes() deve trazer o mes seguinte", function() {
			var mes = new prp.util.Mes(2013, 1);
			
			expect(mes.getProximoMes()).toEqual(new prp.util.Mes(2013, 2));
		});
		it("getProximoMes() deve virar o ano se o mes atual for dezembro", function() {
			var mes = new prp.util.Mes(2013, 12);
			
			expect(mes.getProximoMes()).toEqual(new prp.util.Mes(2014, 1));
		});
		it("getMesAnterior() deve trazer o mes anterior", function() {
			var mes = new prp.util.Mes(2013, 5);
			
			expect(mes.getMesAnterior()).toEqual(new prp.util.Mes(2013, 4));
		});
		it("getMesAnterior() deve diminuir o ano se o mes atual for janeiro", function() {
			var mes = new prp.util.Mes(2013, 1);
			
			expect(mes.getMesAnterior()).toEqual(new prp.util.Mes(2012, 12));
		});
		it("getMesPorExtenso() deve trazer o mes por extenso", function() {
			var mes = new prp.util.Mes(2013, 10);
			
			expect(mes.getMesPorExtenso()).toEqual("Outubro");
		});
		it("toString() deve trazer ano/mes", function() {
			var mes = new prp.util.Mes(2013, 8);
			
			expect(mes + "").toEqual("2013/Agosto");
		});
		
	});
	
});