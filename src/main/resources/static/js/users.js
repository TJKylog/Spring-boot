// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTable').DataTable();
  load_users();
});

async function load_users(){
        const response = await fetch("/users",{
            method: 'GET',
            headers: {
                'Accept' : 'application/json',
                'Content-Type' : 'application/json',
                'Authorization': localStorage.token
            }
        });

        const content = await response.json();

        let userList = '';

        for(let user of content)
        {
            let col_id = `<td>${user.id}</td>`;
            let col_name = `<td>${user.name}</td>`;
            let col_email = `<td>${user.email}</td>`;
            let col_phone = `<td>${user.phone}</td>`;
            let col_actions = `<td><div><a href="javascript:void(0);" onclick="delete_user(${user.id})" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a></div></td>`

            let rowUser = `<tr>${col_id}${col_name}${col_email}${col_phone}${col_actions}</tr>`;

            userList += rowUser;
        }

        document.querySelector('#users tbody').outerHTML = userList;
}

async function delete_user(id){
    if(confirm('¿Quiere borrar el usuario?'))
    {
        const response = await fetch(`/user/${id}`,{
            method: 'DELETE',
            headers: {
                'Accept' : 'application/json',
                'Content-Type' : 'application/json',
                'Authorization': localStorage.token
            }
        });
        document.location.reload()
    }

}