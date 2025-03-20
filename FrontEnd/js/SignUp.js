$(document).ready(function () {
    $('#registerBtn').click(function () {
        let name = $('#name').val();
        let email = $('#email').val();
        let address = $('#address').val();
        let phone = $('#phone').val();
        let password = $('#password').val();
        let confirmPassword = $('#confirm-password').val();
        let role = $('#role').val();
        let profilePicture = $('#profilePicture')[0].files[0];

        if (password !== confirmPassword) {
            Swal.fire('Error', 'Password and Confirm Password do not match', 'error');
            return;
        }

        let formData = new FormData();
        formData.append('user', new Blob([JSON.stringify({
            "phone": phone,
            "email": email,
            "address": address,
            "password": password,
            "name": name,
            "role": role
        })], { type: "application/json" }));
        formData.append('file', profilePicture);

        $.ajax({
            url: 'http://localhost:8080/api/v1/user/register',
            method: 'POST',
            contentType: false,
            processData: false,
            data: formData,
            success: function (response) {
                console.log('Registration successful:', response);
                Swal.fire('Success', 'User registered successfully', 'success');
                window.localStorage.setItem('token', response.data.token);
                window.location.href = 'login.html';
            },
            error: function (error) {
                console.error('Error:', error);
                Swal.fire('Error', 'An error occurred while registering user: ' + (error.responseText || 'Unknown error'), 'error');
            }
        });
    });
});
