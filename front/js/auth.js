const BASE_URL = "http://localhost:9091";

function togglePassword() {
    const passwordInput = document.getElementById("password");
    const toggleIcon = document.querySelector(".toggle-eye");
    
    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        toggleIcon.classList.remove("fa-eye");
        toggleIcon.classList.add("fa-eye-slash");
    } else {
        passwordInput.type = "password";
        toggleIcon.classList.remove("fa-eye-slash");
        toggleIcon.classList.add("fa-eye");
    }
}

function login() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch(BASE_URL + "/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            email: email,
            password: password
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            // ✅ Save JWT
            localStorage.setItem("token", data.data.token);

            alert("Login successful");
            window.location.href = "dashboard.html";
        } else {
            alert(data.message);
        }
    })
    .catch(error => {
        alert("Server error");
        console.error(error);
    });
}

function register() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const role = document.getElementById("role").value;

    fetch(BASE_URL + "/auth/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: name,
            email: email,
            password: password,
            role: role
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert("Registration successful. Please login.");
            navigateTo("index.html");
        } else {
            alert(data.message);
        }
    })
    .catch(error => {
        alert("Server error");
        console.error(error);
    });
}
