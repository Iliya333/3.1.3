showAllUsers();

function showAllUsers(){
    let tBody = document.getElementById("allUsers");
    tBody.innerHTML = "";

    fetch('http://localhost:8080/users')
        .then(response => response.json())
        .then(user => {
            user.forEach(function (user) {

                //заполняем таблицу информацией юзера
                let row = tBody.insertRow();
                let cell0 = row.insertCell();
                cell0.innerHTML = user.id;
                let cell1 = row.insertCell();
                cell1.innerHTML = user.firstName;
                let cell2 = row.insertCell();
                cell2.innerHTML = user.lastName;
                let cell3 = row.insertCell();
                cell3.innerHTML = user.age;
                let cell4 = row.insertCell();
                cell4.innerHTML = user.email;
                let cell5 = row.insertCell();
                cell5.innerHTML = getRolesFromUser(user).textContent;

                var cell6 = row.insertCell();
                cell6.innerHTML =
                    '<button type="button" onclick=" edit(' + user.id +')" class="btn btn-info">\n' +
                    '   Edit</button>\n';

                let cell7 = row.insertCell();
                cell7.innerHTML =
                    '<button type="button" onclick="deleteUser(' + user.id +')" class="btn btn-danger">' +
                    'Delete</button>\n';
            })
        });
    function getRolesFromUser(user) {
        let rolesList = document.createElement('ul');

        for (let i = 0; i < user.authorities.length; i++) {
            let role = document.createElement('li');
            role.textContent = user.authorities[i].role + " ";
            rolesList.appendChild(role);
        }
        return rolesList;
    }

}