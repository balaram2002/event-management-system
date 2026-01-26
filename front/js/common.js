const BASE_URL = "http://localhost:9091";

/* ---------- NAVIGATION HELPER ---------- */
function navigateTo(url) {
    window.location.href = url;
}

/* ---------- AUTH HEADER ---------- */
function getAuthHeaders() {
    const token = localStorage.getItem("token");

    return {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
    };
}

/* ---------- CHECK LOGIN ---------- */
function checkLogin() {
    const token = localStorage.getItem("token");
    if (!token) {
        navigateTo("index.html");
    }
}

/* ---------- LOGOUT ---------- */
function logout() {
    localStorage.removeItem("token");
    alert("Logged out successfully");
    navigateTo("index.html");
}
