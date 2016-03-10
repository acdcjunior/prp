// ============================================================
// Register Namespace
// ------------------------------------------------------------
var io = io || {};
io.github =  io.github || {};
io.github.acdcjunior = io.github.acdcjunior || {};
io.github.acdcjunior.prp = io.github.acdcjunior.prp || {};

(function(net) {
	// ============================================================
	// Constructor - MUST BE AT TOP OF FILE
	// ------------------------------------------------------------
	io.github.acdcjunior.prp.Rectangle = function(width, height, color) {
		this.Width = width;
		this.Height = height;
		this.Color = color;
	};

	// ============================================================
	// Member Functions & Variables
	// ------------------------------------------------------------
	io.github.acdcjunior.prp.Rectangle.prototype = {
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
	io.github.acdcjunior.prp.Rectangle.Sides = 4;

	// ============================================================
	// Static Functions
	// ------------------------------------------------------------
	io.github.acdcjunior.prp.Rectangle.CreateSmallBlue = function() {
		return new io.github.acdcjunior.prp.Rectangle(5, 8, '#0000ff');
	};
	io.github.acdcjunior.prp.Rectangle.CreateBigRed = function() {
		return new io.github.acdcjunior.prp.Rectangle(50, 25, '#ff0000');
	};
	
	io.github.acdcjunior.prp.Rectangle.getHotness = function() {
		return isHot;
	};
	
	io.github.acdcjunior.prp.Rectangle.toggleHotness = function() {
		isHot = !isHot;
	};

	
	
    //Private Property
    var isHot = true;

    //Public Property
    io.github.acdcjunior.prp.Rectangle.ingredient = "Bacon Strips";

    //Public Method
    io.github.acdcjunior.prp.Rectangle.fry = function() {
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

})(net);