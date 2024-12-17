const createButton = document.getElementById('createButton');

createButton.addEventListener('click', () => {
  const title = document.getElementById('challengeTitle').value;
  const startYear = document.getElementById('startYear').value;
  const startMonth = document.getElementById('startMonth').value.padStart(2, '0');
  const startDay = document.getElementById('startDay').value.padStart(2, '0');
  const startDate = `${startYear}/${startMonth}/${startDay}`;

  if (!title || !startYear || !startMonth || !startDay) {
    alert('Please choose the all field!');
    return;
  }

  let challenges = JSON.parse(localStorage.getItem('challenges')) || [];
  challenges.push({ title, startDate, duration: 'D-50' });
  localStorage.setItem('challenges', JSON.stringify(challenges));

  // 3challenges_statistics 페이지로 이동
  window.location.href = '3challeges_statistics.html';
});