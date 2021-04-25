
function deleteUser() {

    let id = document.getElementById("idToDelete").value;
    let url = "http://localhost:8080/deleteUser/" + id.value;
    fetch(url, {method: 'DELETE'})
        .then(() => setTimeout(showAllUsers, 50))
        .then(() => clearDeleteForm());
}

function clearDeleteForm() {
    document.getElementById("firstNameDelete").value;
    document.getElementById("lastnameDelete").value;
    document.getElementById("ageDelete").value;
    document.getElementById("emailDelete").value;
    document.getElementById("passwordDelete").value;
    document.getElementById("rolesToDelete");
    for (let i = 0; i < select.children.length; i++) {
        select.children[i].remove();
    }
}



