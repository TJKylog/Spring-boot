$(document).ready(function() {
});

async function login(){
    let data = {}
    data.email = document.getElementById('email_field').value;
    data.password = document.getElementById('password_field').value;

    const response = await fetch("/login",{
        method: 'POST',
        headers: {
             'Accept' : 'application/json',
             'Content-Type' : 'application/json'
        },
        body: JSON.stringify(data)
    });

    const content = await response.text();
    console.log(content)

    if(content != 'FAIL')
    {
        localStorage.token = content;
        localStorage.email = data.email;
        window.location.href = 'users.html';
    }
}