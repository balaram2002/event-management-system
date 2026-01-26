function loadTickets() {
    checkLogin();

    const eventId = localStorage.getItem("eventId");
    if (!eventId) {
        alert("No event selected");
        window.location.href = "event-list.html";
        return;
    }

    fetch(BASE_URL + "/events/" + eventId + "/tickets", {
        method: "GET",
        headers: getAuthHeaders()
    })
    .then(res => {
        if (!res.ok) {
            throw new Error(`HTTP error! status: ${res.status}`);
        }
        return res.json();
    })
    .then(data => {
        if (data.success) {
            const select = document.getElementById("ticketSelect");

            if (data.data && data.data.length > 0) {
                data.data.forEach(ticket => {
                    const option = document.createElement("option");
                    option.value = ticket.id;
                    option.text =
                        `${ticket.typeName} - ₹${ticket.price} (Available: ${ticket.availableQuantity})`;
                    select.appendChild(option);
                });
            } else {
                alert("No tickets available for this event");
            }
        } else {
            alert(data.message || "Failed to load tickets");
        }
    })
    .catch(err => {
        alert("Error loading tickets: " + err.message);
        console.error(err);
    });
}

function bookTicket() {

    const ticketTypeId = document.getElementById("ticketSelect").value;
    const quantity = document.getElementById("quantity").value;

    if (!ticketTypeId || quantity <= 0) {
        alert("Please select ticket and quantity");
        return;
    }

    fetch(BASE_URL + "/registrations", {
        method: "POST",
        headers: getAuthHeaders(),
        body: JSON.stringify({
            ticketTypeId: ticketTypeId,
            quantity: quantity
        })
    })
    .then(res => res.json())
    .then(data => {
        if (data.success) {
            alert("Ticket booked successfully");
            navigateTo("dashboard.html");
        } else {
            alert(data.message);
        }
    })
    .catch(err => {
        console.error(err);
        alert("Booking failed");
    });
}
