// JSON 객체 저장
const user = { id: "user123", nickname: "John" };
localStorage.setItem("user", JSON.stringify(user));

// JSON 객체 불러오기
const userData = JSON.parse(localStorage.getItem("user"));
console.log(userData.nickname); // "John"
