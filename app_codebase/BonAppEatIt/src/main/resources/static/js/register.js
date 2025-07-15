document.addEventListener('DOMContentLoaded', () => {
    const registrationForm = document.getElementById('registration-form');

    registrationForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const response = await fetch('https://localhost:8443/api/v1/auth/register', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: `${document.getElementById("email").value}`,
                emailConfirmation: `${document.getElementById("email-confirmation").value}`,
                username: `${document.getElementById("nom-utilisateur").value}`,
                password: `${document.getElementById("mot-de-passe").value}`,
                passwordConfirmation: `${document.getElementById("confirmation-mot-de-passe").value}`,
            })
        });

        const data = await response.json();
        if (response.ok) {
            window.location.href = 'index.html';
        } else {
            alert(data.errors[0].message);
        }
    })
});
