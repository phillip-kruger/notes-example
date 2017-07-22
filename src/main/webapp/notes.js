/* 
 * Javascript file for notes.html
 */
var webSocket;

$('document').ready(function(){
    openSocket();
});

window.onbeforeunload = function() {
    closeSocket();
};

function getNote(title){
    webSocket.send(title);
}

function openSocket(){
    // Ensures only one connection is open at a time
    if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
        writeResponse("Already connected...");
        return;
    }
    // Create a new instance of the websocket
    var loc = window.location, new_uri;
    if (loc.protocol === "https:") {
        new_uri = "wss:";
    } else {
        new_uri = "ws:";
    }
    new_uri += "//" + loc.host;
    new_uri += "/notes-example/note";
    webSocket = new WebSocket(new_uri);

    /**
     * Binds functions to the listeners for the websocket.
     */
    webSocket.onopen = function(event){
        if(event.data === undefined)
            return;
        
        writeResponse(event.data);
        
    };

    webSocket.onmessage = function(event){
        
        var json = JSON.parse(event.data);
        
        var type = json.changeType;
        var title = json.title;
        var note = json.text;
        var created = new Date(json.created).toDateString();
        
        if(type == "UPDATE"){ 
            removeCard(title);
            writeCard(title,created,note);
        }else if( type == "DELETE"){
            removeCard(title);
        }else {
            writeCard(title,created,note);
        }
        
    };

    webSocket.onclose = function(){
        writeResponse("Connection closed");
    };

}

function closeSocket(){
    webSocket.close();
}
function removeCard(title){
    $("#" + toHTMLId(title)).remove();
}

function writeCard(title,created,note){
    writeResponse("<div class='ui yellow card' id='" + toHTMLId(title) +"'><div class='content'><a class='header'>" 
                + title 
                + "</a><div class='meta'><span class='date'>" 
                + created 
                + "</span></div><div class='description'>" 
                + note 
                + "</div></div></div>");
}

function writeResponse(text){
    messages.innerHTML += text + "<br/>";
}

function toHTMLId(title){
    return title.split(' ').join('_');
}