function findBuildingById(button){
    let parentDiv = button.parentElement
    let id = parentDiv.querySelector(":nth-child(2)").value

    let fetchOptions = {
        method: "GET",
        mode: "cors",
        cache: "no-cache",
        headers: {
            'Content-Type': 'text/html; charset=UTF-8'
        }
    }

    fetch("http://localhost:8080/buildings/" + id, fetchOptions).then(
        resp => resp.text()
    ).then(
        html => document.body.innerHTML = html
    )
}

function deleteBuilding(button){
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
    fetch("http://localhost:8080/buildings/" + id, fetchOptions).then(
        resp => resp.text()
    ).then(
        html => document.body.innerHTML = html
    )
}

function updateBuildingPage(button){
    let tr = button.parentElement.parentElement

    let data = {
        id: tr.querySelector(':nth-child(1)').innerHTML,
        name: tr.querySelector(':nth-child(2)').innerHTML
    }

    let fetchOptions = {
        method: "PUT",
        mode: "cors",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }

    fetch('http://localhost:8080/buildings', fetchOptions).then(
        resp => resp.text()
    ).then(
        html => document.body.innerHTML = html
    )
}

function updateBuilding(button){
    let form = button.parentElement.parentElement
    let formGroups = form.getElementsByClassName('form-group')
    let id = formGroups[0].querySelector(":nth-child(2)").value

    let data = {
        name: formGroups[1].querySelector(":nth-child(2)").value
    }

    let fetchOptions = {
        method: "PUT",
        mode: "cors",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }

    fetch('http://localhost:8080/buildings/' + id, fetchOptions).then(
        resp => resp.text()
    ).then(
        html => document.body.innerHTML = html
    )
}