// to-do-list_registration_script.js
document.addEventListener("DOMContentLoaded", () => {
    const params = new URLSearchParams(window.location.search);

    if (params.has("edit")) {
        const index = params.get("index");
        const tasks = JSON.parse(localStorage.getItem("daily")) || [];
        const task = tasks[index];

        document.getElementById("task-title").value = task.title;
        document.getElementById("task-description").value = task.description;
        document.getElementById("task-priority").value = task.priority;
        document.getElementById("day-input").value = task.specificField;

        document.getElementById("save-btn").addEventListener("click", () => {
            task.title = document.getElementById("task-title").value;
            task.description = document.getElementById("task-description").value;
            task.priority = document.getElementById("task-priority").value;
            task.specificField = document.getElementById("day-input").value;

            tasks[index] = task;
            localStorage.setItem("daily", JSON.stringify(tasks));
            alert("Task updated!");
            location.href = "daily.html";
        });
    }
});
