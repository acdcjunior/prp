// ============================================================
// Register Namespace
// ------------------------------------------------------------
var net = net || {};
net.acdcjunior = net.acdcjunior || {};
net.acdcjunior.prp = net.acdcjunior.prp || {};

(function(net) {
	// ============================================================
	// Constructor - MUST BE AT TOP OF FILE
	// ------------------------------------------------------------
	net.acdcjunior.prp.Rectangle = function(width, height, color) {
		this.Width = width;
		this.Height = height;
		this.Color = color;
	};

	// ============================================================
	// Member Functions & Variables
	// ------------------------------------------------------------
	net.acdcjunior.prp.Rectangle.prototype = {
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
	net.acdcjunior.prp.Rectangle.Sides = 4;

	// ============================================================
	// Static Functions
	// ------------------------------------------------------------
	net.acdcjunior.prp.Rectangle.CreateSmallBlue = function() {
		return new net.acdcjunior.prp.Rectangle(5, 8, '#0000ff');
	};
	net.acdcjunior.prp.Rectangle.CreateBigRed = function() {
		return new net.acdcjunior.prp.Rectangle(50, 25, '#ff0000');
	};
	
	net.acdcjunior.prp.Rectangle.getHotness = function() {
		return isHot;
	};
	
	net.acdcjunior.prp.Rectangle.toggleHotness = function() {
		isHot = !isHot;
	};

	
	
    //Private Property
    var isHot = true;

    //Public Property
    net.acdcjunior.prp.Rectangle.ingredient = "Bacon Strips";

    //Public Method
    net.acdcjunior.prp.Rectangle.fry = function() {
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