// To-Do 리스트 데이터
const todoData = [
    { title: "Task Title", description: "Description of the task goes here.", priority: "high" },
    { title: "Another Task", description: "Description of the task goes here.", priority: "medium" },
    { title: "Low Priority Task", description: "Description of the task goes here.", priority: "low" }
];

// HTML 요소 가져오기
const todoList = document.getElementById('todoList');
const progressBar = document.getElementById('progressBar');
const progressLabel = document.getElementById('progressLabel');

// 진행률 바 업데이트 함수
function updateProgressBar(rate = 0) {
    progressBar.style.width = `${rate}%`;
    progressLabel.textContent = `${rate}%`;
}

// To-Do 리스트 렌더링 함수
function renderTodoList() {
    todoList.innerHTML = ""; // 기존 내용 초기화

    todoData.forEach(task => {
        const todoItem = document.createElement('div');
        todoItem.classList.add('todo-item');

        todoItem.innerHTML = `
            <h3>${task.title}</h3>
            <p>${task.description}</p>
            <span class="priority ${task.priority}">${task.priority.charAt(0).toUpperCase() + task.priority.slice(1)}</span>
        `;

        todoList.appendChild(todoItem);
    });
}

// 초기 렌더링
document.addEventListener('DOMContentLoaded', () => {
    renderTodoList();    // To-Do 리스트 렌더링
    updateProgressBar(0); // 초기 진행률 0%
});
