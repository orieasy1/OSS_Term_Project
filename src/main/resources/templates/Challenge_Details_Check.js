document.addEventListener("DOMContentLoaded", () => {
    const challengeContainer = document.getElementById("challenge-container");

    // 로컬 스토리지에서 challenge_id 가져오기
    const challengeId = localStorage.getItem("challenge_id");
    if (!challengeId) {
        alert("챌린지를 선택하지 않았습니다.");
        return;
    }

    // API 호출로 챌린지 데이터 가져오기
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

                // DOM 업데이트
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

                // 챌린지 칸 생성
                renderChallengeBoxes(dayStatuses, challengeContainer);

                // 챌린지 종료 시 성공률 페이지로 이동
                if (remainingDays === 0) {
                    calculateAndRedirect(dayStatuses);
                }
            } else {
                alert("챌린지 데이터를 불러오는 데 실패했습니다.");
            }
        })
        .catch(error => {
            console.error("Error fetching challenge data:", error);
            alert("서버에서 데이터를 가져오지 못했습니다.");
        });
});

function renderChallengeBoxes(dayStatuses, container) {
    container.innerHTML = ""; // 기존 내용 초기화
    dayStatuses.forEach((status) => {
        const dayBox = document.createElement("div");
        dayBox.classList.add("challenge-box");
        dayBox.textContent = `DAY ${status.day}`;
        if (status.isCompleted) dayBox.classList.add("checked");

        // 상태 반전 및 업데이트
        dayBox.addEventListener("click", () => {
            status.isCompleted = !status.isCompleted;
            dayBox.classList.toggle("checked");

            // 서버 업데이트
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

    // 성공률 페이지로 데이터 전송
    fetch('/api/v1/challenges/success', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ completedDays, totalDays, successRate })
    }).then(() => {
        window.location.href = "success.html"; // 성공률 페이지로 이동
    });
}
