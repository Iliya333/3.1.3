function updateUser() {
    let url = "http://localhost:8080/updateUser/";
    let data = document.forms.namedItem("formUpdateUser");
    let select = document.getElementById("rolesToUpdate");
    let selected = [];
    for (let i = 0; i < select.options.length; i++) {
        if (select.options[i].selected) {
            selected.push(select.options[i].text);
        }
    }
    fetch(url, {
        method: 'POST', // или 'PUT'
        body: JSON.stringify({
            id: data.elements.namedItem("idToUpdate").value,
            name: data.elements.namedItem("nameToUpdate").value,
            age: data.elements.namedItem("ageToUpdate").value,
            password: data.elements.namedItem("passwordToUpdate").value,
            roles: selected
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(() => setTimeout(showAllUsers, 50))
        .then(() => cleanUpdateTable());
}

function cleanUpdateTable() {
    let data = document.forms.namedItem("formUpdateUser");
    let select = document.getElementById("rolesToUpdate");
    data.elements.namedItem("idToUpdate").value = "";
    data.elements.namedItem("nameToUpdate").value = "";
    data.elements.namedItem("ageToUpdate").value = "";
    data.elements.namedItem("passwordToUpdate").value = "";
    while (select.hasChildNodes()) {
        select.removeChild(select.lastChild);
    }
}