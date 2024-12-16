document.addEventListener("DOMContentLoaded", () => {
    const challengeId = localStorage.getItem("challenge_id"); // Get the challenge ID

    if (!challengeId) {
        alert("No challenge selected.");
        return;
    }

    // Fetch challenge data from API
    fetch(`/api/v1/challenges/${challengeId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch challenge data.");
            }
            return response.json();
        })
        .then(data => {
            // Destructure data from the API
            const { title, duration, start_date, finished_at, count, remaining_days, progress_rate } = data;

            // Update the DOM with challenge data
            document.getElementById("challengeTitle").textContent = title || "Your Challenge";
            document.getElementById("remainingDays").textContent = remaining_days || "N/A";
            document.getElementById("challengeDates").textContent = `${start_date || "N/A"} - ${finished_at || "N/A"}`;
            document.getElementById("totalDays").textContent = duration || 0;
            document.getElementById("completedDays").textContent = count || 0;
            document.getElementById("successRate").textContent = `${progress_rate || 0}% success`;

            // Conditional success message
            const messageElement = document.getElementById("resultMessage");
            if (parseFloat(progress_rate) === 100) {
                messageElement.textContent = "Congratulations!";
                messageElement.classList.add("success-message");
            } else {
                messageElement.innerHTML = `If the result is unsatisfactory, you <em>can try again!</em>`;
                messageElement.classList.remove("success-message");
            }
        })
        .catch(error => {
            console.error("Error fetching challenge data:", error);
            alert("Failed to load data from the server.");
        });
});
