const user = { id: "user123", nickname: "John" };
localStorage.setItem("user", JSON.stringify(user));

const userData = JSON.parse(localStorage.getItem("user"));
console.log(userData.nickname); // "John"
