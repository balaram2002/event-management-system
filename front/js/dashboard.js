function initDashboard() {
    checkLogin();

    const token = localStorage.getItem("token");
    const payload = JSON.parse(atob(token.split(".")[1]));
    const role = payload.role;

    document.getElementById("roleText").innerText =
        "Logged in as: " + role;

    if (role === "ORGANIZER") {
        document.getElementById("organizerSection").style.display = "block";
    } else if (role === "USER") {
        document.getElementById("userSection").style.display = "block";
    }
}

/* ---------- NAVIGATION ---------- */
function goToCreateEvent() {
    navigateTo("create-event.html");
}

function goToCreateTicket() {
    navigateTo("ticket-create.html");
}

function goToOrganizerDashboard() {
    navigateTo("organizer-dashboard.html");
}

function goToEvents() {
    navigateTo("event-list.html");
}

function goToBookTicket() {
    navigateTo("ticket-book.html");
}

function goToFeedback() {
    navigateTo("feedback.html");
}
