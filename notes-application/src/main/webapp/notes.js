/* 
 * Semantic-UI
 * Javascript file for notes.html
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
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
    new_uri += "/notes-application/note";
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
        console.log(json);
        var type = json.changeType;
        var title = json.title;
        var note = json.text;
        var style = json.style;
        var tags = json.tags;
        var created = new Date(json.created).toDateString();
        
        if(type == "update"){ 
            removeCard(title);
            writeCard(title,created,note,style,tags);
        }else if( type == "delete"){
            removeCard(title);
        }else {
            writeCard(title,created,note,style,tags);
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

function writeCard(title,created,note,style,tags){
    
    writeResponse("<div class='ui " + style + " card' id='" + toHTMLId(title) +"'>"
                    + "<div class='content'>"
                        + "<a class='header'>" 
                            + title 
                        + "</a>"
                        + "<div class='meta'>"
                            + "<span class='date'>" 
                                + created 
                            + "</span>"
                        + "</div>" 
                        + "<div class='description'>" 
                            + note + "<br/>"
                            + writeTags(tags)     
                        + "</div>" 
                    + "</div>" 
                + "</div>");
}

function writeResponse(text){
    messages.innerHTML += text + "<br/>";
}

function writeTags(tags){
    var t = "";
    tags.forEach(function(entry) {
        t = t + "<div class='ui label'>"
                + "<i class='tag icon'></i> " + entry
            + "</div>";
    });
    
    return t;
}

function toHTMLId(title){
    return title.split(' ').join('_');
}