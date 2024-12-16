document.addEventListener('DOMContentLoaded', () => {
    const closeButton = document.getElementById('close-modal');

    // Close 버튼 클릭 시 모달 닫기 및 페이지 이동
    closeButton.addEventListener('click', () => {
        alert('Returning to the To-Do List page...');
        window.location.href = '/api/v1/todolist/DAY'; // To-Do 리스트 페이지 URL
    });
});