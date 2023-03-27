function findStudentByID(button){
    let parentDiv = button.parentElement;
    let id = parentDiv.firstElementChild.value;

    let fetchOptions = {
        method: "GET",
        mode: "cors",
        cache: "no-cache",
        headers: {
            'Content-Type': 'text/html; charset=UTF-8'
        }
    }

    fetch("http://localhost:8080/students/" + id, fetchOptions).then(
        resp => resp.text()
    ).then(
        html => document.body.innerHTML = html
    )
}

function addStudentToRoom(button){
    let parentDiv = button.parentElement;
    let studentId = parentDiv.children[0].value;
    let roomId = parentDiv.children[1].value;

    let fetchOptions = {
        method: "POST",
        mode: "cors",
        cache: "no-cache",
        headers: {
            'Content-Type': 'text/html; charset=UTF-8'
        }
    }

    fetch("http://localhost:8080/students/" + studentId + "/addToRoom/" + roomId, fetchOptions).then(
        resp => resp.text()
    ).then(
        html => document.body.innerHTML = html
    )
}

function deleteStudent(button){
    let tr = button.parentElement.parentElement
    let id = tr.firstElementChild.innerText

    let fetchOptions = {
        method: "DELETE",
        mode: "cors",
        cache: "no-cache",
        headers: {
            'Content-Type': 'text/html; charset=UTF-8'
        }
    }
    fetch("http://localhost:8080/students/" + id, fetchOptions).then(
        resp => resp.text()
    ).then(
        html => document.body.innerHTML = html
    )
}

function updateStudentPage(button){
    let tr = button.parentElement.parentElement
    let id = tr.querySelector(':nth-child(1)').innerHTML
    let name = tr.querySelector(':nth-child(2)').innerHTML
    let houseType = tr.querySelector(':nth-child(3)').innerHTML
    let petType = tr.querySelector(':nth-child(4)').innerHTML

    let data = {
        id: id,
        name: name,
        houseType: houseType,
        petType: petType
    }

    let fetchOptions = {
        method: "PUT",
        mode: "cors",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }

    fetch('http://localhost:8080/students', fetchOptions).then(
        resp => resp.text()
    ).then(
        html => document.body.innerHTML = html
    )
}

function updateStudent(button){
    let form = button.parentElement.parentElement
    let formGroups = form.getElementsByClassName('form-group')

    let id = formGroups[0].querySelector(':nth-child(2)').value
    let name = formGroups[1].querySelector(':nth-child(2)').value
    let houseType = formGroups[2].querySelector(':nth-child(2)').value
    let petType = formGroups[3].querySelector(':nth-child(2)').value

    let data = {
        name: name,
        houseType: houseType,
        petType: petType
    }

    let fetchOptions = {
        method: "PUT",
        mode: "cors",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }

    fetch('http://localhost:8080/students/' + id, fetchOptions).then(
        resp => resp.text()
    ).then(
        html => document.body.innerHTML = html
    )
}

function brewPage(button){
    let tr = button.parentElement.parentElement
    let id = tr.querySelector(':nth-child(1)').innerHTML
    let name = tr.querySelector(':nth-child(2)').innerHTML

    let fetchOptions = {
        method: "GET",
        mode: "cors",
        headers: {
            'Content-Type': 'text/html; charset=UTF-8'
        }
    }

    fetch('http://localhost:8080/students/brewing/' + id + '/' + name, fetchOptions).then(
        resp => resp.text()
    ).then(
        html => document.body.innerHTML = html
    )
}

function getLearnedRecipes(button){
    let tr = button.parentElement.parentElement
    let id = tr.firstElementChild.innerText

    let fetchOptions = {
        method: "GET",
        mode: "cors",
        headers: {
            'Content-Type': 'text/html; charset=UTF-8'
        }
    }

    fetch('http://localhost:8080/potions/' + id, fetchOptions).then(
        resp => resp.text()
    ).then(
        html => document.body.innerHTML = html
    )
}