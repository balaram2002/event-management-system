function loadOrganizerEvents() {
    checkLogin();

    fetch(BASE_URL + "/events/my", {
        method: "GET",
        headers: getAuthHeaders()
    })
    .then(res => res.json())
    .then(data => {
        if (data.success) {
            const select = document.getElementById("eventSelect");
            data.data.forEach(event => {
                const option = document.createElement("option");
                option.value = event.id;
                option.text = event.title;
                select.appendChild(option);
            });
        } else {
            alert(data.message);
        }
    });
}

function createTicket() {

    const eventId = document.getElementById("eventSelect").value;

    if (!eventId) {
        alert("Please select an event");
        return;
    }

    const ticketData = {
        typeName: document.getElementById("typeName").value,
        price: document.getElementById("price").value,
        totalQuantity: document.getElementById("quantity").value
    };

    fetch(BASE_URL + "/events/" + eventId + "/tickets", {
        method: "POST",
        headers: getAuthHeaders(),
        body: JSON.stringify(ticketData)
    })
    .then(res => res.json())
    .then(data => {
        if (data.success) {
            alert("Ticket created successfully");
            navigateTo("dashboard.html");
        } else {
            alert(data.message);
        }
    })
    .catch(err => {
        console.error(err);
        alert("Error creating ticket");
    });
}
