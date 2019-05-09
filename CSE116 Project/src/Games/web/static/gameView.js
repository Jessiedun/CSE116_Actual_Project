var socket = io.connect({transports: ['websocket']});
socket.on('gameState', parseGameState);

const tileSize = 30;

var canvas = document.getElementById("canvas");
var context = canvas.getContext("2d");
context.globalCompositeOperation = 'source-over';

function parseGameState(event) {
    // console.log(event);
    const gameState = JSON.parse(event);

    drawGameBoard(gameState['gridSize']);

    placeSquare(gameState['start']['x'], gameState['start']['y'], '#00ff00');

    const health = gameState['baseHealth'];
    const maxHealth = gameState['maxBaseHealth'];
    const percentHealth = health / maxHealth;
    const color = rgb(percentHealth * 255, percentHealth * 255, 0);

    placeSquare(gameState['base']['x'], gameState['base']['y'], color);

    for (let tower of gameState['towers']) {
        drawTower(tower['x'], tower['y']);
    }

    for (let player of gameState['players']) {
        placeCircle(player['x'], player['y'], player['id'] === socket.id ? '#ffff00' : '#56bcff', 2.0);
    }

    for (let wall of gameState['walls']) {
        placeSquare(wall['x'], wall['y'], 'grey');
    }

    for (let projectile of gameState['projectiles']) {
        placeCircle(projectile['x'], projectile['y'], 'red', 1.0);
    }

}

function cleanInt(input) {
    const value = Math.round(input);
    const asString = value.toString(16);
    return value > 15 ? asString : "0" + asString;
}

function rgb(r, g, b) {
    return "#" + cleanInt(r) + cleanInt(g) + cleanInt(b);
}


function drawGameBoard(gridSize) {

    const gridWidth = gridSize['x'];
    const gridHeight = gridSize['y'];

    context.clearRect(0, 0, gridWidth * tileSize, gridHeight * tileSize);

    canvas.setAttribute("width", gridWidth * tileSize);
    canvas.setAttribute("height", gridHeight * tileSize);

    context.strokeStyle = '#bbbbbb';
    for (let j = 0; j <= gridWidth; j++) {
        context.beginPath();
        context.moveTo(j * tileSize, 0);
        context.lineTo(j * tileSize, gridHeight * tileSize);
        context.stroke();
    }
    for (let k = 0; k <= gridHeight; k++) {
        context.beginPath();
        context.moveTo(0, k * tileSize);
        context.lineTo(gridWidth * tileSize, k * tileSize);
        context.stroke();
    }

}


function placeSquare(x, y, color) {
    context.fillStyle = color;
    context.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
    context.strokeStyle = 'black';
    context.strokeRect(x * tileSize, y * tileSize, tileSize, tileSize);
}


function placeCircle(x, y, color, size) {
    context.fillStyle = color;
    context.beginPath();
    context.arc(x * tileSize,
        y * tileSize,
        size / 10.0 * tileSize,
        0,
        2 * Math.PI);
    context.fill();
    context.strokeStyle = 'black';
    context.stroke();
}

function xComp(degrees){
    return Math.cos(Math.PI*degrees/180.0)
}

function yComp(degrees){
    return Math.sin(Math.PI*degrees/180.0)
}

function drawTower(x, y) {
    const size = 3.0;

    const scaledSize = size / 10.0 * tileSize;
    const centerX = (x + 0.5) * tileSize;
    const centerY = (y + 0.5) * tileSize;

    context.fillStyle = '#760672';
    context.strokeStyle = 'black';

    context.beginPath();
    context.moveTo(centerX + scaledSize , centerY);
    for(let i = 0; i<=7; i++){
        const degrees = i*60.0;
        context.lineTo(centerX + xComp(degrees) * scaledSize, centerY + yComp(degrees) * scaledSize);
    }

    context.lineWidth = 5;
    context.stroke();
    context.lineWidth = 1;
    context.fill()
}

var myGamePiece;

function startGame() {
    myGameArea.start();
    myGamePiece = new component(30, 30, "red", 10, 120);
}

var myGameArea = {
    canvas : document.createElement("canvas"),
    start : function() {
        this.canvas.width = 480;
        this.canvas.height = 270;
        this.context = this.canvas.getContext("2d");
        document.body.insertBefore(this.canvas, document.body.childNodes[0]);
        this.interval = setInterval(updateGameArea, 20);
        window.addEventListener('keydown', function (e) {
            myGameArea.keys = (myGameArea.keys || []);
            myGameArea.keys[e.keyCode] = (e.type == "keydown");
        })
        window.addEventListener('keyup', function (e) {
            myGameArea.keys[e.keyCode] = (e.type == "keydown");
        })
    },
    clear : function(){
        this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
    }
}

function component(width, height, color, x, y) {
    this.gamearea = myGameArea;
    this.width = width;
    this.height = height;
    this.speedX = 50;
    this.speedY = 50;
    this.x = x;
    this.y = y;
    this.update = function() {
        ctx = myGameArea.context;
        ctx.fillStyle = color;
        ctx.fillRect(this.x, this.y, this.width, this.height);
    }
    this.newPos = function() {
        this.x += this.speedX;
        this.y += this.speedY;
    }
}

function updateGameArea() {
    myGameArea.clear();
    myGamePiece.speedX = 0;
    myGamePiece.speedY = 0;
    if (myGameArea.keys && myGameArea.keys[37]) {myGamePiece.speedX = -1; }
    if (myGameArea.keys && myGameArea.keys[39]) {myGamePiece.speedX = 1; }
    if (myGameArea.keys && myGameArea.keys[38]) {myGamePiece.speedY = -1; }
    if (myGameArea.keys && myGameArea.keys[40]) {myGamePiece.speedY = 1; }
    myGamePiece.newPos();
    myGamePiece.update();
    //lol
}