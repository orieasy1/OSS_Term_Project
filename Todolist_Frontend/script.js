document.addEventListener("DOMContentLoaded", () => {
    const modal = document.getElementById("deleteModal");
    const confirmDelete = document.getElementById("confirmDelete");
    const cancelDelete = document.getElementById("cancelDelete");

    // 삭제 모달 열기 (예제용, 원하는 조건에 연결)
    modal.style.display = "flex"; // 이 부분은 예제이므로 테스트 시 보이게 합니다.

    // Yes 버튼 클릭 시 (삭제 확정)
    confirmDelete.addEventListener("click", () => {
        alert("To-do has been deleted.");
        modal.style.display = "none";
    });

    // No 버튼 클릭 시 (삭제 취소)
    cancelDelete.addEventListener("click", () => {
        modal.style.display = "none";
    });
});
