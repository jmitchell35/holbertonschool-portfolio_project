document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');

    loginForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const credentials = btoa(`${document.getElementById('email').value}:` +
            `${document.getElementById('mot-de-passe').value}`);


        const response = await fetch('https://localhost:8443/api/v1/auth/login', {
            method: 'POST',
            headers: {
                'Authorization': `Basic ${credentials}`,
                "Content-Type": "application/json"
            }
        })

        const data = await response.json();

        if (response.ok) {
            window.location.href = 'index.html';
        } else {
            alert('Login failed: ' + data.errors[0].message);
        }
    });
});
