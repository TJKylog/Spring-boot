$(document).ready(function() {
});

async function register(){
    let data = {}
    data.name = document.getElementById('name_field').value;
    data.email = document.getElementById('email_field').value;
    data.phone = document.getElementById('phone_field').value;
    data.password = document.getElementById('password_field').value;

    if ( document.getElementById('repeat_pass_field').value != data.password){
        alert('Las contraseñas no coinciden');
        return
    }


    const response = await fetch("/register",{
        method: 'POST',
        headers: {
             'Accept' : 'application/json',
             'Content-Type' : 'application/json'
        },
        body: JSON.stringify(data)
    });

    const content = await response.json();
}