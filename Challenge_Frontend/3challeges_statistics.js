document.addEventListener('DOMContentLoaded', () => {
    const challengeList = document.getElementById('challengeList');
    const addChallengeButton = document.getElementById('addChallengeButton');
    const checkDetailButton = document.getElementById('checkDetailButton');
    const editModal = document.getElementById('editModal');
    const deleteModal = document.getElementById('deleteModal');
    const closeModalButton = document.querySelector('.close-button');
    const saveEditButton = document.getElementById('saveEditButton');
    const confirmDeleteButton = document.getElementById('confirmDeleteButton');
    const cancelDeleteButton = document.getElementById('cancelDeleteButton');

    let challenges = JSON.parse(localStorage.getItem('challenges')) || [];
    let currentEditIndex = null;

    // 챌린지 목록 표시
    function displayChallenges() {
        challengeList.innerHTML = '';
        challenges.forEach((challenge, index) => {
            const challengeBox = document.createElement('div');
            challengeBox.className = 'challenge-box';
            challengeBox.innerHTML = `
                <h3>${challenge.title}</h3>
                <p>Start Date: ${challenge.startDate}</p>
                <p>Duration: ${challenge.duration}</p>
                <button class="edit-button" data-index="${index}">Edit</button>
                <button class="delete-button" data-index="${index}">Delete</button>
            `;
            challengeList.appendChild(challengeBox);
        });

        // Edit 버튼
        document.querySelectorAll('.edit-button').forEach(button => {
            button.addEventListener('click', openEditModal);
        });

        // Delete 버튼
        document.querySelectorAll('.delete-button').forEach(button => {
            button.addEventListener('click', openDeleteModal);
        });
    }

    function openEditModal(event) {
        currentEditIndex = event.target.dataset.index;
        const challenge = challenges[currentEditIndex];
        document.getElementById('editTitle').value = challenge.title;

        // Extract and set Year, Month, Day values
        const [year, month, day] = challenge.startDate.split('-');
        document.getElementById('editYear').value = year;
        document.getElementById('editMonth').value = parseInt(month);
        document.getElementById('editDay').value = parseInt(day);

        editModal.style.display = 'block';
    }


    // Edit 저장
    saveEditButton.addEventListener('click', () => {
        const newTitle = document.getElementById('editTitle').value;
        const newYear = document.getElementById('editYear').value;
        const newMonth = document.getElementById('editMonth').value.padStart(2, '0');
        const newDay = document.getElementById('editDay').value.padStart(2, '0');

        challenges[currentEditIndex] = {
            title: newTitle,
            startDate: `${newYear}-${newMonth}-${newDay}`,
            duration: "D-50"
        };
        localStorage.setItem('challenges', JSON.stringify(challenges));
        displayChallenges();
        editModal.style.display = 'none';
    });

// Close Edit Modal when X button is clicked
    document.querySelector('.close-button').addEventListener('click', () => {
        const editModal = document.getElementById('editModal');
        editModal.style.display = 'none'; // Hide the modal
    });

    // Delete Modal 열기
    function openDeleteModal(event) {
        currentEditIndex = event.target.dataset.index;
        deleteModal.style.display = 'block';
    }

    // Delete 확인
    confirmDeleteButton.addEventListener('click', () => {
        challenges.splice(currentEditIndex, 1);
        localStorage.setItem('challenges', JSON.stringify(challenges));
        displayChallenges();
        deleteModal.style.display = 'none';
    });

    // Delete 취소
    cancelDeleteButton.addEventListener('click', () => {
        deleteModal.style.display = 'none';
    });

    // Add Challenge 페이지 이동
    addChallengeButton.addEventListener('click', () => {
        window.location.href = 'choose_challenge.html';
    });

    // Check Detail 버튼
    checkDetailButton.addEventListener('click', () => {
        window.location.href = 'Challenge_Details_Check.html';
    });

    displayChallenges();
});
// Populate Year, Month, Day dropdown options
function populateDateDropdowns() {
    const yearSelect = document.getElementById('editYear');
    const monthSelect = document.getElementById('editMonth');
    const daySelect = document.getElementById('editDay');

    // Populate Year (2024 - 2030)
    for (let year = 2024; year <= 2030; year++) {
        const option = document.createElement('option');
        option.value = year;
        option.textContent = year;
        yearSelect.appendChild(option);
    }

    // Populate Month (1 - 12)
    for (let month = 1; month <= 12; month++) {
        const option = document.createElement('option');
        option.value = month;
        option.textContent = month;
        monthSelect.appendChild(option);
    }

    // Populate Day (1 - 31)
    for (let day = 1; day <= 31; day++) {
        const option = document.createElement('option');
        option.value = day;
        option.textContent = day;
        daySelect.appendChild(option);
    }
}
populateDateDropdowns(); // Call this function when the page loads

