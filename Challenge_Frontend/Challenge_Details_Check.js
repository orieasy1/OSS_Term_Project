document.addEventListener("DOMContentLoaded", () => {
    const challengeContainer = document.getElementById("challenge-container");
    const totalDays = 50; // Total challenge days
    let completedDays = parseInt(localStorage.getItem("completedDays")) || 0;

    // Set static data
    document.getElementById("remainingDays").textContent = "D - 15";
    document.getElementById("challengeTitle").textContent = "🏃🏻‍♀️ Jogging everyday";

    // Render 50 challenge boxes
    for (let i = 1; i <= totalDays; i++) {
        const dayBox = document.createElement("div");
        dayBox.classList.add("challenge-box");
        dayBox.textContent = `DAY ${i}`;

        // Restore completed state
        const isCompleted = localStorage.getItem(`day${i}`) === "true";
        if (isCompleted) {
            dayBox.classList.add("checked");
            completedDays++;
        }

        // Toggle completed state on click
        dayBox.addEventListener("click", () => {
            const currentlyCompleted = dayBox.classList.toggle("checked");
            localStorage.setItem(`day${i}`, currentlyCompleted);

            // Update completedDays count
            completedDays = currentlyCompleted ? completedDays + 1 : completedDays - 1;
            localStorage.setItem("completedDays", completedDays);
        });

        challengeContainer.appendChild(dayBox);
    }

    // Handle "Check Current Success Rate" button
    const successRateButton = document.getElementById("successRateButton");
    successRateButton.addEventListener("click", () => {
        localStorage.setItem("totalDays", totalDays); // Save totalDays
        localStorage.setItem("completedDays", completedDays); // Save completedDays
        window.location.href = "Challenge_Endpage.html"; // Navigate to Endpage
    });
});
