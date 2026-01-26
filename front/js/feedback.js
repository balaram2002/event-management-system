function loadEventsForFeedback() {
    checkLogin();

    fetch(BASE_URL + "/events", {
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
    })
    .catch(err => {
        console.error(err);
        alert("Error loading events");
    });
}

function submitFeedback() {

    const eventId = document.getElementById("eventSelect").value;
    const comment = document.getElementById("comment").value;

    if (!eventId || comment.trim() === "") {
        alert("Please select event and write feedback");
        return;
    }

    fetch(BASE_URL + "/feedback", {
        method: "POST",
        headers: getAuthHeaders(),
        body: JSON.stringify({
            eventId: eventId,
            comment: comment
        })
    })
    .then(res => res.json())
    .then(data => {
        if (data.success) {
            alert("Feedback submitted successfully");
            navigateTo("dashboard.html");
        } else {
            alert(data.message);
        }
    })
    .catch(err => {
        console.error(err);
        alert("Feedback failed");
    });
}
