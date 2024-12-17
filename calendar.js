const titleElement = document.getElementById('calendarTitle');
const calendarBody = document.getElementById('calendarBody');
const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
let currentMonth = 11; // December (index: 11)
let currentYear = 2024;
let dailyProgressData = [];

// API 호출 및 데이터 가져오기
async function fetchCalendarData(year, month) {
    try {
        const response = await fetch(`/api/v1/calendar?year=${year}&month=${month + 1}`);
        const result = await response.json();
        if (result.code === "SUCCESS") {
            dailyProgressData = result.data.daily_progress;
        } else {
            console.error("Failed to fetch calendar data");
        }
    } catch (error) {
        console.error("Error fetching calendar data: ", error);
    }
}

// 달력 렌더링
async function renderCalendar() {
    await fetchCalendarData(currentYear, currentMonth);

    titleElement.textContent = `${months[currentMonth]}, ${currentYear}`;
    calendarBody.innerHTML = "";

    const firstDay = new Date(currentYear, currentMonth, 1).getDay(); // 시작 요일
    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate(); // 해당 월의 총 일 수

    let row = "<tr>";
    for (let i = 0; i < firstDay; i++) {
        row += "<td></td>"; // 첫 주 시작 요일 앞의 빈 칸
    }

    for (let day = 1; day <= daysInMonth; day++) {
        if ((firstDay + day - 1) % 7 === 0 && day !== 1) {
            row += "</tr><tr>"; // 주 단위로 행 변경
        }

        const date = `${currentYear}-${String(currentMonth + 1).padStart(2, "0")}-${String(day).padStart(2, "0")}`;
        const progress = dailyProgressData.find(p => p.date === date);

        const greenTag = progress?.tasks?.reduce((sum, t) => sum + t.green_tag, 0) || 0;
        const redTag = progress?.tasks?.reduce((sum, t) => sum + t.red_tag, 0) || 0;

        row += `
            <td>
                <div>${day}</div>
                ${greenTag > 0 ? `<div class="green-tag">Green: ${greenTag}</div>` : ""}
                ${redTag > 0 ? `<div class="red-tag">Red: ${redTag}</div>` : ""}
            </td>`;
    }

    row += "</tr>";
    calendarBody.innerHTML = row;
}

// 이전 달 버튼
document.getElementById('prevMonth').addEventListener('click', () => {
    currentMonth--;
    if (currentMonth < 0) {
        currentMonth = 11;
        currentYear--;
    }
    renderCalendar();
});

// 다음 달 버튼
document.getElementById('nextMonth').addEventListener('click', () => {
    currentMonth++;
    if (currentMonth > 11) {
        currentMonth = 0;
        currentYear++;
    }
    renderCalendar();
});

// 홈 버튼 클릭 이벤트 추가
document.getElementById('homeButton').addEventListener('click', () => {
    window.location.href = '/'; // 홈 페이지 URL로 이동
});

// 초기 렌더링
renderCalendar();
