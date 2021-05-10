showUserInfo();

function showUserInfo(users) {
        fetch('http://localhost:8080/getAuthorizedUser')
            .then(response => response.json())
            .then(user => {
                    //заполняем таблицу информацией юзера
                    let tBody = document.getElementById("user_info");
                    tBody.innerHTML = "";

            let row = tBody.insertRow(0);
            let cell1 = row.insertCell(0);
            cell1.innerHTML = user.firstName;
            let cell2 = row.insertCell(1);
            cell2.innerHTML = user.lastName;
            let cell3 = row.insertCell(2);
            cell3.innerHTML = user.age;
            let cell4 = row.insertCell(3);
            cell4.innerHTML = user.email;
            let cell5 = row.insertCell(4);
            cell5.innerHTML = getRoleUser(user).textContent;
        });


    function getRoleUser(user) {
        let rolesList = document.createElement('ul');

        for (let i = 0; i < user.authorities.length; i++) {
            let role = document.createElement('li');
            role.textContent = user.authorities[i].role + " ";
            rolesList.appendChild(role);
        }
        return rolesList;


}
}