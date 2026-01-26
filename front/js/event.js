function submitCreateEvent() {

    const eventData = {
        title: document.getElementById("title").value,
        description: document.getElementById("description").value,
        location: document.getElementById("location").value,
        eventDate: document.getElementById("eventDate").value,
        eventTime: document.getElementById("eventTime").value
    };

    fetch(BASE_URL + "/events", {
        method: "POST",
        headers: getAuthHeaders(),
        body: JSON.stringify(eventData)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert("Event created successfully");
            navigateTo("dashboard.html");
        } else {
            alert(data.message);
        }
    })
    .catch(error => {
        console.error(error);
        alert("Error creating event");
    });
}

function loadEvents() {
    checkLogin();

    fetch(BASE_URL + "/events", {
        method: "GET",
        headers: getAuthHeaders()
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            renderEvents(data.data);
        } else {
            alert(data.message);
        }
    })
    .catch(error => {
        console.error(error);
        alert("Error loading events");
    });
}

function renderEvents(events) {
    const container = document.getElementById("eventList");
    container.innerHTML = "";

    if (events.length === 0) {
        container.innerHTML = "<p>No events available</p>";
        return;
    }

    events.forEach(event => {
        const div = document.createElement("div");
        div.className = "event-card";

        div.innerHTML = `
            <h3>${event.title}</h3>
            <p><b>Location:</b> ${event.location}</p>
            <p><b>Date:</b> ${event.eventDate}</p>
            <button onclick="goToBook(${event.id})">Book Ticket</button>
        `;

        container.appendChild(div);
    });
}

function goToBook(eventId) {
    localStorage.setItem("eventId", eventId);
    navigateTo("ticket-book.html");
}
