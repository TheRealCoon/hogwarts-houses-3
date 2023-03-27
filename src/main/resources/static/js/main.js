function deleteRoom(button){
    let tr = button.parentElement.parentElement
    let id = tr.firstElementChild.innerText
    deleteRoomById(id)
}
function deleteRoomById(id) {
    let fetchOptions = {
        method: "DELETE",
        mode: "cors",
        cache: "no-cache",
        headers: {
            'Content-Type': 'text/html; charset=UTF-8'
        }
    }

    fetch('http://localhost:8080/rooms/' + id, fetchOptions).then(
        resp => resp.text()
    ).then(
        html => document.body.innerHTML = html
    )
}

function updateRoom(button){
    let tr = button.parentElement.parentElement
    let id = tr.firstElementChild.innerText
    updateRoomById(id)
}

function updateRoomById(id){
    let fetchOptions = {
        method: "PUT",
        mode: "cors",
        cache: "no-cache",
        headers: {
            'Content-Type': 'text/html; charset=UTF-8'
        }
    }

    fetch('http://localhost:8080/rooms/' + id, fetchOptions).then(
        resp => resp.text()
    ).then(
        html => document.body.innerHTML = html
    )
}

function getRoomById(button){
    let parentDiv = button.parentElement;
    let id = parentDiv.firstElementChild.value;
    getRoomByIdRequest(id);
}

function getRoomByIdRequest(id){
    let fetchOptions = {
        method: "GET",
        mode: "cors",
        cache: "no-cache",
        headers: {
            'Content-Type': 'text/html; charset=UTF-8'
        }
    }
    fetch('http://localhost:8080/rooms/' + id, fetchOptions).then(
        resp => resp.text()
    ).then(
        html => document.body.innerHTML = html
    )
}