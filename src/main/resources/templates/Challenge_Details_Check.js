document.addEventListener("DOMContentLoaded", () => {
    const challengeContainer = document.getElementById("challenge-container");

    const challengeId = localStorage.getItem("challenge_id");
    if (!challengeId) {
        alert("챌린지를 선택하지 않았습니다.");
        return;
    }

    fetch(`/api/v1/challenges/${challengeId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch challenge data");
            }
            return response.json();
        })
        .then(data => {
            if (data.success) {
                const { challengeTitle, totalDays, startDate, endDate, dayStatuses } = data;

                document.getElementById("challengeTitle").textContent = challengeTitle || "Your Challenge";
                document.getElementById("challengeDays").textContent = `${totalDays} days`;
                document.getElementById("startDate").textContent = startDate || "N/A";
                document.getElementById("endDate").textContent = endDate || "N/A";

                const today = new Date();
                let remainingDays = "N/A";
                if (endDate) {
                    const end = new Date(endDate);
                    if (!isNaN(end.getTime())) {
                        remainingDays = Math.max(Math.ceil((end - today) / (1000 * 60 * 60 * 24)), 0);
                    }
                }
                document.getElementById("remainingDays").textContent = `D - ${remainingDays}`;

                renderChallengeBoxes(dayStatuses, challengeContainer);

                if (remainingDays === 0) {
                    calculateAndRedirect(dayStatuses);
                }
            } else {
                alert("Failed to retrieve challenge data.");
            }
        })
        .catch(error => {
            console.error("Error fetching challenge data:", error);
            alert("Failed to get data from the server.");
        });
});

function renderChallengeBoxes(dayStatuses, container) {
    container.innerHTML = "";
    dayStatuses.forEach((status) => {
        const dayBox = document.createElement("div");
        dayBox.classList.add("challenge-box");
        dayBox.textContent = `DAY ${status.day}`;
        if (status.isCompleted) dayBox.classList.add("checked");

        dayBox.addEventListener("click", () => {
            status.isCompleted = !status.isCompleted;
            dayBox.classList.toggle("checked");

            fetch(`/api/v1/challenges/${challengeId}/day/${status.day}`, {
                method: 'PUT',
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ isCompleted: status.isCompleted })
            });
        });

        container.appendChild(dayBox);
    });
}

function calculateAndRedirect(dayStatuses) {
    const completedDays = dayStatuses.filter(status => status.isCompleted).length;
    const totalDays = dayStatuses.length;

    const successRate = ((completedDays / totalDays) * 100).toFixed(2);

    fetch('/api/v1/challenges/success', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ completedDays, totalDays, successRate })
    }).then(() => {
        window.location.href = "success.html";
    });
}
