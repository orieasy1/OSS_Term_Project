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

    const firstDay = new Date(currentYear, currentMonth, 1).getDay();
    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();

    let row = "<tr>";
    for (let i = 0; i < firstDay; i++) {
        row += "<td></td>";
    }

    for (let day = 1; day <= daysInMonth; day++) {
        if ((firstDay + day - 1) % 7 === 0 && day !== 1) {
            row += "</tr><tr>";
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

// 이전/다음 달 버튼
document.getElementById('prevMonth').addEventListener('click', () => {
    currentMonth--;
    if (currentMonth < 0) {
        currentMonth = 11;
        currentYear--;
    }
    renderCalendar();
});

document.getElementById('nextMonth').addEventListener('click', () => {
    currentMonth++;
    if (currentMonth > 11) {
        currentMonth = 0;
        currentYear++;
    }
    renderCalendar();
});

// Dec, 2024 버튼 클릭 이벤트 추가
titleElement.addEventListener('click', () => {
    alert(`현재 선택된 날짜: ${months[currentMonth]}, ${currentYear}`);
});

// 초기 렌더링
renderCalendar();

