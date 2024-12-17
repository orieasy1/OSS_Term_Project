const titleElement = document.getElementById('calendarTitle');
const calendarBody = document.getElementById('calendarBody');
const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
let currentMonth = 11; // December (index: 11)
let currentYear = 2024;

// 달력 렌더링
function renderCalendar() {
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
        row += `<td>${day}</td>`;
    }

    row += "</tr>";
    calendarBody.innerHTML = row;
}

// 이전 달 버튼 이벤트
document.getElementById('prevMonth').addEventListener('click', () => {
    currentMonth--;
    if (currentMonth < 0) {
        currentMonth = 11; // 12월
        currentYear--;
    }
    renderCalendar();
});

// 다음 달 버튼 이벤트
document.getElementById('nextMonth').addEventListener('click', () => {
    currentMonth++;
    if (currentMonth > 11) {
        currentMonth = 0; // 1월
        currentYear++;
    }
    renderCalendar();
});

// 초기 렌더링
renderCalendar();
