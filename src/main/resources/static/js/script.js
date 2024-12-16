// CREATE 버튼 클릭 이벤트
const createButton = document.getElementById('createButton');

if (createButton) {
    createButton.addEventListener('click', () => {
        // 입력 필드에서 값 가져오기
        const title = document.getElementById('challengeTitle').value.trim();
        const details = document.getElementById('challengeDetails').value.trim();
        const startDate = document.getElementById('startDate').value.trim();
        const duration = document.getElementById('duration').value.trim();

        // 입력값 유효성 검사
        if (!title || !details || !startDate || !duration) {
            alert('모든 필드를 입력해주세요!');
            return;
        }

        // API 요청 데이터
        const requestData = {
            title: title,
            description: details,
            startDate: startDate,  // 필드명 수정
            duration: duration
        };

        // 챌린지 생성 API 호출
        fetch('/api/v1/challenges/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestData),
        })
            .then(response => {
                if (!response.ok) {
                    // 서버 오류 메시지 처리
                    return response.json().then(errorData => {
                        throw new Error(errorData.message || '챌린지 생성 중 오류가 발생했습니다.');
                    });
                }
                return response.json(); // 성공 시 응답 데이터 반환
            })
            .then(data => {
                // 성공 메시지 및 페이지 이동
                alert(`챌린지가 생성되었습니다.\nTitle: ${data.title}`);
                window.location.href = '/challenges'; // 챌린지 목록 페이지로 이동
            })
            .catch(error => {
                // 오류 메시지 표시
                alert(`오류: ${error.message}`);
            });
    });
}
