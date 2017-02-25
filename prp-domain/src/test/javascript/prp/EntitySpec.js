define(['prp/Entity'], function() {
	
	beforeEach(function () {
		window.prp = window.prp || {};
		prp.Util = {};
		prp.Util.carregarPropriedadesDeJson = function () {};
		prp.Ajax = {};
		prp.Ajax.get = jasmine.createSpy("Ajax.get() spy");
		prp.Ajax.getAgora = jasmine.createSpy("Ajax.getAgora() spy");

		prp.Entity.URL = 'url/';
	});
	
	describe("Classe prp.Entity", function() {

		it("new prp.Entity(id) deve definir id", function() {
			// given
			var id = 123;
			// when
			var entity = new prp.Entity(id);
			// then
			expect(entity.id).toEqual(id);
		});
		
		it("new prp.Entity() deve definir id como zero", function() {
			// given
			// when
			var entity = new prp.Entity();
			// then
			expect(entity.id).toEqual(0);
		});
		
		it("new prp.Entity(id) deve definir id", function() {
			// given
			// when
			var entity = new prp.Entity(123);
			// then
			expect(entity.isPropriedadesCarregadas()).toBeFalsy();
		});
		
		it("new prp.Entity(id, true) deve carregar propriedades", function() {
			// given
			var idEntidade = 999;
			var valorDaPropriedade = '123';
			prp.Ajax.getAgora = function(arg) {
				expect(arg).toEqual(prp.Entity.URL + idEntidade);
				return { propriedadeteste: valorDaPropriedade };
			};
			// when
			var entity = new prp.Entity(idEntidade, true);
			// then
			expect(entity.isPropriedadesCarregadas()).toBeTruthy();
			expect(entity.propriedadeteste).toEqual(valorDaPropriedade);
		});
		
		it("Entity.carregarPropriedades() deve carregar propriedades", function() {
			// given
			var idEntidade = 999;
			var valorDaPropriedade = '123';
			prp.Ajax.get = function(argURL, argCallback) {
				expect(argURL).toEqual(prp.Entity.URL + idEntidade);
				return argCallback({ propriedadeteste: valorDaPropriedade });
			};
			// when
			var entity = new prp.Entity(idEntidade);
			
			var callback = function () {
				expect(entity.isPropriedadesCarregadas()).toBeTruthy();
				expect(entity.propriedadeteste).toEqual(valorDaPropriedade);
			};
			
			entity.carregarPropriedades(callback);
			// then
			// verificacoes estao na callback
		});
		
		it("Entity deve permitir heranca de seus metodos", function() {
			// given
			var filhaTesteURL = 'filhateste/';
			(function (prp) {
				prp.FilhaTeste = function (id, carregarPropriedadesAgora) {
					prp.Entity.call(this, id, carregarPropriedadesAgora); // super
				};
				// herança
				prp.FilhaTeste.prototype = Object.create(prp.Entity.prototype);
				prp.FilhaTeste.prototype.constructor = prp.FilhaTeste;
				
				prp.FilhaTeste.URL = filhaTesteURL;
			})(prp);
			
			var idFilhaTeste = 5656;
			var valorDaPropriedade = '123';
			
			prp.Ajax.getAgora = function(argURL) {
				expect(argURL).toEqual(filhaTesteURL + idFilhaTeste);
				return { propriedadeteste: valorDaPropriedade };
			};
			// when
			var filhaTeste = new prp.FilhaTeste(idFilhaTeste, true);
			// then
			expect(filhaTeste.isPropriedadesCarregadas()).toBeTruthy();
			expect(filhaTeste.propriedadeteste).toEqual(valorDaPropriedade);
		});
		
	});
	
});