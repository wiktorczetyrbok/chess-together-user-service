var board;
var selectedId;

buildBoard();

function buildBoard() {
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	  if (this.readyState == 4 && this.status == 200) {
	    board = JSON.parse(this.responseText);
	    document.getElementById("status").innerHTML = board.activePlayer + "'s turn";
		var chessTable = document.getElementById("chessTable");
		for (var y=8; y>=1; y--) {
			var row = document.createElement("tr");
			chessTable.appendChild(row);
			for (var x=1; x<=8; x++) {
				var id = "" + y + "." + x;
				var data = document.createElement("td");
				row.appendChild(data);
				var image = document.createElement("img");
				image.src = getImage(board, y, x);
				image.id = id;
				image.addEventListener("click", function(){ processClick(this.id) });
				data.appendChild(image);
			}
		}
		var promotionalPieceTable = document.getElementById("promotionPieces");
		var headerRow = document.createElement("th");
		headerRow.innerHtml = "Promotion Pieces"
		promotionalPieceTable.appendChild(headerRow);
		for (var i=0; i<board.promotionPieces.length; i++) {
			var row = document.createElement("tr");
			var data = document.createElement("td");
			row.appendChild(data);

			var image = document.createElement("img");
			image.src = "images/White" + board.promotionPieces[i] + "DarkBackground.png";
			data.appendChild(image);
			promotionalPieceTable.appendChild(row);
		}
	  }
	};
	xmlhttp.open("GET", "http://localhost:8080/chess/board.json", true);
	xmlhttp.send();
}

function updateBoard(startId, endId) {
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	  if (this.readyState == 4 && this.status == 200) {
	    board = JSON.parse(this.responseText);
	    var status = board.activePlayer + "'s turn";
	    if (board.status!=null) {
	    	status = status + " (" + board.status + ")";
	    }
	    document.getElementById("status").innerHTML = status;
		for (var y=8; y>=1; y--) {
			for (var x=1; x<=8; x++) {
				var id = "" + y + "." + x;
				var image = document.getElementById(id);
				image.src = getImage(board, y, x);
			}
		}
	  }
	};
	var url = "http://localhost:8080/chess/board.json?x1=" + startId.replace(".", "&y1=") + "&x2=" + endId.replace(".", "&y2=");
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}

function getImage(board, x, y) {
	var background = getBackground(x, y);
	for (var i=0; i<board.squares.length; i++) {
		if (board.squares[i].x == x && board.squares[i].y == y) {
			if (board.squares[i].piece == null) {
				return "images/" + background;
			} else {
				return "images/" + board.squares[i].piece.owner + board.squares[i].piece.type + background;
			}
		}
	}
	return background;
}

function getBackground(x, y) {
	if ((x+y)%2==0) {
		return "DarkBackground.png";
	} else {
		return "LightBackground.png";
	}
}

function processClick(id) {
	removeAllInvertedImages();
	if (selectedId == id) {
		selectedId = null;
	} else {
		if (imageIsAMovablePiece(id)) {
			highlightAPieceAndItsMoves(id);
		} else if (pieceCanBeMoved(selectedId, id)) {
			updateBoard(selectedId, id);
			selectedId = null;
		} else {
			selectedId = null;
		}
	}

}

function removeAllInvertedImages() {
	var selected = document.getElementsByClassName("inverted");
	for (var i=selected.length-1; i>=0; i--) {
		selected[i].classList.remove('inverted');
	}
}

function imageIsAMovablePiece(id) {
	var squareCanBeSelected = false;
	for (var i=0; i<board.moves.length; i++) {
		var moveStartId = board.moves[i].x1 + "." + board.moves[i].y1;
		if (moveStartId == id) {
			squareCanBeSelected = true;
		}
	}
	return squareCanBeSelected;
}

function highlightAPieceAndItsMoves(id) {
	selectedId = id;
	document.getElementById(id).classList.add('inverted');
	for (var i=0; i<board.moves.length; i++) {
		var moveStartId = board.moves[i].x1 + "." + board.moves[i].y1;
		if (moveStartId == id) {
			var moveEndId = board.moves[i].x2 + "." + board.moves[i].y2;
			document.getElementById(moveEndId).classList.add('inverted');
		}
	}
}

function pieceCanBeMoved(selectedId, id) {
	var squareCanBeSelected = false;
	for (var i=0; i<board.moves.length; i++) {
		var moveStartId = board.moves[i].x1 + "." + board.moves[i].y1;
		var moveEndId = board.moves[i].x2 + "." + board.moves[i].y2;
		if (moveStartId == selectedId && moveEndId == id) {
			squareCanBeSelected = true;
		}
	}
	return squareCanBeSelected;
}
