allUsers();

function allUsers(){
    alert("allUsers connect")

    let tBody = document.getElementById("allUsers");
    tBody.innerHTML = "";
    fetch('http://localhost:8080/allUsers')
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
               // cell5.innerHTML = getRolesFromUser(user).textContent;

                let cell6 = row.insertCell();
                cell6.innerHTML =
                    '<button type="button" onclick="modalEdit(' + user.id +')" class="btn btn-primary btn-sm">\n' +
                    '   Edit</button>\n';

                let cell7 = row.insertCell();
                cell7.innerHTML =
                    '<button type="button" onclick="modalDelete(' + user.id +')" class="btn btn-danger btn-sm">' +
                    'Delete</button>\n';
            })
        });
}