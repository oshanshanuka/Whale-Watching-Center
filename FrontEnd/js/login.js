$(document).ready(function () {
    $('#loginBtn').click(function () {
        let email = $('#email').val().trim();
        let password = $('#password').val().trim();

        if (!email || !password) {
            Swal.fire('Warning', 'Please enter both email and password.', 'warning');
            return;
        }

        $.ajax({
            url: 'http://localhost:8080/api/v1/auth/authenticate',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                "email": email,
                "password": password
            }),
            success: function (response) {
                console.log(response);
                if (response.data && response.data.token) {
                    Swal.fire('Login Successful', 'Welcome to Whale Watch Tours!', 'success');
                    localStorage.setItem('token', response.data.token);
                    console.log('User role:', response.data.role);

                    // Redirect based on role
                    if (response.data.role === 'ADMIN') {
                        window.location.href = 'admin_dashboard.html';
                    } else {
                        window.location.href = 'user_dashboard.html';
                    }
                } else {
                    Swal.fire('Login failed', 'Invalid response from server.', 'error');
                }
            },
            error: function (error) {
                console.log('Error:', error);
                if (error.status === 401) {
                    Swal.fire('Invalid Credentials', 'Please check your email and password.', 'error');
                } else {
                    Swal.fire('Error', 'Something went wrong! ' + (error.responseText || ''), 'error');
                }
            }
        });
    });
});
