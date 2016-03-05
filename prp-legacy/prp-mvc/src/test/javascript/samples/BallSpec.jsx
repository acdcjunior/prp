console.log('BallSpec.js -- DEFINE BEGIN (prp-mock, Mes)', window.jQuery != undefined);
define('x', ['jQuery'] , function() {
	console.debug('x done;');
});
define('prp-Ajax',['x', 'prp/util/Ajax'], function() {
	console.debug('ajax');
});
define('prp-Mes',['prp/util/Mes'], function() {
	console.debug('mes');
});
define('prp-Entity',['prp-Ajax', 'prp-Mes', 'prp/Entity'], function() {
	console.debug('Entity');
});
define('four',['jas/four'], function() {});
define('five',['jas/five', 'four'], function() {});
define(['prp-Entity'], function() {
	
	console.warn('BallSpec.js -- RUN BEGIN', window.jQuery != undefined);
	
	describe("BALL", function() {
		
		it("ball() deve rodar", function() { expect(1).toEqual(1); console.info("************************ BALL", window.jQuery != undefined); });
		it("ball() deve rodar", function() { expect(1).toEqual(1); console.info("************************ BALL", window.jQuery != undefined); });
		it("ball() deve rodar", function() { expect(1).toEqual(1); console.info("************************ BALL", window.jQuery != undefined); });
		it("ball() deve rodar", function() { expect(1).toEqual(1); console.info("************************ BALL", window.jQuery != undefined); });
		it("ball() deve rodar", function() { expect(1).toEqual(1); console.info("************************ BALL", window.jQuery != undefined); });
		it("ball() deve rodar", function() { expect(1).toEqual(1); console.info("************************ BALL", window.jQuery != undefined); });
		it("ball() deve rodar", function() { expect(1).toEqual(1); console.info("************************ BALL", window.jQuery != undefined); });
		it("ball() deve rodar", function() { expect(1).toEqual(1); console.info("************************ BALL", window.jQuery != undefined); });
		it("ball() deve rodar", function() { expect(1).toEqual(1); console.info("************************ BALL", window.jQuery != undefined); });
		it("ball() deve rodar", function() { expect(1).toEqual(1); console.info("************************ BALL", window.jQuery != undefined); });
		
	});
	
	console.warn('BallSpec.js -- RUN END', window.jQuery != undefined);

//	});
});
console.log('BallSpec.js -- DEFINE END', window.jQuery != undefined);