function editUser() {

    fetch('http://localhost:8080/editUser', {
        method: 'PUT',
        body: JSON.stringify({
            id: window.formEditUser.editID.value,
            firstName: window.formEditUser.editFirstName.value,
            lastName: window.formEditUser.editLastName.value,
            age: window.formEditUser.editAge.value,
            email: window.formEditUser.editEmail.value,
            password: window.formEditUser.editPassword.value,
            roles: window.formEditUser.editRoles.value
        }),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
        .then(response => response.json())
        .then(user => {
            allUsers();
        });
}