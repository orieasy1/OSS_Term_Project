document.addEventListener("DOMContentLoaded", () => {
    const totalDays = 50; // Fixed total days
    const completedDays = parseInt(localStorage.getItem("completedDays")) || 0; // Get saved progress

    // Calculate success rate
    const progressRate = ((completedDays / totalDays) * 100).toFixed(2);

    // Update the DOM
    document.getElementById("challengeTitle").textContent = "🏃🏻‍♀️ Jogging everyday";
    document.getElementById("remainingDays").textContent = "D - 15";
    document.getElementById("challengeDates").textContent = "12/11/24 - 31/12/24";
    document.getElementById("totalDays").textContent = totalDays;
    document.getElementById("completedDays").textContent = completedDays;
    document.getElementById("successRate").textContent = `${progressRate}% success!`;

    // Update motivational message
    const messageElement = document.getElementById("resultMessage");
    messageElement.textContent = "Fighting!🔥";
    messageElement.style.color = progressRate === "100.00" ? "green" : "#FF4500";
});
