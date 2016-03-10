// ============================================================
// Register Namespace
// ------------------------------------------------------------
var io = io || {};
io.github =  io.github || {};
io.github.acdcjunior = io.github.acdcjunior || {};
io.github.acdcjunior.prp = io.github.acdcjunior.prp || {};

net.acdcjunor.prp.Circle = (function(net) {
	// ============================================================
	// Constructor - MUST BE AT TOP OF FILE
	// ------------------------------------------------------------
	var clazz = function(width, height, color) {
		this.Width = width;
		this.Height = height;
		this.Color = color;
	};

	// ============================================================
	// Member Functions & Variables
	// ------------------------------------------------------------
	clazz.prototype = {
		Width : null,
		Height : null,
		Color : null,
		Draw : function Shape_Rectangle_Draw(canvasId, x, y) {
			var canvas = document.getElementById(canvasId);
			var context = canvas.getContext("2d");
			context.fillStyle = this.Color;
			context.fillRect(x, y, this.Width, this.Height);
		}
	};

	// ============================================================
	// Static Variables
	// ------------------------------------------------------------
	clazz.Sides = 0;

	// ============================================================
	// Static Functions
	// ------------------------------------------------------------
	clazz.CreateSmallBlue = function() {
		return new io.github.acdcjunior.prp.Rectangle(5, 8, '#0000ff');
	};
	clazz.CreateBigRed = function() {
		return new io.github.acdcjunior.prp.Rectangle(50, 25, '#ff0000');
	};
	
	clazz.getHotness = function() {
		return isHot;
	};
	
	clazz.prototype.toggleHotness = function() {
		isHot = !isHot;
	};

	
	
    //Private Property
    var isHot = true;

    //Public Property
    clazz.ingredient = "Bacon Strips";

    //Public Method
    clazz.fry = function() {
        addItem( "\t\n Butter \n\t" );
        addItem( isHot );
        console.log( "Frying " + skillet.ingredient );
    };

    //Private Method
    function addItem( item ) {
        if ( item !== undefined ) {
            console.log( "Adding " + $.trim(item) );
        }
    }

    return clazz;

})(net);