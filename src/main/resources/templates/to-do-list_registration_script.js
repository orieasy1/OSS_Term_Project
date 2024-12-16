// Save 버튼 이벤트
const saveButton = document.getElementById('save-btn');

if (saveButton) {
    saveButton.addEventListener('click', event => {
        // Task Type 가져오기
        const taskType = document.getElementById('task-type').value;

        // 공통 데이터
        const taskTitle = document.getElementById('task-title').value;
        const taskDescription = document.getElementById('task-description').value;
        const taskPriority = document.getElementById('task-priority').value;

        // JSON 데이터 구성
        let requestBody = {
            title: taskTitle,
            description: taskDescription,
            priority: taskPriority
        };

        // task-type에 따라 다른 필드 추가
        if (taskType === 'daily') {
            requestBody.day = document.getElementById('day-input').value;
        } else if (taskType === 'weekly') {
            requestBody.week = document.getElementById('week-input').value;
        } else if (taskType === 'monthly') {
            requestBody.month = document.getElementById('month-input').value;
        }

        // 서버로 데이터 전송
        fetch('/api/tasks', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(requestBody)
        })
            .then(response => {
                if (response.ok) {
                    alert('Task saved successfully!');
                    location.reload(); // 페이지 새로고침
                } else {
                    alert('Error saving task. Please try again.');
                }
            });
    });
}