$(document).ready(function () {
    restartAllUser();
    $('.AddBtn').on('click', function () {
        let user = {
            firstname: $("#firstname").val(),
            surname: $("#surname").val(),
            age: $("#age").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            rolesOfUser: getRole("#selectRole")
        }
        console.log(user);
        fetch("api/newUser", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(user)
        }).then(() => openTabById('nav-home'))
            .then(() => restartAllUser());
        $('input').val('');
    });
});

function createTableRow(user) {
    let userRole = "";
    for (let i = 0; i < user.rolesOfUser.length; i++) {
        userRole += " " + user.rolesOfUser[i].role;
    }
    return `<tr id="user_table_row">
            <td>${user.id}</td>
            <td>${user.firstname}</td>
            <td>${user.surname}</td>
            <td>${user.age}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${userRole}</td>
            <td>
            <a  href="/api/${user.id}" class="btn btn-info eBtn">Edit</a>
            </td>
            <td>
            <a  href="/api/delete/${user.id}" class="btn btn-danger delBtn">Delete</a>
            </td>
        </tr>`;
}

function getRole(address) {
    let data = [];
    $(address).find("option:selected").each(function () {
        data.push({
            id: $(this).val(),
            role: $(this).attr("name"),
            authority: $(this).attr("name")})
    });
    return data;
}

function restartAllUser() {
    let UserTableBody = $("#user_table_body")

    UserTableBody.children().remove();

    fetch("api/allUsers")
        .then((response) => {
            response.json().then(data => data.forEach(function (item) {
                let TableRow = createTableRow(item);
                UserTableBody.append(TableRow);

            }));
        }).catch(error => {
        console.log(error);
    });
}

document.addEventListener('click', function (event) {
    event.preventDefault()
    if ($(event.target).hasClass('delBtn')) {
        let href = $(event.target).attr("href");
        delModalButton(href)
    }
    if ($(event.target).hasClass('eBtn')) {
        let href = $(event.target).attr("href");
        $(".editUser #exampleModal").modal();

        $.get(href, function (user) {
            $(".editUser #id").val(user.id);
            $(".editUser #edit-firstname").val(user.firstname);
            $(".editUser #edit-surname").val(user.surname);
            $(".editUser #edit-age").val(user.age);
            $(".editUser #edit-username").val(user.username);
            $(".editUser #edit-password").val(user.password);
            $(".editUser #edit-roles").val(user.rolesOfUser);
        });
    }
    if ($(event.target).hasClass('editButton')) {
        let user = {
            id:$('#id').val(),
            firstname:$('#edit-firstname').val(),
            surname:$('#edit-surname').val(),
            age:$('#edit-age').val(),
            username:$('#edit-username').val(),
            password:$('#edit-password').val(),
            rolesOfUser: getRole("#edit-roles")

        }
        editModalButton(user)
        console.log(user);
    }

    if ($(event.target).hasClass('logout')) {
        logout();
    }
    if ($(event.target).hasClass('btnUserTable')) {
        userTable();
    }
});

function delModalButton(href) {
    fetch(href, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        }
    }).then(() => restartAllUser());
}

function editModalButton(user) {
    fetch("api/edit", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        body: JSON.stringify(user)
    }).then(function () {
        $('input').val('');
        $('.editUser #exampleModal').modal('hide');
        restartAllUser();
    })
}

function openTabById(tab) {
    $('.nav-tabs a[href="#' + tab + '"]').tab('show');
}
function logout(){
    document.location.replace("/logout");
}
function userTable(){
    document.location.replace("/user");
}

